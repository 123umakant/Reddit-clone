package com.reddit.clone.service.implementation;

import com.mountblue.blogapplication.model.RedditUserDetails;
import com.reddit.clone.model.User;
import com.reddit.clone.repository.UserRepository;
import com.reddit.clone.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {

    private UserRepository userRepository;

    public UserServiceimpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.findByEmail(userName);
        return new RedditUserDetails(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
