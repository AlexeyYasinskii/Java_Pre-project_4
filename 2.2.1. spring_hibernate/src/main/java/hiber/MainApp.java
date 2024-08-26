package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Lada", 21010);
        Car car2 = new Car("BMW", 5);
        Car car3 = new Car("Mercedes-Benz", 2024);
        Car car4 = new Car("Mazda", 6);

        userService.add(new User("Alex", "Alexsov", "Alex@mail.ru", car1));
        userService.add(new User("Biba", "Boba", "Biba@mail.ru", car2));
        userService.add(new User("Diba", "Doba", "Diba@mail.ru", car3));
        userService.add(new User("Siba", "Soba", "Siba@mail.ru", car4));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();

            System.out.println("findUserByCar = " + userService.findUserByCar("Mercedes-Benz", 2024));

            try {
                userService.findUserByCar("BMW", 2024);
            } catch (NoResultException e) {
                System.out.println("User not found");
            }
        }
        context.close();
    }
}
