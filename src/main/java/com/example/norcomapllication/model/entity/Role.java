package com.example.norcomapllication.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    public RoleEnumClass roleEnumClass;
    public User user;
    public Admin admin;

    public Role() {
    }
    @Enumerated(EnumType.STRING)
    public RoleEnumClass getRoleEnumClass() {
        return roleEnumClass;
    }

    public void setRoleEnumClass(RoleEnumClass roleEnumClass) {
        this.roleEnumClass = roleEnumClass;
    }
    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @OneToOne
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
