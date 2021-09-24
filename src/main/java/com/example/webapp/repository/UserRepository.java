package com.example.webapp.repository;

import com.example.webapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByMainNumber(String main_number);

    List<User> findByOrderByFirstNameAsc();

    List<User> findByOrderByFirstNameDesc();

    List<User> findByOrderByLastNameAsc();

    List<User> findByOrderByLastNameDesc();
}
