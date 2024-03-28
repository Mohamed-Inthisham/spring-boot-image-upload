package com.example.imageuploadapi.service.impl;

import com.example.imageuploadapi.controller.request.StudentRequest;
import com.example.imageuploadapi.controller.response.StudentResponse;
import com.example.imageuploadapi.model.Student;
import com.example.imageuploadapi.repository.StudentRepository;
import com.example.imageuploadapi.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/images";

    @Override
    public StudentResponse create(StudentRequest request, MultipartFile file) throws IOException {
        Student student = new Student();
        student.setName(request.getName());
        student.setAddress(request.getAddress());
        student.setDob(request.getDob());
        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        student.setImage(originalFilename);
        studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setAddress(student.getAddress());
        studentResponse.setDob(student.getDob());
        studentResponse.setImage(student.getImage());

        return studentResponse;
    }

    @Override
    public List<StudentResponse> getAll() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentResponse> studentResponseList = new ArrayList<>();
        for (Student student : studentList) {
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setId(student.getId());
            studentResponse.setName(student.getName());
            studentResponse.setAddress(student.getAddress());
            studentResponse.setDob(student.getDob());
            studentResponse.setImage(student.getImage());
            studentResponseList.add(studentResponse);
        }
        return studentResponseList;
    }

    @Override
    public StudentResponse getSpecific(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        StudentResponse studentResponse = new StudentResponse();
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentResponse.setName(student.getName());
            studentResponse.setAddress(student.getAddress());
            studentResponse.setDob(student.getDob());
            studentResponse.setImage(student.getImage());
            return studentResponse;
        }
        return null;
    }

    @Override
    public ResponseEntity<Resource> getImage(Long id) throws IOException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        StudentResponse studentResponse = new StudentResponse();
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentResponse.setId(student.getId());
            studentResponse.setName(student.getName());
            studentResponse.setAddress(student.getAddress());
            studentResponse.setDob(student.getDob());
            Path imagePath = Paths.get(uploadDirectory, student.getImage());
            Resource resource = new FileSystemResource(imagePath.toFile());
            String contentType = Files.probeContentType(imagePath);

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
        }
        return null;
    }
}
