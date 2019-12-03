package com.laundry.moving.modal.repo;

import com.laundry.moving.modal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserEntityRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsernameAndPassword(String username, String password);
    Optional findByToken(String token);
}
