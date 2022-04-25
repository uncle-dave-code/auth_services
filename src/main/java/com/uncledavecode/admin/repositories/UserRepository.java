package com.uncledavecode.admin.repositories;

import java.util.Optional;

import com.uncledavecode.admin.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
