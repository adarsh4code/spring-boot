package com.adarsh.springboot.student;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping(path = "api/v1") @RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping("/students")
    public List<Student> getAllStudent() {
        return service.getAllStudents();

    }

    @GetMapping("{Id}")
    public Student getOneStudent(@PathVariable("Id") Long Id) {
        return service.getOneStudent(Id);
    }
    @DeleteMapping("{Id}")
    public String deleteStudentById(@PathVariable("Id") Long Id){
        service.deleteStudentById(Id);
        return "The student with id " + Id + " is successfully deleted" ;

    }
    @PostMapping()
    public void addNewStudent(@RequestBody Student student){
        service.addNewStudent(student);

    }
    @PutMapping("{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){

        service.updateStudent(studentId, name, email);
    }
}
