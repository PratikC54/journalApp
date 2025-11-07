package com.PratikC54.journalApp.services;

import com.PratikC54.journalApp.Entity.User;
import com.PratikC54.journalApp.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserServices {
    @Autowired
    private UserRepository userrepository;

  

    public List<User> getAll() {
        return userrepository.findAll();
    }

    public Optional<User> findbyId(ObjectId id) {
        return userrepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userrepository.deleteById(id);
    }
    public User findByUserName(String username) {
        return userrepository.findByUsername(username);
    }


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userrepository.save(user);
    }

    public void saveNewUser(User user) {
        userrepository.save(user);
    }
}
