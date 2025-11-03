package com.PratikC54.journalApp.services;

import com.PratikC54.journalApp.Entity.User;
import com.PratikC54.journalApp.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void saveEntry(User user) {
        userrepository.save(user);
    }
}
