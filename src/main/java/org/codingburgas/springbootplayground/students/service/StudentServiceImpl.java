package org.codingburgas.springbootplayground.students.service;

import org.codingburgas.springbootplayground.notes.repository.NoteRepository;
import org.codingburgas.springbootplayground.students.model.Student;
import org.codingburgas.springbootplayground.students.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;


@Service
public class StudentServiceImpl implements StudentService, UserDetailsService {

  private static final Logger LOGGER = Logger.getLogger(StudentServiceImpl.class.getName());

  private final StudentRepository studentRepository;
  private final NoteRepository noteRepository;

  public StudentServiceImpl(StudentRepository studentRepository, NoteRepository noteRepository) {
    this.studentRepository = studentRepository;
    this.noteRepository = noteRepository;
  }

  @Override
  public List<Student> getStudents() {
    return studentRepository.getStudents();
  }

  @Override
  public Student getStudentByUsername(String username) {
    return studentRepository.getStudentByUsername(username);
  }

  @Override
  public Student getStudentById(Long id) {
    return studentRepository.getStudentById(id);
  }

  @Override
  public void addStudent(Student student) {
    studentRepository.addStudent(student);
  }

  @Override
  public Student getBestStudent() {
    return studentRepository.getStudentById(noteRepository.getBestStudentIdByNoteAverage());
  }

  @Override
  public Student getWorstStudent() {
    // TODO: implement method
    return null;
  }

  @Override
  public Student getStudentWithMostBestNotes() {
    // TODO: implement method
    return null;
  }

  @Override
  public Student getStudentWithMostWorstNotes() {
    // TODO: implement method
    return null;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    var student = this.studentRepository.getStudentByUsername(username);
    if (student == null) {
      throw new UsernameNotFoundException("User not found!");
    }

    return getStudentByUsername(username);
  }
}