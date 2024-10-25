package org.codingburgas.springbootplayground.home.controller;

import org.codingburgas.springbootplayground.students.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

  private final StudentService studentService;

  public HomeController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public String index() {
    return "index";
  }


}