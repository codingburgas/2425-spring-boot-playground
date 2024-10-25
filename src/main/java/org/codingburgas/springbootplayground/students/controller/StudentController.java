package org.codingburgas.springbootplayground.students.controller;

import org.codingburgas.springbootplayground.students.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public String overview(Model model) {
    model.addAttribute("students", studentService.getStudents());
    return "students";
  }

  @GetMapping("/create")
  public String create() {
    return "students_create";
  }
}