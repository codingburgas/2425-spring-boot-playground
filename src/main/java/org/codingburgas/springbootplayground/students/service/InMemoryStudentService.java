package org.codingburgas.springbootplayground.students.service;

import org.codingburgas.springbootplayground.students.model.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class InMemoryStudentService implements StudentService {

  private final static Logger LOGGER = Logger.getLogger(InMemoryStudentService.class.getName());

  private List<Student> students = new ArrayList<>();

  public InMemoryStudentService() {
  LOGGER.log(Level.WARNING, "Student Service created");
    generateSomeStudents();
  }

  @Override
  public List<Student> getStudents() {
    return students;
  }

  @Override
  public Student getStudentByUsername(String username) {
    return null;
  }

  @Override
  public void addStudent(Student student) {
    students.add(student);
  }

  @Override
  public Student getStudentById(Integer id) {
    // TODO 6: impement method to return the correct Student from the collection
    return null;
  }

  /*
   * TODO 2: add ids to the generated students here
   *
   * TODO 3: add at least 10 notes to each student
   */
  private void generateSomeStudents() {
    for (var i = 0; i < 26; i++) {
      final var student = new Student();
      student.setFirstname(String.format("Student %d", i + 1));
      student.setLastname(String.format("Unknown %d", i + 1));
      student.setUsername(String.format("student%d@condingburgas.org", i + 1));
      students.add(student);
    }
  }
}