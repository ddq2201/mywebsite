package com.example.mywebsite.controller;

import com.example.mywebsite.model.Note;
import com.example.mywebsite.repository.NoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    private final NoteRepository noteRepo;

    public NoteController(NoteRepository noteRepo) {
        this.noteRepo = noteRepo;
    }

    @GetMapping("/notes")
    public String viewNotes(Model model) {
        model.addAttribute("pageTitle", "Notes");
        model.addAttribute("contentFragment", "notes");
        model.addAttribute("notes", noteRepo.findAll());
        return "layout";
    }

    @PostMapping("/notes")
    public String addNote(@RequestParam("content") String content) {
        noteRepo.save(new Note(content));
        return "redirect:/notes";
    }

    @PostMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteRepo.deleteById(id);
        return "redirect:/notes";
    }

    @GetMapping("/notes/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Note note = noteRepo.findById(id).orElse(null);
        if (note == null) return "redirect:/notes";

        model.addAttribute("noteToEdit", note);
        model.addAttribute("notes", noteRepo.findAll());
        model.addAttribute("pageTitle", "Edit Note");
        model.addAttribute("contentFragment", "notes");
        return "layout";
    }

    @PostMapping("/notes/update")
    public String updateNote(@RequestParam Long id, @RequestParam String content) {
        Note note = noteRepo.findById(id).orElse(null);
        if (note != null) {
            note.setContent(content);
            noteRepo.save(note);
        }
        return "redirect:/notes";
    }
}
