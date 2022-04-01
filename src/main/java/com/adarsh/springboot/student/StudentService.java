package com.adarsh.springboot.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getOneStudent(Long id) {
        return repository.findById(id).get();
    }

    public void deleteStudentById(Long id) {
        boolean existsById = repository.existsById(id);
        if (!existsById){
            throw new IllegalStateException("student with id " + id + " does not exist" );
        }
        repository.deleteById(id);
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = repository.findByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        repository.save(student);
    }
     @Transactional
    public void updateStudent(Long studentId, String name, String email) {
         Student student = repository.findById(studentId).orElseThrow(() ->
                 new IllegalStateException("student with id " + studentId +
                 "does not exist"));
         if (name !=null && name.length()>0 && !Objects.equals(student.getName() , name)){
             student.setName(name);
         }
         if (email !=null && email.length()>0 && !Objects.equals(student.getEmail() , email)){
             Optional<Student> byEmail = repository.findByEmail(email);
             if (byEmail.isPresent()){
                 throw new IllegalStateException("email taken");
             }
             student.setEmail(email);
         }
     }

}
