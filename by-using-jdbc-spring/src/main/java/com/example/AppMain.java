package com.example;

import com.example.config.AppConfig;
import com.example.dao.StudentDAO;
import com.example.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentDAO studentDAO = context.getBean(StudentDAO.class);

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Get Student by ID");
            System.out.println("5. Get All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    studentDAO.addStudent(new Student(name, email));
                    break;

                case 2:
                    System.out.print("ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String uname = sc.nextLine();
                    System.out.print("New Email: ");
                    String uemail = sc.nextLine();
                    studentDAO.updateStudent(new Student(uid, uname, uemail));
                    break;

                case 3:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    studentDAO.deleteStudent(did);
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    int gid = sc.nextInt();
                    Student s = studentDAO.getStudentById(gid);
                    System.out.println("ID: " + s.getId() + ", Name: " + s.getName() + ", Email: " + s.getEmail());
                    break;

                case 5:
                    List<Student> list = studentDAO.getAllStudents();
                    for (Student st : list) {
                        System.out.println(st.getId() + " - " + st.getName() + " - " + st.getEmail());
                    }
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    break;
            }
        } while (choice != 6);
    }
}
