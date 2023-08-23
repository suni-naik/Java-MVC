package com.niveus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niveus.model.Students;

public interface StudentsRepository extends JpaRepository<Students, Integer> {

}
