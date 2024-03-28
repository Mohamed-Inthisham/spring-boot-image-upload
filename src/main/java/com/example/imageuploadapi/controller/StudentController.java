package com.example.imageuploadapi.controller;

import com.example.imageuploadapi.controller.request.StudentRequest;
import com.example.imageuploadapi.controller.response.StudentResponse;
import com.example.imageuploadapi.model.Student;
import com.example.imageuploadapi.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/save")
    public StudentResponse saveStudent(@ModelAttribute StudentRequest request, @RequestParam("image") MultipartFile file) throws IOException {
        return studentService.create(request, file);
    }
    @GetMapping("/students")
    public List<StudentResponse> getAllStudents(){
       return studentService.getAll();
    }
    @GetMapping("/students/{id}")
    public StudentResponse specificStudent(@PathVariable ("id") Long id){
        return studentService.getSpecific(id);
    }
    @GetMapping("/students/image/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable ("id") Long id) throws IOException {

        return studentService.getImage(id);

    }
}
