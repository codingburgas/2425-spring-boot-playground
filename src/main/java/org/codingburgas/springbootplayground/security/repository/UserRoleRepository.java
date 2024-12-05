package org.codingburgas.springbootplayground.security.repository;

import org.codingburgas.springbootplayground.security.model.Role;
import org.codingburgas.springbootplayground.security.model.User;

import java.util.List;

public interface UserRoleRepository {
  User getUserByUsername(String username);
  void addUser(User user);

  void updateUserRoles(User user);

  Long addRole(Role role);
  List<Role> getRoles();

  Role getRoleById(Long id);

  Role getRoleByTitle(String title);

  void addRoleToUser(Role role, String username);

}
