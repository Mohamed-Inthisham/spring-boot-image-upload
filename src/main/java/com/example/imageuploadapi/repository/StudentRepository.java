package com.example.imageuploadapi.repository;

import com.example.imageuploadapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
