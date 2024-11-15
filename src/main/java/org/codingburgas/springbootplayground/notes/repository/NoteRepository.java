package org.codingburgas.springbootplayground.notes.repository;

import org.codingburgas.springbootplayground.notes.model.Note;

import java.util.List;

public interface NoteRepository {
  List<Note> getNotes();
  List<Note> getNotesForStudent(Long id);

  void addNoteForStudent(Note note, Long studentId);

  double getNotesAverage();

  double getNotesAverageForStudent(Long id);

  long getTotalNoteCount();

  Long getBestStudentIdByNoteAverage();

  Long getWorstStudentIdByNoteAverage();
}
