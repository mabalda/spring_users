package com.example.users_2_3_1.service;

import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return user;
    }

    public void changeRequestForAdmin() {
        User user = getCurrentUser();
        boolean status = user.isRequestForAdmin();
        user.setRequestForAdmin(!status);
        userRepository.save(user);
    }

    public void resetPassword(User user) {
        User userForUpdate = userRepository.findByTelegramUsername(user.getTelegramUsername());
        userForUpdate.setPassword(user.getPassword());
        userRepository.save(userForUpdate);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
