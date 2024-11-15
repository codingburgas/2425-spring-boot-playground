package org.codingburgas.springbootplayground.notes.controller;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.Subject;
import org.codingburgas.springbootplayground.notes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/notes")
public class ApiNoteController {

  private final NoteService noteService;

  public ApiNoteController(NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping
  public List<Note> getNotes(@RequestParam() Optional<Subject> subject, @RequestParam Optional<BigDecimal> value) {
    return noteService.getNotes(subject.orElse(null), value.orElse(null));
  }

  @GetMapping("/random")
  public ResponseEntity<Note> getRandomNote() {
    var random = new Random();
    var testNote = new Note(BigDecimal.valueOf(random.nextDouble(2.00, 6.00)), LocalDate.now(), Subject.getRandomSubject());
    return new ResponseEntity<>(testNote, HttpStatus.PARTIAL_CONTENT);
  }
}