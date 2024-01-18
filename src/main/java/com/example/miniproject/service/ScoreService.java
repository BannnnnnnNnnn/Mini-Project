package com.example.miniproject.service;

import com.example.miniproject.DTO.ScoreDTO;

import java.util.List;

public interface ScoreService {
    List<ScoreDTO> getAllScores();
    List<ScoreDTO> getScoresByStudentId(String studentId);
    List<ScoreDTO> getScoresBySubjectId(String subjectId);
    boolean createOrUpdateScore(ScoreDTO scoreDTO);
    boolean deleteScore(String studentId, String subjectId);
}
