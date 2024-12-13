package org.codingburgas.springbootplayground.security.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class User implements Authentication {
  private String username;
  private String password;

  private List<String> roles;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
  }

  @Override
  public Object getCredentials() {
    return this.password;
  }

  @Override
  public Object getDetails() {
    return this;
  }

  @Override
  public Object getPrincipal() {
    return this;
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
    return username;
  }
}
