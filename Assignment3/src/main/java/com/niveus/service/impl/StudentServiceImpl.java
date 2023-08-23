package com.niveus.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niveus.model.Students;
import com.niveus.repository.StudentsRepository;
import com.niveus.service.Studentservice;

@Service	
public class StudentServiceImpl implements Studentservice {

    @Autowired
    private StudentsRepository studentsRepository;

    @Override
    public Students createStudent(Students student) {
    	
        return studentsRepository.save(student);
    }

    @Override
    public List<Students> getAllStudents() {
        return studentsRepository.findAll();
    }
	
    @Override
    public Students updateStudent(Students student) {
        return studentsRepository.save(student);
    }
    
    @Override
    public void deleteStudent(Integer id) {
        studentsRepository.deleteById(id);
    }

    @Override
    public Optional<Students> getStudentById(Integer id) {
        return studentsRepository.findById(id);
    }
}