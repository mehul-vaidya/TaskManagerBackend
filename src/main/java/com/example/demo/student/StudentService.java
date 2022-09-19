package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentid) {
        boolean exists = studentRepository.existsById(studentid);
        if(!exists){
            throw new IllegalStateException("student with id " +studentid +" does not exists");
        }
        studentRepository.deleteById(studentid);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException("student with id " +studentId +" does not exists"));
        if( name!= null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if( email!= null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                System.out.println(email);
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }

    @Transactional
    public void updateStudent_using_RequestBody(Long studentId, Student student_param) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException("student with id " +studentId +" does not exists"));
        if( student_param.getName()!= null && student_param.getName().length()>0 && !Objects.equals(student.getName(),student_param.getName())
                &&student_param.getEmail()!= null && student_param.getEmail().length()>0 && !Objects.equals(student.getEmail(),student_param.getEmail())){

            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student_param.getEmail());
            if(studentOptional.isPresent()){
                System.out.println(student_param.getEmail());
                throw new IllegalStateException("email taken");
            }
            student.setName(student_param.getName());
            student.setEmail(student_param.getEmail());
        }
    }
}


