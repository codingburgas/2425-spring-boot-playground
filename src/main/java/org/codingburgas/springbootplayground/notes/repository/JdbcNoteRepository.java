package org.codingburgas.springbootplayground.notes.repository;

import org.codingburgas.springbootplayground.notes.model.Note;
import org.codingburgas.springbootplayground.notes.model.Subject;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
@Profile("h2")
public class JdbcNoteRepository implements NoteRepository, RowMapper<Note> {

  private static final String SQL_GET_ALL_NOTES = "SELECT * FROM note";
  private static final String SQL_GET_ALL_NOTES_FOR_STUDENT = "SELECT * FROM note WHERE student_id = ?";

  private static final String SQL_GET_TOTAL_NOTES_COUNT = "SELECT COUNT(*) FROM note";

  private static final String SQL_GET_TOTAL_NOTES_AVERAGE = "SELECT AVG(note_value) FROM note";

  private static final String SQL_GET_TOTAL_NOTES_AVERAGE_FOR_STUDENT = "SELECT AVG(note_value) FROM note WHERE student_id = ?";
  private static final String SQL_GET_ALL_NOTE_AVERAGE = """
      SELECT
        student.*, AVG(note.note_value) AS avg
      FROM student
        INNER JOIN note ON student.id = note.student_id
      GROUP BY note.student_id;
      """;

  private static final String SQL_GET_BEST_STUDENT = """
      SELECT
         student.id
      FROM student
      INNER JOIN note ON student.id = note.student_id
      GROUP BY note.student_id
      ORDER BY AVG(note.note_value)
      DESC LIMIT 1;
     """;

  private static final String SQL_INSERT_NOTE_FOR_STUDENT = """
    INSERT INTO note
      (date, subject, note_value, student_id)\s
    VALUES\s
      (?, ?, ?, ?)""";

  private final JdbcTemplate jdbcTemplate;

  public JdbcNoteRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Note> getNotes() {
    return jdbcTemplate.query(SQL_GET_ALL_NOTES, this);
  }

  @Override
  public List<Note> getNotesForStudent(Long id) {
    return jdbcTemplate.query(SQL_GET_ALL_NOTES_FOR_STUDENT, this, id);
  }

  @Override
  public void addNoteForStudent(Note note, Long studentId) {
    jdbcTemplate.update(
        SQL_INSERT_NOTE_FOR_STUDENT,
        note.getDate() == null ? LocalDate.now() : note.getDate(),
        note.getSubject().name(),
        note.getValue(),
        studentId
    );
  }

  @Override
  public double getNotesAverage() {
    return jdbcTemplate.queryForObject(SQL_GET_TOTAL_NOTES_AVERAGE, Double.class);
  }

  @Override
  public double getNotesAverageForStudent(Long id) {
    return 0;
  }

  @Override
  public long getTotalNoteCount() {
    return jdbcTemplate.queryForObject(SQL_GET_TOTAL_NOTES_COUNT, Long.class);
  }

  @Override
  public Long getBestStudentIdByNoteAverage() {
    return jdbcTemplate.queryForObject(SQL_GET_BEST_STUDENT, Long.class);
  }

  @Override
  public Long getWorstStudentIdByNoteAverage() {
    // TODO: Implement this method
    return null;
  }

  @Override
  public Long getStudentIdWithMostBestNotes() {
    return null;
  }

  @Override
  public Long getStudentIdWithMostWorstNotes() {
    return null;
  }

  /**
   * Maps a ResultSet to a Note class
   * <p>
   * Can be used to transform jdbc results to instances of class Note
   *
   * @param rs
   * @param rowNum
   * @return
   * @throws SQLException
   */
  @Override
  public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
    var note = new Note();
    String date = rs.getString(2);
    if (date != null) {
      note.setDate(LocalDate.parse(date));
    }
    note.setSubject(Subject.valueOf(rs.getString(3)));
    note.setValue(rs.getBigDecimal(4));
    return note;
  }
}
