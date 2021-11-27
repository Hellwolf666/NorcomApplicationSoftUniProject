package com.example.norcomapllication.model.entity;

import com.example.norcomapllication.model.entity.enums.RoleEnumClass;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    public RoleEnumClass role;
    public Set<User> users;

    public Role() {
    }

    @Enumerated(EnumType.STRING)
    public RoleEnumClass getRole() {
        return role;
    }

    public Role setRole(RoleEnumClass roleEnumClass) {
        this.role = roleEnumClass;
        return this;
    }
    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}