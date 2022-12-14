package com.example.users_2_3_1.service;


import com.example.users_2_3_1.model.Role;
import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.repository.RoleRepository;
import com.example.users_2_3_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsersWhoWantToBeAdmin() {
        return userRepository.findByRequestForAdmin(true);
    }

    public User saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return userFromDB;
        }

        user.setRequestForAdmin(false);
        return userRepository.save(user);
    }

    public void changeRequestForAdmin(Long id) {
        User user = findById(id);
        boolean status = user.isRequestForAdmin();
        user.setRequestForAdmin(!status);

        userRepository.save(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateUser(User updatedUser, Long id) {
        User userToBeUpdated = findById(id);

        updatedUser.setPassword(userToBeUpdated.getPassword());

        if (updatedUser.getRoles() == null) {
            Set<Role> roles = userToBeUpdated.getRoles();
            updatedUser.setRoles(roles);
        }

        updatedUser.setRequestForAdmin(userToBeUpdated.isRequestForAdmin());

        userRepository.save(updatedUser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
