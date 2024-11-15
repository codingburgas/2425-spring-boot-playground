package org.codingburgas.springbootplayground.notes.service;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.NotesOverviewInfo;
import org.codingburgas.springbootplayground.notes.model.Subject;

import java.math.BigDecimal;
import java.util.List;

public interface NoteService {

  /**
   * Get list of all notes
   * <p>
   * If subject or value are set, use them for filtering, otherwise return all notes
   *
   * @param subject - return only notes for the provided subject
   * @param value - return only notes with the provided value
   * @return list of notes
   */
  List<Note> getNotes(Subject subject, BigDecimal value);

  /**
   * Returns a notes overview
   *
   * @return overview info object
   */
  NotesOverviewInfo getOverviewInfo();

}