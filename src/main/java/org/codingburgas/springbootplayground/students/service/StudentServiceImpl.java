package org.codingburgas.springbootplayground.students.service;

import org.codingburgas.springbootplayground.students.model.Student;
import org.codingburgas.springbootplayground.students.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;


@Service
public class StudentServiceImpl implements StudentService {

  private static final Logger LOGGER = Logger.getLogger(StudentServiceImpl.class.getName());

  private final StudentRepository studentRepository;

  public StudentServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public List<Student> getStudents() {
    return studentRepository.getStudents();
  }

  @Override
  public Student getStudentByUsername(String username) {
    return null;
  }

  @Override
  public Student getStudentById(Integer id) {
    return studentRepository.getStudentById(id);
  }

  @Override
  public void addStudent(Student student) {
    studentRepository.addStudent(student);
  }
}