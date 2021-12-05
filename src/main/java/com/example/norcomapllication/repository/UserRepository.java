package com.example.norcomapllication.repository;

import com.example.norcomapllication.model.entity.User;
import com.example.norcomapllication.model.view.AdminUsersViewModel;
import com.example.norcomapllication.model.view.ProfileUserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameIgnoreCase(String username);

    @Query("select user from User user left join fetch user.roles")
    List<User> getAllUsersByFetch();
}
