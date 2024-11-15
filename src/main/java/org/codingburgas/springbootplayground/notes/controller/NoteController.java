package org.codingburgas.springbootplayground.notes.controller;

import org.codingburgas.springbootplayground.notes.service.NoteService;
import org.codingburgas.springbootplayground.students.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoteController {

  private final NoteService noteService;
  private final StudentService studentService;

  public NoteController(NoteService noteService, StudentService studentService) {
    this.noteService = noteService;
    this.studentService = studentService;
  }

  @GetMapping("/notes")
  public String getNotes(Model model) {
    model.addAttribute("info", noteService.getOverviewInfo());
    model.addAttribute("bestStudent", studentService.getBestStudent());
    // TODO: Add model attributes for worstStudent, student with most notes 6 and student with most notes 2 here
    //       in order to show the information in the template
    return "notes";
  }
}