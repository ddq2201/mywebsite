package com.example.mywebsite.repository;

import com.example.mywebsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
