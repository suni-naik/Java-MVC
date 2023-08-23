package com.niveus.service;

import java.util.List;
import java.util.Optional;

import com.niveus.model.Students;

public interface Studentservice {
    Students createStudent(Students student);
    List<Students> getAllStudents();
	Students updateStudent(Students student);
	void deleteStudent(Integer id);
	Optional<Students> getStudentById(Integer id);
}
