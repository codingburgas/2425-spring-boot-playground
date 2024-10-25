package org.codingburgas.springbootplayground.students.controller;

import org.codingburgas.springbootplayground.students.model.Student;
import org.codingburgas.springbootplayground.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.logging.Logger;

@Controller
@RequestMapping("/students")
public class StudentController {

  private final static Logger LOGGER = Logger.getLogger(StudentController.class.getName());

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
  public String createTemplate() {
    return "students_create";
  }

  @PostMapping("/create")
  public RedirectView create(@ModelAttribute Student student) {
    LOGGER.info(student.getFirstname());
    studentService.addStudent(student);
    return new RedirectView("/students");
  }
}