package org.codingburgas.springbootplayground.security;

import org.codingburgas.springbootplayground.students.model.Student;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * StudentAuthentication
 * <p>
 * A wrapper class for the Student, which implements Authentication.
 * Used by the StudentAuthenticationProvider for the authentication process.
 * This is another demonstration of how to enable authentication. If you remember, when we
 * created a custom UserDetailsService, we just implemented the interface directly in the Student class.
 * If you don't want to change the model (domain) classes, you can just create a wrapper as here.
 * <p>
 * This class can directly be accessed from the security context when the user is authenticated.
 *
 */
public class StudentAuthentication implements Authentication {

  /**
   * Wrapped student object
   */
  private final Student student;

  public StudentAuthentication(Student student) {
    this.student = student;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return student.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
  }

  @Override
  public Object getCredentials() {
    return student.getPassword();
  }

  @Override
  public Object getDetails() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return student;
  }

  @Override
  public boolean isAuthenticated() {
    return true;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

  }

  @Override
  public String getName() {
    return student.getUsername();
  }
}
