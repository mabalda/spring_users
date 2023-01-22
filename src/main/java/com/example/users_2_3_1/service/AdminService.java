package com.example.users_2_3_1.service;

import com.example.users_2_3_1.model.RequestDTO;
import com.example.users_2_3_1.model.Role;
import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.repository.RoleRepository;
import com.example.users_2_3_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

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

    public void requestForApplication(RequestDTO request) {
        Long userId = request.getId();
        User user = findById(userId);

        if (request.isApplied()) {
            Role adminRole = roleRepository.findByRole("ROLE_ADMIN");
            user.getRoles().add(adminRole);
        }

        boolean status = user.isRequestForAdmin();
        user.setRequestForAdmin(!status);
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
