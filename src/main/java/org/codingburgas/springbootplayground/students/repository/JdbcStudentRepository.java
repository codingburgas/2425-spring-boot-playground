package org.codingburgas.springbootplayground.students.repository;

import org.codingburgas.springbootplayground.notes.repository.JdbcNoteRepository;
import org.codingburgas.springbootplayground.students.model.Student;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
@Profile("h2")
public class JdbcStudentRepository implements StudentRepository, RowMapper<Student> {

  private static final Logger LOGGER = Logger.getLogger(JdbcStudentRepository.class.getName());

  private static final String SQL_GET_ALL_STUDENTS = "SELECT * FROM student";
  private static final String SQL_INSERT_STUDENT =
      "INSERT INTO student(firstname, lastname, birthdate, username) VALUES (?, ?, ?, ?)";
  private static final String SQL_GET_STUDENT_BY_ID = "SELECT * FROM student WHERE id = ?";

  private final JdbcTemplate jdbcTemplate;
  private final DataSource dataSource;

  public JdbcStudentRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
    this.jdbcTemplate = jdbcTemplate;
    this.dataSource = dataSource;
  }

  @Override
  public List<Student> getStudents() {
    return jdbcTemplate.query(SQL_GET_ALL_STUDENTS, this);
  }

//  /**
//   * getStudents implementation only with DataSource and connection example
//   * <p>
//   * This implementation is only for demonstration. Notice that most of the
//   * code is used for setting up the connection and checking for exceptions.
//   * <p>
//   * jdbcTemplate can help a lot with that. See the implementation above!
//   *
//   *
//   * @param username
//   * @return
//   */
//  @Override
//  public List<Student> getStudents() {
//    List<Student> students = new ArrayList<>();
//    Connection connection = null;
//    PreparedStatement getAllStudentsStatement = null;
//    ResultSet resultSet = null;
//    try {
//      connection = dataSource.getConnection();
//      getAllStudentsStatement = connection.prepareStatement("SELECT * FROM student");
//      resultSet = getAllStudentsStatement.executeQuery();
//      while (resultSet.next()) {
//        Student student = new Student();
//        student.setId(resultSet.getLong(1));
//        student.setFirstname(resultSet.getString(2));
//        student.setLastname(resultSet.getString(3));
//        var dateString = resultSet.getString(4);
//        if (dateString != null) {
//          student.setBirthday(LocalDate.parse(resultSet.getString(4)));
//        }
//
//        student.setUsername(resultSet.getString(5));
//        students.add(student);
//      }
//    } catch (SQLException e) {
//      throw new RuntimeException(String.format("ERROR: %s, Code: %d, sql state: %s", e.getMessage(), e.getErrorCode(), e.getSQLState()));
//    } finally {
//      try {
//        if (connection != null) connection.close();
//        if (getAllStudentsStatement != null) getAllStudentsStatement.close();
//        if (resultSet != null) resultSet.close();
//      } catch (SQLException e) {
//        throw new RuntimeException("Cannot cleanup");
//      }
//    }
//    return students;
//  }

  @Override
  public Student getStudentByUsername(String username) {
    return null;
  }

  @Override
  public Student getStudentById(Long id) {
    return jdbcTemplate.queryForObject(SQL_GET_STUDENT_BY_ID, this, id);
  }

  @Override
  public void addStudent(Student student) {
    if (student != null) {
      jdbcTemplate.update(
          SQL_INSERT_STUDENT,
          student.getFirstname(),
          student.getLastname(),
          student.getBirthday(),
          student.getUsername()
      );
    }
  }

  @Override
  public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
    var student = new Student();
    student.setId(rs.getLong(1));
    student.setFirstname(rs.getString(2));
    student.setLastname(rs.getString(3));
    String birthday = rs.getString(4);
    if (birthday != null) {
      student.setBirthday(LocalDate.parse(birthday));
    }
    student.setUsername(rs.getString(5));
    return student;
  }
}
