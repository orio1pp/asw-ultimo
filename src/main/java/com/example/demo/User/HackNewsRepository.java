package com.example.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface HackNewsRepository extends JpaRepository<User, String> {
    public User findUserByUsername(String username);
}
