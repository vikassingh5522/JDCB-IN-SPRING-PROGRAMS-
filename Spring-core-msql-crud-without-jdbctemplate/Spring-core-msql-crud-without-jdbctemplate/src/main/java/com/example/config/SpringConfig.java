package com.example.config;

import com.example.controller.StudentController;
import com.example.dao.StudentDAO;
import com.example.dao.StudentDAOImpl;
import com.example.view.StudentView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration

public class SpringConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root"); // Replace with your MySQL username
        dataSource.setPassword("Vikas@9156"); // Replace with your MySQL password
        return dataSource;
    }

    @Bean
    public StudentDAO studentDAO(DataSource dataSource) {
        return new StudentDAOImpl(dataSource);
    }

    @Bean
    public StudentController studentController(StudentDAO studentDAO) {
        return new StudentController(studentDAO);
    }

    @Bean
    public StudentView studentView(StudentController studentController) {
        return new StudentView(studentController);
    }

    @Bean
    public DatabaseInitializer databaseInitializer(DataSource dataSource) {
        return new DatabaseInitializer(dataSource);
    }
}