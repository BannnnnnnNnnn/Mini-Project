package com.example.miniproject.converter;

import com.example.miniproject.DTO.StudentDTO;
import com.example.miniproject.model.Student;
import com.example.miniproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentConverter {

    @Autowired
    AccountRepository accountRepository;

    public Student toEntity(StudentDTO studentDTO){
        if(studentDTO!=null){

            Student student = new Student();
            student.setAccount(accountRepository.getById(studentDTO.getUsername()));
            student.setId(studentDTO.getId());
            student.setName(studentDTO.getName());
            student.setDob(studentDTO.getDob());
            student.setGender(studentDTO.getGender());

            return student;
        }
        return null;
    }

    public StudentDTO toDTO(Student student){
        if(student!=null){

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setUsername((student.getAccount().getUsername()));
            studentDTO.setName(student.getName());
            studentDTO.setDob(student.getDob());
            studentDTO.setGender(student.getGender());

            return studentDTO;
        }
        return null;
    }
}
