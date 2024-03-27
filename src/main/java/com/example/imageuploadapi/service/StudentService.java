package com.example.imageuploadapi.service;

import com.example.imageuploadapi.controller.request.StudentRequest;
import com.example.imageuploadapi.controller.response.StudentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface StudentService {
    StudentResponse create(StudentRequest request,MultipartFile file) throws IOException;

    List<StudentResponse> getAll();

    StudentResponse getSpecific(Long id);
}
