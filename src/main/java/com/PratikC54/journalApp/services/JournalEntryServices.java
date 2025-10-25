package com.PratikC54.journalApp.services;

import com.PratikC54.journalApp.Entity.JournalEntry;
import com.PratikC54.journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {
    @Autowired
    private JournalEntryRepository journalentryrepository;

    public void saveEntry(JournalEntry journalEntry) {
        journalentryrepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalentryrepository.findAll();
    }

    public Optional<JournalEntry> findbyId(ObjectId id) {
        return journalentryrepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        journalentryrepository.deleteById(id);
    }
}
