package com.example.miniproject.converter;

import com.example.miniproject.DTO.SubjectDTO;
import com.example.miniproject.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter {

    public Subject toEntity(SubjectDTO subjectDTO){
        if(subjectDTO!=null){

            Subject subject = new Subject();
            subject.setId(subjectDTO.getId());
            subject.setName(subjectDTO.getName());
            return subject;
        }
        return null;
    }

    public SubjectDTO toDTO(Subject subject){
        if(subject!=null){

            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setId(subject.getId());
            subjectDTO.setName(subject.getName());
            return subjectDTO;
        }
        return null;
    }
}
