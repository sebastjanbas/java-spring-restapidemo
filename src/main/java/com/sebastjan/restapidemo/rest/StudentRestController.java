package com.sebastjan.restapidemo.rest;

import com.sebastjan.restapidemo.entity.Student;
import com.sebastjan.restapidemo.error.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    // define @PostConstruct to load the student data ... only once!
    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();

        students.add(new Student());
        students.add(new Student("Marco", "Polo"));
        students.add(new Student("John", "Snow"));
        students.add(new Student("Van", "Buren"));
    }

    // define endpoint for "/students" - return list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    // define endpoint or "/student/{studentId}" - return student by index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if (studentId < 0 || studentId >= students.size()) {
           throw new StudentNotFoundException("Student not found - " + studentId);
        }
        return students.get(studentId);
    }

    // previously handled exceptions
}
