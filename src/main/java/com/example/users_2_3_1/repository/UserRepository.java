package com.example.users_2_3_1.repository;

import com.example.users_2_3_1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
