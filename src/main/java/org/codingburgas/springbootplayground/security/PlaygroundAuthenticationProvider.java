package org.codingburgas.springbootplayground.security;

import org.codingburgas.springbootplayground.security.repository.UserRoleRepository;
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
public class PlaygroundAuthenticationProvider implements AuthenticationProvider {

  private final StudentService studentService;
  private final UserRoleRepository userRoleRepository;
  private final PasswordEncoder passwordEncoder;

  public PlaygroundAuthenticationProvider(StudentService studentService, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
    this.studentService = studentService;
    this.userRoleRepository = userRoleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    var username = authentication.getName();
    var password = authentication.getCredentials().toString();

    // check if the username exists
    var user = userRoleRepository.getUserByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found!");
    }

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new UsernameNotFoundException("Wrong credentials");
    }

    var student = studentService.getStudentByUsername(username);
    if (student == null) {
      // user is not a student!!! Do some actions here if you add additional classes like teacher/etc.
      return user;
    }

    return student;
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
