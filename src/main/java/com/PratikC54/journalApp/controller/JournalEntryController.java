package com.PratikC54.journalApp.controller;

import com.PratikC54.journalApp.Entity.JournalEntry;
import com.PratikC54.journalApp.services.JournalEntryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryServices journalEntryServices;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryServices.getAll();
    }
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryServices.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
       return journalEntryServices.findbyId(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId) {
        journalEntryServices.deleteById(myId);
        return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
       JournalEntry old = journalEntryServices.findbyId(id).orElse(null);
       if(old !=null) {
           old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
           old.setContent(newEntry.getContent()!=null && !newEntry.equals("")? newEntry.getContent() : old.getContent());
       }
       journalEntryServices.saveEntry(old);
       return old;
    }
}