package com.example.imageuploadapi.controller.response;

import lombok.Data;

@Data
public class StudentImageResponse  extends StudentResponse{
    private StudentResponse studentResponse;
    private byte[] imageBytes;
    private String contentType;
}