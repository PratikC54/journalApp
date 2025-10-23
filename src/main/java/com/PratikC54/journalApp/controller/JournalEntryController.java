package com.PratikC54.journalApp.controller;

import com.PratikC54.journalApp.Entity.JournalEntry;
import com.PratikC54.journalApp.services.JournalEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryServices journalEntryServices;

    @GetMapping
    public List<JournalEntry> getAll() {
        return null;
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryServices.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntry(@PathVariable long myId) {
        return null;
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntry(@PathVariable long myId) {
        return null;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable String id, @RequestBody JournalEntry myEntry) {
        return null;
    }
}
