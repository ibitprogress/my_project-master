package ua.autostock.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.autostock.DTO.UserDTO;
import ua.autostock.entity.CarEntity;
import ua.autostock.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserDTO saveUser (UserDTO userDto);

    UserDTO findUserById(Long id);

    Page<UserEntity> getUsersByPage(Pageable pageable);

    UserDTO updateUserById(Long id, UserDTO userToUpdate);

    void deleteUserById (Long id);


}
