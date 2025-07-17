package com.example.mywebsite.repository;

import com.example.mywebsite.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
// This interface extends JpaRepository, which provides CRUD operations for the Note entity.