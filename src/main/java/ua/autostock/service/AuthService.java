package ua.autostock.service;

import ua.autostock.DTO.Request.SignInRequest;
import ua.autostock.DTO.Request.SignUpRequest;

public interface AuthService {

    void registerUser(SignUpRequest request);

    String loginUser (SignInRequest request);


}
