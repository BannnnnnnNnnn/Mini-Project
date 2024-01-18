package com.example.miniproject.service;

import com.example.miniproject.DTO.SubjectDTO;

import java.util.List;

public interface SubjectService {
    List<SubjectDTO> getAllSubjects();

    SubjectDTO getSubjectById(String subjectId);

    boolean createSubject(SubjectDTO subjectDTO);

    boolean updateSubject(String subjectId, SubjectDTO updatedSubject);

    boolean deleteSubject(String subjectId);

}
