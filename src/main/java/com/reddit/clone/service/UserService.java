package com.reddit.clone.service;

import com.reddit.clone.dto.RegisterDto;
import com.reddit.clone.model.User;
import com.reddit.clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String userName);

    User save(User user);
}
