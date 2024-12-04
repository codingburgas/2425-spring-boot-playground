package org.codingburgas.springbootplayground.students.repository;

import org.codingburgas.springbootplayground.students.model.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Repository
@Profile("h2")
public class JdbcStudentRepository implements StudentRepository, RowMapper<Student> {

  private static final Logger LOGGER = Logger.getLogger(JdbcStudentRepository.class.getName());

  private static final String SQL_GET_ALL_STUDENTS = "SELECT * FROM student";
  private static final String SQL_INSERT_STUDENT =
      "INSERT INTO student(firstname, lastname, birthdate, username, password, role) VALUES (?, ?, ?, ?, ?, ?)";
  private static final String SQL_GET_STUDENT_BY_ID = "SELECT * FROM student WHERE id = ?";

  private static final String SQL_GET_STUDENT_BY_USERNAME = "SELECT * FROM student WHERE username = ? ";


  private final JdbcTemplate jdbcTemplate;
  private final DataSource dataSource;

  private final PasswordEncoder passwordEncoder;

  public JdbcStudentRepository(JdbcTemplate jdbcTemplate, DataSource dataSource, PasswordEncoder passwordEncoder) {
    this.jdbcTemplate = jdbcTemplate;
    this.dataSource = dataSource;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public List<Student> getStudents() {
    return jdbcTemplate.query(SQL_GET_ALL_STUDENTS, this);
  }

  @Override
  public Student getStudentByUsername(String username) {
    return jdbcTemplate.queryForObject(SQL_GET_STUDENT_BY_USERNAME, this, username);
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
          student.getUsername(),
          student.getPassword(),
          student.getRole()
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
    student.setPassword(rs.getString(6));
    student.setRole(rs.getString(7));
    return student;
  }
}
