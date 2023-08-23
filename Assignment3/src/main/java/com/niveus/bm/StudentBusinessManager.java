package com.niveus.bm;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;

import com.niveus.DTO.StudentsDto;
import com.niveus.model.Students;
import com.niveus.service.Studentservice;


@Component
public class StudentBusinessManager {
	
	
    @Autowired
    private Studentservice studentService; 
    
	public Students createUser( StudentsDto student) {
    	Students s= new Students();
    	s.setName(student.getName());
    	s.setAge(student.getAge());
        Students savedUser = studentService.createStudent(s);
        return savedUser;
    }
	
	public List<StudentsDto> getAllStudents() {
        List<Students> studentsList = studentService.getAllStudents();
        List<StudentsDto> studentsDtoList = studentsList.stream()
                .map(student -> new StudentsDto(student.getName(), student.getAge()))
                .collect(Collectors.toList());
       return studentsDtoList;
    }
	
	public StudentsDto updateStudent(Integer id, StudentsDto updatedStudent) {
	    Optional<Students> optionalStudent = studentService.getStudentById(id);
	    
	    try {
	        if (optionalStudent.isPresent()) {
	            Students student = optionalStudent.get();
	            student.setName(updatedStudent.getName());
	            student.setAge(updatedStudent.getAge());
	            Students savedStudent = studentService.updateStudent(student);
	            return new StudentsDto(savedStudent.getName(), savedStudent.getAge());
	        } else {
	            throw new NotFoundException();
	        }
	    } catch (NotFoundException e) {
	        e.printStackTrace();  
	        return new StudentsDto("Not Found", 0);  
	    }
	}
	
	public boolean deleteStudent(Integer id) {
	    Optional<Students> optionalStudent = studentService.getStudentById(id);
	    
	    try {
	        if (optionalStudent.isPresent()) {
	            studentService.deleteStudent(id);
	            return true;  
	        } else {
	            throw new NotFoundException();
	        }
	    } catch (NotFoundException e) {
	        e.printStackTrace();
	        return false;  
	    }
	}
}
