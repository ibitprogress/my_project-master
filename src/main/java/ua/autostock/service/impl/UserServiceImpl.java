package ua.autostock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.autostock.DTO.UserDTO;
import ua.autostock.exceptions.NotFoundException;
import ua.autostock.entity.UserEntity;
import ua.autostock.exceptions.AlreadyExistsException;
import ua.autostock.exceptions.ServerException;
import ua.autostock.repository.UserRepository;
import ua.autostock.service.UserService;
import ua.autostock.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public UserDTO findUserById(Long id) {
        boolean exists = userRepository.existsById(id);
        if (exists){
        UserEntity userEntity = userRepository.findById(id).get();
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
        }else {
            throw new NotFoundException("User with id: " + id + " not found");
        }
    }

    @Override
    public UserDTO saveUser(UserDTO userDto) {
        if (userDto.getPassword().equals(userDto.getPasswordConfirm())) {
                UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
                userRepository.save(userEntity);
            return userDto;
        }else {
            throw new ServerException("Password not match");
        }
    }


    @Override
    public UserDTO updateUserById(Long id, UserDTO userToUpdate) {
        boolean exists = userRepository.existsById(id);

        if(!exists) {
            return null;
        }else {
            UserEntity userFromDB = userRepository.findById(id).get();

            userFromDB.setFirstName(userToUpdate.getFirstName());
            userFromDB.setSurName(userToUpdate.getSurName());
            userFromDB.setTelephone(userToUpdate.getTelephone());
            userFromDB.setEmail(userToUpdate.getEmail());
            userFromDB.setPassword(userToUpdate.getPassword());
            userFromDB.setGender(userToUpdate.getGender());
            userFromDB.setAge(userToUpdate.getAge());

            userRepository.save(userFromDB);
            UserDTO userDTO = modelMapper.map(userFromDB, UserDTO.class);
            return userDTO;
        }

    }

    @Override
    public void deleteUserById(Long id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
    }

    @Override
    public Page<UserEntity> getUsersByPage(Pageable pageable) {
        Page<UserEntity> userEntities = userRepository.findAll(pageable);
        return userEntities;
    }
}
