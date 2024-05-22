package com.example.demo.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto studentDto = new StudentDto("John", "Doe", "john@example.com", 1);
        Student student = studentMapper.toStudent(studentDto);

        assertEquals(studentDto.firstname(), student.getFirstName());
        assertEquals(studentDto.lastname(), student.getLastName());
        assertEquals(studentDto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_NullPointerException_when_studentDto_is_null() {
        var exp = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
        assertEquals("The student dto should not be null", exp.getMessage());
    }


    @Test
    public void shouldMapStudentToStudentResponseDto() {
        Student student = new Student("John", "Doe", "john@example.com", 20);
        StudentResponseDto studentResponseDto = studentMapper.toStudentResponseDto(student);

        assertEquals(student.getFirstName(), studentResponseDto.firstname());
        assertEquals(student.getLastName(), studentResponseDto.lastname());
        assertEquals(student.getEmail(), studentResponseDto.email());
    }

}