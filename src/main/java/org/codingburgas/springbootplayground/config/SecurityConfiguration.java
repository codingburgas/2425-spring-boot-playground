package org.codingburgas.springbootplayground.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests(auth ->
        auth.requestMatchers("/login", "/logout", "/", "/students").permitAll()
            .requestMatchers("/students/create").hasAuthority("ADMIN")
            .requestMatchers("/notes").hasAnyAuthority("USER", "ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable);
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
    );
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
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
