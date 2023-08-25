package com.niveus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.niveus.bm.StudentBusinessManager;
import com.niveus.dto.StudentsDto;
import com.niveus.model.Students;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
 class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentBusinessManager studentBusinessManager;

    @Test
     void testGetAllStudents() {
        List<StudentsDto> studentsDtoList = new ArrayList<>();
        studentsDtoList.add(new StudentsDto("John", 20));

        when(studentBusinessManager.getAllStudents()).thenReturn(studentsDtoList);

        ResponseEntity<List<StudentsDto>> response = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentsDtoList, response.getBody());
    }

    @Test
     void testCreateUser() {
        StudentsDto newStudent = new StudentsDto("Alice", 25);
        Students createdStudent = new Students();
        createdStudent.setId(1);
        createdStudent.setName(newStudent.getName());
        createdStudent.setAge(newStudent.getAge());

        when(studentBusinessManager.createUser(newStudent)).thenReturn(createdStudent);

        ResponseEntity<Students> response = studentController.createUser(newStudent);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdStudent, response.getBody());
    }

    @Test
     void testUpdateStudent() {
        Integer studentId = 1;
        StudentsDto updatedStudent = new StudentsDto("Updated Name", 30);
        StudentsDto updatedStudentResponse = new StudentsDto("Updated Name", 30);
        when(studentBusinessManager.updateStudent(studentId, updatedStudent)).thenReturn(updatedStudentResponse);

        ResponseEntity<StudentsDto> response = studentController.updateStudent(studentId, updatedStudent);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedStudentResponse, response.getBody());
    }

    @Test
     void testUpdateStudentNotFound() {
        Integer nonExistentStudentId = 999;
        StudentsDto updatedStudent = new StudentsDto("Updated Name", 30);
        when(studentBusinessManager.updateStudent(nonExistentStudentId, updatedStudent)).thenReturn(null);

        ResponseEntity<StudentsDto> response = studentController.updateStudent(nonExistentStudentId, updatedStudent);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
     void testDeleteStudent() {
        Integer studentId = 1;
        when(studentBusinessManager.deleteStudent(studentId)).thenReturn(true);

        ResponseEntity<Void> response = studentController.deleteStudent(studentId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
     void testDeleteStudentNotFound() {
        Integer nonExistentStudentId = 999;
        when(studentBusinessManager.deleteStudent(nonExistentStudentId)).thenReturn(false);

        ResponseEntity<Void> response = studentController.deleteStudent(nonExistentStudentId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
