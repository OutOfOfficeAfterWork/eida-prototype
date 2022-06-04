package com.example.springapp.auth;

import com.example.springapp.application.StudentService;
import com.example.springapp.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginComponent implements UserDetailsService {
    private final StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String studentCode) throws UsernameNotFoundException {
        Student student = studentService.mustFind(studentCode);
        return new StudentUser(student);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }
}
