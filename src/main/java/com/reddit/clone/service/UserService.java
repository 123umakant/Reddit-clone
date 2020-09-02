package com.reddit.clone.service;

import com.reddit.clone.dto.RegisterDto;
import com.reddit.clone.model.User;
import com.reddit.clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void registerUser(RegisterDto registerDto){
        User user = new User();
        user.setUserName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
