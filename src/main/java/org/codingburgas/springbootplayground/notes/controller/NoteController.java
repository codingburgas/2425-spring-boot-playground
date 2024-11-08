package org.codingburgas.springbootplayground.notes.controller;

import org.codingburgas.springbootplayground.notes.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoteController {

  private final NoteService noteService;

  public NoteController(NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping("/notes")
  public String getNotes(Model model) {
    model.addAttribute("info", noteService.getOverviewInfo());
    return "notes";
  }
}