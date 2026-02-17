package com.lms.libraryManagement.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lms.libraryManagement.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

    @Column(name = "mobile" , nullable = false , unique = true)
    private String mobile;

    @Column(name = "dept" , nullable = false)
    private String dept;

    @Column(name = "sem" , nullable = false)
    private String sem;

    @Column(name = "gender" , nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "address" , nullable = false)
    private String address;

    @Column(name = "dob" , nullable = false)
    private Date dob;

    @OneToOne(mappedBy = "student" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private Card card;

}
