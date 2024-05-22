package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper studentMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        StudentDto studentDto = new StudentDto("John", "Doe", "john@example.com", 1);
        Student student = new Student("John", "Doe", "john@example.com", 20);
        Student savedStudent = new Student("John", "Doe", "john@example.com", 20);
        savedStudent.setId(1);

        when(studentMapper.toStudent(studentDto)).thenReturn(student);
        when(repository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent)).
                thenReturn(new StudentResponseDto("John", "Doe", "john@example.com"));


        StudentResponseDto studentResponseDto = studentService.saveStudent(studentDto);
        assertEquals(studentDto.firstname(), studentResponseDto.firstname());
        assertEquals(studentDto.lastname(), studentResponseDto.lastname());
        assertEquals(studentDto.email(), studentResponseDto.email());


        verify(studentMapper, times(1)).toStudent(studentDto);
        verify(repository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void should_return_all_student() {
        // given
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("John", "Doe", "john@example.com", 20));

        // mock the calls
        when(repository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class))).
                thenReturn(new StudentResponseDto("John", "Doe", "john@example.com"));

        // when
        List<StudentResponseDto> studentResponseDtos = studentService.findAllStudent();
        assertEquals(students.size(), studentResponseDtos.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_Id() {
        // given
        Integer studentId = 1;
        Student student = new Student("John", "Doe", "john@example.com", 20);

        // mock the call
        when(repository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(student)).thenReturn(new StudentResponseDto("John", "Doe", "john@example.com"));

        // when
        StudentResponseDto studentResponseDto = studentService.findStudentById(studentId);

        //then
        assertEquals(student.getFirstName(), studentResponseDto.firstname());
        assertEquals(student.getLastName(), studentResponseDto.lastname());
        assertEquals(student.getEmail(), studentResponseDto.email());

        verify(repository, times(1)).findById(studentId);

    }

    @Test
    public void should_return_student_by_name() {
        // given
        String studentName = "John";
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("John", "Doe", "john@example.com", 20));

        // mock sthe calls
        when(repository.findAllByFirstNameContaining(studentName)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class))).
                thenReturn(new StudentResponseDto("John", "Doe", "john@example.com"));

        // when

        var responseDto = studentService.findStudentByName(studentName);

        assertEquals(students.size(), responseDto.size());
    }
}