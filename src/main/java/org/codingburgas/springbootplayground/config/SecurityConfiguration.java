package org.codingburgas.springbootplayground.config;

import org.codingburgas.springbootplayground.security.PlaygroundAuthenticationProvider;
import org.codingburgas.springbootplayground.security.repository.UserRoleRepository;
import org.codingburgas.springbootplayground.students.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

  /**
   * Defines the security of the application resources
   * <p>
   * The rules will be processed by their definition. Please put specific rules first, and more general ones
   * at the end, since if the general rule matches, it will be executed and the processing will terminate.
   *
   * @param httpSecurity
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationProvider authenticationProvider) throws Exception {
    httpSecurity.authorizeHttpRequests(auth ->
        auth.requestMatchers("/login", "/logout", "/", "/students").permitAll()
            .requestMatchers("/students/create").hasAuthority("ROLE_ADMIN")
            .requestMatchers("/notes").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable);
    httpSecurity.authenticationProvider(authenticationProvider);
    return httpSecurity.build();
  }

  /**
   * Disables security for static content
   * <p>
   * This can also be done in the filter chain, but if placed there, the rules WILL BE PROCESSED
   *
   * @return
   */
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web -> web.ignoring()
        .requestMatchers("/css/**")
        .requestMatchers("/js/**")
        .requestMatchers("/images/**")
        .requestMatchers("/h2-console")
        .requestMatchers("/h2-console/**")
    );
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(StudentService studentService, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
    return new PlaygroundAuthenticationProvider(studentService, userRoleRepository, passwordEncoder);
  }

  //@Bean
  public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails user = User.withUsername("user")
        .password(passwordEncoder.encode("user"))
        .roles("USER").build();
    UserDetails admin = User.withUsername("admin")
        .password(passwordEncoder.encode("admin"))
        .roles("ADMIN").build();
    return new InMemoryUserDetailsManager(user, admin);
  }
}
