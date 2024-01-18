package com.example.miniproject.repository;

import com.example.miniproject.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SubjectRepository extends JpaRepository<Subject, String> {
}
