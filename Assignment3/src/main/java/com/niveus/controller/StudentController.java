package com.niveus.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niveus.DTO.StudentsDto;
import com.niveus.model.Students;
import com.niveus.service.Studentservice;

@RestController
public class StudentController {


    @Autowired
    private Studentservice studentService;

    @PostMapping("/createStudent")
    public ResponseEntity<Students> createUser(@RequestBody StudentsDto student) {
    	Students s= new Students();
    	s.setName(student.getName());
    	s.setAge(student.getAge());
        Students savedUser = studentService.createStudent(s);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentsDto>> getAllStudents() {
        List<Students> studentsList = studentService.getAllStudents();
        List<StudentsDto> studentsDtoList = studentsList.stream()
                .map(student -> new StudentsDto(student.getName(), student.getAge()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentsDtoList);
    }

    
    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<StudentsDto> updateStudent(@PathVariable Integer id, @RequestBody StudentsDto updatedStudent) {
        Optional<Students> optionalStudent = studentService.getStudentById(id);
        
        if (optionalStudent.isPresent()) {
            Students student = optionalStudent.get();
            student.setName(updatedStudent.getName());
            student.setAge(updatedStudent.getAge());
            Students savedStudent = studentService.updateStudent(student);
            return ResponseEntity.ok(new StudentsDto(savedStudent.getName(), savedStudent.getAge()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        Optional<Students> optionalStudent = studentService.getStudentById(id);
        
        if (optionalStudent.isPresent()) {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
