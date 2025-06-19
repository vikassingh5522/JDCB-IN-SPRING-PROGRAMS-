package dao;

import model.Student;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private final JdbcTemplate jdbcTemplate;

    public StudentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Student> studentRowMapper = new RowMapper<Student>() {
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
            );
        }
    };

    @Override
    public void create(Student student) {
        jdbcTemplate.update("INSERT INTO students (id, name, email) VALUES (?, ?, ?)",
                student.getId(), student.getName(), student.getEmail());
    }

    @Override
    public Student read(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM students WHERE id = ?",
                    studentRowMapper,
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No student found with ID: " + id);
            return null;
        }
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update("UPDATE students SET name = ?, email = ? WHERE id = ?",
                student.getName(), student.getEmail(), student.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("SELECT * FROM students", studentRowMapper);
    }
}
