package com.example.miniproject.service;

import com.example.miniproject.DTO.StudentDTO;
import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO searchStudent(String studentId);
    boolean createStudent(StudentDTO studentDTO);
    boolean updateStudent(String studentId, StudentDTO updatedStudent);
    boolean deleteStudent(String studentId);
}
