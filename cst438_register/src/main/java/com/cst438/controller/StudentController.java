package com.cst438.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://registerf-cst438.herokuapp.com/"})
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Add new student 
    @PostMapping("/student")
    public ResponseEntity<StudentDTO> addStudent(@RequestParam String email, @RequestParam String name) {
        Student existingStudent = studentRepository.findByEmail(email);
        if (existingStudent != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Student student = new Student();
        student.setEmail(email);
        student.setName(name);
        student.setOnHold(false); // set onHold to false by default
        studentRepository.save(student);

        StudentDTO studentDTO = new StudentDTO();
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    // student registration on hold
    @PutMapping("/student/{id}/hold")
    public ResponseEntity<String> putStudentOnHold(@PathVariable("id") int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
        student.setOnHold(true);
        studentRepository.save(student);
        return new ResponseEntity<>("Student registration put on hold", HttpStatus.OK);
    }

    //release the hold on student registration
    @PutMapping("/student/{id}/release")
    public ResponseEntity<String> releaseStudentHold(@PathVariable("id") int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
        student.setOnHold(false);
        studentRepository.save(student);
        return new ResponseEntity<>("Hold on student registration released", HttpStatus.OK);
    }

    // GET  student information
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        StudentDTO studentDTO = new StudentDTO();
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }
}