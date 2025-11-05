package com.PratikC54.journalApp.services;

import com.PratikC54.journalApp.Entity.JournalEntry;
import com.PratikC54.journalApp.Entity.User;
import com.PratikC54.journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {
    @Autowired
    private JournalEntryRepository journalentryrepository;

    @Autowired
    private UserServices userServices ;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        User user = userServices.findByUserName(username);
        if(user!=null) {
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalentryrepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userServices.saveEntry(user);
        }
        else throw new RuntimeException("User not found : "+username);
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalentryrepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalentryrepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalentryrepository.findById(id);
    }

    public void deleteById(String username, ObjectId id) {
        User user = userServices.findByUserName(username);
        if(user!=null) {
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userServices.saveEntry(user);
            journalentryrepository.deleteById(id);
        }
    }
}
