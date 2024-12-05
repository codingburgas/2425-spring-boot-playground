package org.codingburgas.springbootplayground.security.repository;

import org.codingburgas.springbootplayground.security.model.Role;
import org.codingburgas.springbootplayground.security.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class JdbcUserRoleRepository implements UserRoleRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcUserRoleRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Long addRole(Role role) {
    var jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    var parameters = new HashMap<String, String>();
    parameters.put("title", role.getTitle());
    return (long) jdbcInsert
        .withTableName("`role`")
        .usingColumns("title")
        .usingGeneratedKeyColumns("id")
        .executeAndReturnKey(parameters);
  }

  @Override
  public List<Role> getRoles() {
    return null;
  }

  @Override
  public Role getRoleById(Long id) {
    return null;
  }

  @Override
  public Role getRoleByTitle(String title) {
    return jdbcTemplate.queryForObject("SELECT * FROM `role` WHERE title = ?", (rs, rowNum) -> {
      var role = new Role();
      role.setId(rs.getLong(1));
      role.setTitle(rs.getString(2));
      return role;
    }, title);
  }

  @Override
  public User getUserByUsername(String username) {
    var sql = """
      SELECT `user`.username, `user`.password, `role`.title FROM `user`
        JOIN user_role ON `user`.username = user_role.user_username
          JOIN `role` ON `user_role`.role_id = `role`.id
          WHERE `user`.username = ?
      """;
    return jdbcTemplate.query(sql, rs -> {
      var user = new User();
      user.setRoles(new ArrayList<>());
      while (rs.next()) {
        user.setUsername(rs.getString(1));
        user.setPassword(rs.getString(2));
        user.getRoles().add(rs.getString(3));
      }
      return user;
    }, username);
  }

  @Override
  public void addUser(User user) {
    jdbcTemplate.update("INSERT INTO `user`(username, password) VALUES(?, ?)", user.getUsername(), user.getPassword());

    if (user.getRoles() != null && !user.getRoles().isEmpty()) {
      for (String roleToAdd: user.getRoles()) {
        var role = getRoleByTitle(roleToAdd);
        if (role != null) {
          addRoleToUser(role, user.getUsername());
        }
      }
    }
  }

  @Override
  public void updateUserRoles(User user) {

  }

  @Override
  public void addRoleToUser(Role role, String username) {
    jdbcTemplate.update("INSERT INTO user_role(user_username, role_id) VALUES (?, ?)", username, role.getId());
  }
}
