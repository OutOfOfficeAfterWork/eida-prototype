package com.example.springapp.auth;

import com.example.springapp.domain.Student;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

public class StudentUser extends User {
    public StudentUser(Student student) {
        super(student.getName(),
            student.getBirthDate(),
            Set.of());
    }
}
