package com.example.dao;

import com.example.model.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private final DataSource dataSource;

    public StudentDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Student student) {
        String sql = "INSERT INTO student (name, email) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.executeUpdate();
            System.out.println("Student created successfully in DAO.");
        } catch (SQLException e) {
            System.err.println("Error creating student: " + e.getMessage());
            throw new RuntimeException("Failed to create student", e);
        }
    }

    @Override
    public Student read(int id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error reading student: " + e.getMessage());
            throw new RuntimeException("Failed to read student", e);
        }
        return null;
    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE student SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getId());
            pstmt.executeUpdate();
            System.out.println("Student updated successfully in DAO.");
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            throw new RuntimeException("Failed to update student", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM student WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Student deleted successfully in DAO.");
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            throw new RuntimeException("Failed to delete student", e);
        }
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving students: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve students", e);
        }
        return students;
    }
}