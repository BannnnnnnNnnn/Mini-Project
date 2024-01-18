package com.example.miniproject.repository;

import com.example.miniproject.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findByStudentIdAndSubjectId(String studentId, String subjectId);
    List<Score> findByStudentId(String studentId);
    List<Score> findBySubjectId(String subjectId);
}
