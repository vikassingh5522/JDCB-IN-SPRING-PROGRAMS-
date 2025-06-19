package com.example;

import com.example.config.SpringConfig;
import com.example.view.StudentView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Initialize Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // Get StudentView bean and start the application
        StudentView view = context.getBean(StudentView.class);
        view.displayMenu();

        // Close context when done
        context.close();
    }
}