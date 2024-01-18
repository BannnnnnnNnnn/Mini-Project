package com.example.miniproject.converter;

import com.example.miniproject.DTO.ScoreDTO;
import com.example.miniproject.model.Score;
import com.example.miniproject.repository.StudentRepository;
import com.example.miniproject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreConverter {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    public Score toEntity(ScoreDTO scoreDTO){
        if(scoreDTO!=null){
            Score score = new Score();
            score.setId(scoreDTO.getScoreId());
            score.setScore(scoreDTO.getScore());
            score.setStudent(studentRepository.getById(scoreDTO.getStudentId()));
            score.setSubject(subjectRepository.getById(scoreDTO.getSubjectId()));
            return score;
        }
        return null;
    }

    public ScoreDTO toDTO(Score score){
        if(score!=null){
            ScoreDTO scoreDTO = new ScoreDTO();
            scoreDTO.setScoreId(score.getId());
            scoreDTO.setScore(score.getScore());
            scoreDTO.setStudentId(score.getStudent().getId());
            scoreDTO.setSubjectId(score.getSubject().getId());
            return scoreDTO;
        }
        return null;
    }
}
