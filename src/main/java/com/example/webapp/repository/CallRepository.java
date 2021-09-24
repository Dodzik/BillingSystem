package com.example.webapp.repository;

import com.example.webapp.entities.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call,Long> {

    List<Call> findCallsByMainNumber(String main_number);

    List<Call> findCallsByTargetNumber(String Target_number);

    List<Call> findByOrderByMainNumberAsc();

    List<Call> findByOrderByMainNumberDesc();

    List<Call> findByOrderByTargetNumberAsc();

    List<Call> findByOrderByTargetNumberDesc();

    List<Call> findByOrderByTalkTimeAsc();

    List<Call> findByOrderByTalkTimeDesc();

    List<Call> findByOrderByDateAsc();

    List<Call> findByOrderByDateDesc();
}