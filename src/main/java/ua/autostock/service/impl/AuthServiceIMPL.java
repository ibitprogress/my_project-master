package ua.autostock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.autostock.DTO.Request.SignInRequest;
import ua.autostock.DTO.Request.SignUpRequest;
import ua.autostock.config.JWT.JwtTokenProvider;
import ua.autostock.entity.RoleEntity;
import ua.autostock.entity.UserEntity;
import ua.autostock.exceptions.AlreadyExistsException;
import ua.autostock.exceptions.NotFoundException;
import ua.autostock.exceptions.ServerException;
import ua.autostock.repository.RoleRepository;
import ua.autostock.repository.UserRepository;
import ua.autostock.service.AuthService;

import java.util.Arrays;

@Service
public class AuthServiceIMPL implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void registerUser(SignUpRequest request) {

        if(userRepository.existsByEmailIgnoreCase(request.getEmail())){
            throw new AlreadyExistsException("user with email - " + request.getEmail() +" already exists");
        }else if(userRepository.existsByTelephone(request.getTelephone())){
            throw new AlreadyExistsException("user with telephone - " + request.getTelephone() + " already exists");
        }

        System.out.println(request);

        if(request.getPassword().equals(request.getPasswordConfirm())) {
            String password = request.getPassword();
            String encPassword = passwordEncoder.encode(password);
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(request.getFirstName());
            userEntity.setSurName(request.getSurName());
            userEntity.setEmail(request.getEmail());
            userEntity.setTelephone(request.getTelephone());
            userEntity.setAge(request.getAge());
            userEntity.setGender(request.getGender());
            userEntity.setRegistryDate(request.getRegistryDate());
            userEntity.setPassword(encPassword);

            RoleEntity roleEntity = roleRepository.findByRoleIgnoreCase("USER")
                    .orElseThrow(() -> new NotFoundException("role not found"));
            userEntity.setRoles(Arrays.asList(roleEntity));
            userRepository.save(userEntity);
        }else {
            throw new ServerException("Pass not match");
        }
    }

    @Override
    public String loginUser(SignInRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
}
