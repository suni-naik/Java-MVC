package com.niveus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niveus.DTO.StudentsDto;
import com.niveus.bm.StudentBusinessManager;
import com.niveus.model.Students;

@RestController
public class StudentController {
 
    @Autowired
    StudentBusinessManager studentBusinessManager;	

    @PostMapping("/createStudent")
    public ResponseEntity<Students> createUser(@RequestBody StudentsDto student) {
        Students createdStudent = studentBusinessManager.createUser(student);
        if (createdStudent != null) {
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentsDto>> getAllStudents() {
    	 List<StudentsDto> allStudents =  studentBusinessManager.getAllStudents();
         return ResponseEntity.ok(allStudents);
    }

    
    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<StudentsDto> updateStudent(@PathVariable Integer id, @RequestBody StudentsDto updatedStudent) {
        StudentsDto updated = studentBusinessManager.updateStudent(id, updatedStudent);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        boolean deleted = studentBusinessManager.deleteStudent(id);
        
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
