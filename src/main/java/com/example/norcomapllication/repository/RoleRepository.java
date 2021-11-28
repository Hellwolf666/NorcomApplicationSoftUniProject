package com.example.norcomapllication.repository;

import com.example.norcomapllication.model.entity.Role;
import com.example.norcomapllication.model.entity.enums.RoleEnumClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRole(RoleEnumClass administrator);
}
