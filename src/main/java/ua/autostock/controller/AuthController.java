package ua.autostock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.autostock.DTO.Request.SignInRequest;
import ua.autostock.DTO.Request.SignInResponse;
import ua.autostock.DTO.Request.SignUpRequest;
import ua.autostock.service.AuthService;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("signup")
    public ResponseEntity registerUser (@Valid @RequestBody SignUpRequest request){
        authService.registerUser(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity loginUser (@Valid @RequestBody SignInRequest request){
        String token = authService.loginUser(request);
        System.out.println(token);
        return new ResponseEntity(SignInResponse.builder().token(token).build(), HttpStatus.OK);
    }



}
