package org.codingburgas.springbootplayground.students.service;

import org.codingburgas.springbootplayground.students.model.Student;

import java.util.List;

/**
 * Interface StudentService
 * <p>
 * Defines methods that retrieve or modify student data.
 */
public interface StudentService {

  /**
   * Returns all students
   *
   * @return
   */
  List<Student> getStudents();

  /**
   * Retrieves a user by username
   *
   * @param username
   * @return
   */
  Student getStudentByUsername(String username);

  /**
   * Retrieves a user by id
   *
   * @param id
   * @return
   */
  Student getStudentById(Long id);

  /**
   * Adds a new student
   * @param student
   */
  void addStudent(Student student);

  /**
   * Returns the best student
   * <p>
   * Best student is defined as the one with the maximal
   * average of all notes
   *
   * @return
   */
  Student getBestStudent();

  /**
   * Returns the worst student
   * <p>
   * Worst student is defined as the one with the minimal
   * average of all notes
   * @return
   */
  Student getWorstStudent();

  /**
   * Returns the student with the highest number of best notes (6.00)
   * @return
   */
  Student getStudentWithMostBestNotes();

  /**
   * Returns the student with the highest number of worst notes (2.00)
   * @return
   */
  Student getStudentWithMostWorstNotes();
}