package com.example.miniproject.controller;

import com.example.miniproject.DTO.StudentDTO;
import com.example.miniproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getall")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/search/{studentId}")
    public ResponseEntity<?> searchStudentById(@PathVariable String studentId) {
        StudentDTO studentDTO = studentService.searchStudent(studentId);
        if (studentDTO != null) {
            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.badRequest().body("Student not found with ID: " + studentId);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody StudentDTO studentDTO) {
        if (studentService.createStudent(studentDTO)) {
            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.badRequest().body("ERROR Nothing has not been added to the database!!");
        }
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable String studentId, @RequestBody StudentDTO updatedStudentDTO) {
        if (studentService.updateStudent(studentId, updatedStudentDTO)) {
            return ResponseEntity.ok("Student updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update student with ID: " + studentId);
        }
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable String studentId) {
        if (studentService.deleteStudent(studentId)) {
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete student with ID: " + studentId);
        }
    }
}
