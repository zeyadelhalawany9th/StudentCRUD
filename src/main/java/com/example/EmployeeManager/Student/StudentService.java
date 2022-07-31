package com.example.EmployeeManager.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.nio.channels.IllegalChannelGroupException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService
{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student)
    {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent())
        {
            throw new IllegalStateException("Email is taken by another student");
        }

        studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId)
    {
        boolean studentExists = studentRepository.existsById(studentId);

        if(!studentExists)
        {
            throw new IllegalStateException("Student with id: " + studentId +" is not found");
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudentById(Long studentId, String name, String email)
    {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id: "+studentId+" is not found"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name))
        {
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email))
        {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if(studentOptional.isPresent())
            {
                throw new IllegalStateException("Email is taken by another student");
            }

            student.setEmail(email);
        }


    }
}
