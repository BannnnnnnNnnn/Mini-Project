package com.example.miniproject.service.impl;

import com.example.miniproject.DTO.AccountDTO;
import com.example.miniproject.DTO.StudentDTO;
import com.example.miniproject.converter.AccountConverter;
import com.example.miniproject.converter.StudentConverter;
import com.example.miniproject.model.Account;
import com.example.miniproject.model.Student;
import com.example.miniproject.repository.AccountRepository;
import com.example.miniproject.repository.StudentRepository;
import com.example.miniproject.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentConverter studentConverter;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountConverter accountConverter;

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO searchStudent(String studentId) {
        try {
            return studentConverter.toDTO(studentRepository.getById(studentId));
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }

    @Override
    public boolean createStudent(StudentDTO studentDTO) {
        // Kiểm tra xem sinh viên có tồn tại hay không
        if (!studentRepository.existsById(studentDTO.getId())) {
            studentDTO.setUsername(studentDTO.getId());
            try {
                AccountDTO accountDTO = new AccountDTO(studentDTO.getId(), studentDTO.getId(), true, "student");
                accountRepository.save(accountConverter.toEntity(accountDTO));
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            studentRepository.save(studentConverter.toEntity(studentDTO));
            return true; // Trả về true khi tạo mới thành công
        }
        return false;
    }

    @Override
    public boolean updateStudent(String studentId, StudentDTO updatedStudent) {
        if (studentRepository.existsById(studentId)) {
            updatedStudent.setId(studentId);
            if (updatedStudent.getUsername() == null) {
                updatedStudent.setUsername(studentId);
            }
            Student existingStudent = studentRepository.getById(studentId);
            BeanUtils.copyProperties(studentConverter.toEntity(updatedStudent), existingStudent, "id", "username");
            studentRepository.save(existingStudent);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudent(String studentId) {
        try {
            // Kiểm tra xem sinh viên đã tồn tại hay không
            Student existingStudent = studentRepository.getById(studentId);
            // Xóa sinh viên
            studentRepository.delete(existingStudent);
            //  Tìm và cập nhật trạng thái của tài khoản tương ứng
            Account account = accountRepository.findAccountByUsername(existingStudent.getAccount().getUsername());
            if (account != null) {
                account.setStatus(false); // Đặt trạng thái về false thay vì xóa tài khoản
                accountRepository.save(account);
            }
            return true; // Trả về true khi xóa thành công
        } catch (EntityNotFoundException ex) {
            return false; // Trả về false nếu không tìm thấy sinh viên cần xóa
        }
    }
}
