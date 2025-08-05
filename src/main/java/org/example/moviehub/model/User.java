package org.example.moviehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Access(AccessType.FIELD)
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String username;
    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    //constructors


    public User() {}

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    //getter and setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
