package com.example.imageuploadapi.controller.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponse {

    private Long id;
    private String name;
    private String address;
    private LocalDate dob;
    private String image;
}
