package com.example.miniproject.service.impl;

import com.example.miniproject.DTO.ScoreDTO;
import com.example.miniproject.converter.ScoreConverter;
import com.example.miniproject.model.Score;
import com.example.miniproject.repository.ScoreRepository;
import com.example.miniproject.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreImpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private ScoreConverter scoreConverter;

    @Override
    public List<ScoreDTO> getAllScores() {
        List<Score> scores = scoreRepository.findAll();
        return scores.stream()
                .map(scoreConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScoreDTO> getScoresByStudentId(String studentId) {
        List<Score> scores = scoreRepository.findByStudentId(studentId);
        return scores.stream()
                .map(scoreConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScoreDTO> getScoresBySubjectId(String subjectId) {
        List<Score> scores = scoreRepository.findBySubjectId(subjectId);
        return scores.stream()
                .map(scoreConverter::toDTO)
                .collect(Collectors.toList());    }

    @Override
    public boolean createOrUpdateScore(ScoreDTO scoreDTO) {
        try {
            // Kiểm tra xem đã tồn tại điểm cho sinh viên và môn học hay chưa
            Score existingScore = scoreRepository.findByStudentIdAndSubjectId(scoreDTO.getStudentId(), scoreDTO.getSubjectId());
            if (existingScore == null) {
                // Nếu chưa tồn tại, tạo mới điểm
                scoreRepository.save(scoreConverter.toEntity(scoreDTO));
            } else {
                // Nếu đã tồn tại, cập nhật điểm mới
                existingScore.setScore(scoreDTO.getScore());
                scoreRepository.save(existingScore);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteScore(String studentId, String subjectId) {
        try {
            // Kiểm tra xem đã tồn tại điểm cho sinh viên và môn học hay chưa
            Score existingScore = scoreRepository.findByStudentIdAndSubjectId(studentId, subjectId);
            if (existingScore != null) {
                // Nếu tồn tại, xóa điểm
                scoreRepository.delete(existingScore);
                return true;
            } else {
                // Nếu không tồn tại, thông báo rằng không có điểm để xóa
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
