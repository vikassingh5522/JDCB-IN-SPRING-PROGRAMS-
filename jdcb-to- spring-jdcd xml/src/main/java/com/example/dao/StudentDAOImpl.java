package com.example.dao;

import com.example.model.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO student(name, email) VALUES(?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getEmail());
        System.out.println("✅ Student added.");
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE student SET name=?, email=? WHERE id=?";
        jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getId());
        System.out.println("✅ Student updated.");
    }

    @Override
    public void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id=?";
        jdbcTemplate.update(sql, id);
        System.out.println("✅ Student deleted.");
    }

    @Override
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM student WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }
}