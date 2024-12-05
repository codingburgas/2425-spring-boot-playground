package org.codingburgas.springbootplayground.security;

import org.codingburgas.springbootplayground.students.service.StudentService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * StudentAuthenticationProvider
 * <p>
 * Provider that authenticated students based on their credentials, stored in the database.
 */
public class StudentAuthenticationProvider implements AuthenticationProvider {

  private final StudentService studentService;
  private final PasswordEncoder passwordEncoder;

  public StudentAuthenticationProvider(StudentService studentService, PasswordEncoder passwordEncoder) {
    this.studentService = studentService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    var username = authentication.getName();
    var password = authentication.getCredentials().toString();

    // check if the username exists
    var student = studentService.getStudentByUsername(username);
    if (student == null) {
      throw new UsernameNotFoundException("User not found");
    }

    // check the password
    if (!passwordEncoder.matches(password, student.getPassword())) {
      throw new UsernameNotFoundException("Wrong credentials");
    }

    return new StudentAuthentication(student);
  }

  /**
   * Which classes are supported by the provider
   *
   * @param authentication
   * @return
   */
  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
  }
}
