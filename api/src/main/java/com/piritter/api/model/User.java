package com.piritter.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"), // does this ignore capitalization?
    })
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @JsonIgnore
  @NotBlank
  @Size(max = 120)
  private String password;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  // json ignore?
  @ElementCollection
  private Set<Long> following = new HashSet<>();

  @JsonIgnore
  @OneToMany
  private Set<Tweet> retweets = new HashSet<>();

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  // add a .equals
}
