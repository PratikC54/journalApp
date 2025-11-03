package com.PratikC54.journalApp.controller;

import com.PratikC54.journalApp.Entity.JournalEntry;
import com.PratikC54.journalApp.Entity.User;
import com.PratikC54.journalApp.services.JournalEntryServices;
import com.PratikC54.journalApp.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryServices journalEntryServices;

    @Autowired
    private UserServices userServices;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username) {
        User user = userServices.findByUserName(username);
        List<JournalEntry> all = user.getJournalEntries();
        if (all!=null && !all.isEmpty()) return new ResponseEntity<>(all,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry , @PathVariable String username) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryServices.saveEntry(myEntry , username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
       Optional<JournalEntry> journalEntry = journalEntryServices.findbyId(myId);
        if(journalEntry.isPresent()) return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        return new ResponseEntity<>(journalEntry.get(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<JournalEntry> deleteEntryById(@PathVariable String username , @PathVariable ObjectId myId) {
        journalEntryServices.deleteById(username,myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{username}/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable String username, @PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
       JournalEntry old = journalEntryServices.findbyId(myId).orElse(null);
       if(old !=null) {
           old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
           old.setContent(newEntry.getContent()!=null && !newEntry.equals("")? newEntry.getContent() : old.getContent());
       }
       journalEntryServices.saveEntry(old);
       return new ResponseEntity<>(old, HttpStatus.OK);
    }
}