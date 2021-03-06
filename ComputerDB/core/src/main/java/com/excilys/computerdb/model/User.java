package com.excilys.computerdb.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  private String username;
  private String password;
  private boolean enabled;
  private Set<UserRole> userRole = new HashSet<UserRole>(0);

  /**
   * Creates a new User.
   */
  public User() {
  }

  /**
   * Creates a new User with the given arguments.
   * @param username : the user's name.
   * @param password : the user's password.
   * @param enabled : if the user's is enabled or not.
   */
  public User(String username, String password, boolean enabled) {
    this.username = username;
    this.password = password;
    this.enabled = enabled;
  }

  /**
   * Creates a new User with the given arguments.
   * @param username : the user's name.
   * @param password : the user's password.
   * @param enabled : if the user's is enabled or not.
   * @param userRole : the roles given to the user.
   */
  public User(String username, String password,
      boolean enabled, Set<UserRole> userRole) {
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.userRole = userRole;
  }

  @Id
  @Column(name = "username", unique = true,
      nullable = false, length = 45)
  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "password",
      nullable = false, length = 60)
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "enabled", nullable = false)
  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  public Set<UserRole> getUserRole() {
    return this.userRole;
  }

  public void setUserRole(Set<UserRole> userRole) {
    this.userRole = userRole;
  }

}