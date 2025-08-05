package org.example.moviehub.model;

import jakarta.persistence.*;
import org.example.moviehub.enums.RoleType;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //constructors
    public Role(RoleType roleType) {
        this.roleType = roleType;
    }
    public Role() {}

    //getter and setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
