package com.banco.service;


import com.banco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banco.model.User;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Create user method
    public User createUser(User user) {

        return userRepository.save(user);
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }




}
