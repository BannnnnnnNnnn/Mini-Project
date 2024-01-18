package com.example.miniproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDTO {
    private int scoreId;
    private double score;
    private String studentId;
    private String subjectId;

}
