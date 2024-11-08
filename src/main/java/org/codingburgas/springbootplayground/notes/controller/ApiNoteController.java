package org.codingburgas.springbootplayground.notes.controller;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.Subject;
import org.codingburgas.springbootplayground.notes.service.NoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class ApiNoteController {

  private final NoteService noteService;

  public ApiNoteController(NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping
  public List<Note> getNotes(@RequestParam() Optional<Subject> subject, @RequestParam Optional<Integer> value) {
    return noteService.getNotes(subject.orElse(null), value.orElse(null));
  }
}