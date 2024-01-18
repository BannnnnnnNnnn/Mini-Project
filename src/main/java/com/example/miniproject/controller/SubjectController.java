package com.example.miniproject.controller;

import com.example.miniproject.DTO.SubjectDTO;
import com.example.miniproject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/getall")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/get/{subjectId}")
    public ResponseEntity<?> getSubjectById(@PathVariable String subjectId) {
        SubjectDTO subjectDTO = subjectService.getSubjectById(subjectId);
        if (subjectDTO != null) {
            return ResponseEntity.ok(subjectDTO);
        } else {
            return ResponseEntity.badRequest().body("Subject not found with ID: " + subjectId);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSubject(@RequestBody SubjectDTO subjectDTO) {
        if (subjectService.createSubject(subjectDTO)) {
            return ResponseEntity.ok(subjectDTO);
        } else {
            return ResponseEntity.badRequest().body("Subject already exists with ID: " + subjectDTO.getId());
        }
    }

    @PutMapping("/update/{subjectId}")
    public ResponseEntity<?> updateSubject(@PathVariable String subjectId, @RequestBody SubjectDTO updatedSubject) {
        if (subjectService.updateSubject(subjectId, updatedSubject)) {
            updatedSubject.setId(subjectId);
            return ResponseEntity.ok(updatedSubject);
        } else {
            return ResponseEntity.badRequest().body("Subject not found with ID: " + subjectId);
        }
    }

    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable String subjectId) {
        if (subjectService.deleteSubject(subjectId)) {
            return ResponseEntity.ok("Subject deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Subject not found with ID: " + subjectId);
        }
    }
}
