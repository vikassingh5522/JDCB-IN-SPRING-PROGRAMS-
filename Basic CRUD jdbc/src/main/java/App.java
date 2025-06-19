import config.AppConfig;
import dao.StudentDAO;
import model.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        StudentDAO dao = context.getBean(StudentDAO.class);

        // CREATE
        dao.create(new Student(1, "Vikas", "vikas@example.com"));
        dao.create(new Student(2, "Anita", "anita@example.com"));
        dao.create(new Student(3, "Rohan", "rohan@example.com"));

        // READ
        System.out.println("Reading ID 2: " + dao.read(2));

        // UPDATE
        dao.update(new Student(3, "Rohan Kumar", "rohan.k@example.com"));

        // DELETE
        dao.delete(1);

        // DISPLAY ALL
        System.out.println("\nAll Students in Table Format:");
        printTable(dao.findAll());

        context.close();
    }

    public static void printTable(List<Student> students) {
        System.out.println("+-----+------------+----------------------+");
        System.out.println("| ID  | Name       | Email                |");
        System.out.println("+-----+------------+----------------------+");
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("+-----+------------+----------------------+");
    }
}
