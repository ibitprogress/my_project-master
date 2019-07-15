package ua.autostock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.autostock.entity.UserEntity;
import ua.autostock.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {

    boolean existsById(Long id);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByTelephone(String telephone);

    UserEntity findByEmailIgnoreCase(String email);

    UserEntity findByTelephone(String telephone);

}
