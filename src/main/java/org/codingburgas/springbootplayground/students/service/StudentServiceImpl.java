package org.codingburgas.springbootplayground.students.service;

import org.codingburgas.springbootplayground.notes.repository.NoteRepository;
import org.codingburgas.springbootplayground.students.model.Student;
import org.codingburgas.springbootplayground.students.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;


@Service
public class StudentServiceImpl implements StudentService {

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
    return null;
  }

  @Override
  public Student getStudentById(Long id) {
    final var student = studentRepository.getStudentById(id);
    if (student != null) {
      student.setNotes(noteRepository.getNotesForStudent(id));
    }
    return student;
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
}