package com.example.miniproject.service.impl;

import com.example.miniproject.DTO.SubjectDTO;
import com.example.miniproject.converter.SubjectConverter;
import com.example.miniproject.model.Score;
import com.example.miniproject.model.Subject;
import com.example.miniproject.repository.SubjectRepository;
import com.example.miniproject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectConverter subjectConverter;

    @Override
    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(subjectConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDTO getSubjectById(String subjectId) {
        try {
            Subject subject = subjectRepository.getById(subjectId);
            return subjectConverter.toDTO(subject);
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }

    @Override
    public boolean createSubject(SubjectDTO subjectDTO) {
        // Kiểm tra xem môn học đã tồn tại hay chưa
        if (!subjectRepository.existsById(subjectDTO.getId())) {
            subjectRepository.save(subjectConverter.toEntity(subjectDTO));
            return true; // Trả về true khi tạo mới thành công
        }
        return false;
    }

    @Override
    public boolean updateSubject(String subjectId, SubjectDTO updatedSubject) {
        try {
            Subject existingSubject = subjectRepository.getById(subjectId);
            // Cập nhật thông tin môn học từ DTO
            existingSubject.setName(updatedSubject.getName());
            // Lưu môn học đã cập nhật vào cơ sở dữ liệu
            subjectRepository.save(existingSubject);
            return true;
        } catch (EntityNotFoundException ex) {
            return false;
        }
    }

    @Override
    public boolean deleteSubject(String subjectId) {
        try {
            Subject existingSubject = subjectRepository.getById(subjectId);
            // Kiểm tra xem môn học có điểm số nào đang liên kết không
            List<Score> scores = existingSubject.getScores();
            if (scores != null && !scores.isEmpty()) {
                //Không cho phép xóa nếu có điểm số liên kết
                return false;
            }
            // Xóa môn học
            subjectRepository.delete(existingSubject);
            return true; // Trả về true khi xóa thành công
        } catch (EntityNotFoundException ex) {
            return false;
        }
    }
}
