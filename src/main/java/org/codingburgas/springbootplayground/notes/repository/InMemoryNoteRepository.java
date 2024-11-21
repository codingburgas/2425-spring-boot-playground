package org.codingburgas.springbootplayground.notes.repository;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("default")
public class InMemoryNoteRepository implements NoteRepository {

  private final Map<Long, List<Note>> studentNotes = new HashMap<>();

  @Override
  public List<Note> getNotes() {
    return studentNotes.values().stream().flatMap(List::stream).toList();
  }

  @Override
  public List<Note> getNotesForStudent(Long id) {
    return studentNotes.get(id);
  }

  @Override
  public void addNoteForStudent(Note note, Long studentId) {
    if (studentNotes.containsKey(studentId)) {
      studentNotes.get(studentId).add(note);
    } else {
      var notes = new ArrayList<Note>();
      notes.add(note);
      studentNotes.put(studentId, notes);
    }
  }

  @Override
  public double getNotesAverage() {
    var optAvg = getNotes().stream().mapToDouble(note -> note.getValue().doubleValue()).average();
    if (optAvg.isPresent()) {
      return optAvg.getAsDouble();
    }
    return 0.0;
  }

  @Override
  public double getNotesAverageForStudent(Long id) {
    return 0;
  }

  @Override
  public long getTotalNoteCount() {
    return getNotes().size();
  }

  @Override
  public Long getBestStudentIdByNoteAverage() {
    return Collections.max(studentNotes.entrySet(), (entry1, entry2) -> Double.compare(
        entry1.getValue().stream().mapToDouble(note -> note.getValue().doubleValue()).average().getAsDouble(),
        entry2.getValue().stream().mapToDouble(note -> note.getValue().doubleValue()).average().getAsDouble()
    )).getKey();
  }

  @Override
  public Long getWorstStudentIdByNoteAverage() {
    // TODO: Implement this method
    return null;
  }

  @Override
  public Long getStudentIdWithMostBestNotes() {
    // TODO: Implement this method
    return null;
  }

  @Override
  public Long getStudentIdWithMostWorstNotes() {
    // TODO: Implement this method
    return null;
  }
}
