package com.lms.libraryManagement.requestDTO;

import com.lms.libraryManagement.enums.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class StudentRequestDTO {
    private String name;
    private String email;
    private String mobile;
    private String dept;
    private String sem;
    private Gender gender;
    private String address;
    private Date dob;
}
