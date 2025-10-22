package com.PratikC54.journalApp.controller;

import Entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntry> journalentries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalentries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalentries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntry(@PathVariable long myId) {
        return journalentries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntry(@PathVariable long myId) {
        return journalentries.remove(myId);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable long id, @RequestBody JournalEntry myEntry) {
        return journalentries.put(id,myEntry);
    }
}
