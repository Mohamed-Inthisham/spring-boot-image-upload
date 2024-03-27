package com.example.imageuploadapi.controller.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class StudentRequest {
    private String name;
    private String address;
    private LocalDate dob;
    private MultipartFile image;
}
