package com.example.miniproject.controller;

import com.example.miniproject.DTO.ScoreDTO;
import com.example.miniproject.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/getall")
    public ResponseEntity<List<ScoreDTO>> getAllScores() {
        List<ScoreDTO> scores = scoreService.getAllScores();
        return ResponseEntity.ok(scores);
    }

    @GetMapping("/getbystudent/{studentId}")
    public ResponseEntity<List<ScoreDTO>> getScoresByStudentId(@PathVariable String studentId) {
        List<ScoreDTO> scores = scoreService.getScoresByStudentId(studentId);
        return ResponseEntity.ok(scores);
    }

    @GetMapping("/getbysubject/{subjectId}")
    public ResponseEntity<List<ScoreDTO>> getScoresBySubjectId(@PathVariable String subjectId) {
        List<ScoreDTO> scores = scoreService.getScoresBySubjectId(subjectId);
        return ResponseEntity.ok(scores);
    }

    @PostMapping("/createorupdate")
    public ResponseEntity<?> createOrUpdateScore(@RequestBody ScoreDTO scoreDTO) {
        if (scoreService.createOrUpdateScore(scoreDTO)) {
            return ResponseEntity.ok(scoreDTO);
        } else {
            return ResponseEntity.badRequest().body("Error creating/updating score");
        }
    }

    @DeleteMapping("/delete/{studentId}/{subjectId}")
    public ResponseEntity<?> deleteScore(@PathVariable String studentId, @PathVariable String subjectId) {
        if (scoreService.deleteScore(studentId, subjectId)) {
            return ResponseEntity.ok("Score deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("No score found to delete");
        }
    }
}
