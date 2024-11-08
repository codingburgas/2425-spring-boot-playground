package org.codingburgas.springbootplayground.students.controller;

import org.codingburgas.springbootplayground.students.model.Student;
import org.codingburgas.springbootplayground.students.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.logging.Logger;

@Controller
@RequestMapping("/students")
public class StudentController {

  private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());

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

  @GetMapping("/{id}")
  public String getStudentDetails(@PathVariable Integer id, Model model) {
    // TODO 6: Implement the method, so that it passes the information of the Student with the specified id to the model (template)
    model.addAttribute("student", studentService.getStudentById(id));
    return "student_details.html";
  }
}