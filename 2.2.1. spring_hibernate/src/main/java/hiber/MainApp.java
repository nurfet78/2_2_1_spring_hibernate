package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(User.builder()
              .firstName("User1")
              .lastName("Lastname1")
              .email("user1@mail.ru")
              .car(Car.builder()
                      .model("Toyota")
                      .series(7)
                      .build()).build());

      userService.add(User.builder()
              .firstName("User2")
              .lastName("Lastname2")
              .email("user2@mail.ru")
              .car(Car.builder()
                      .model("Mercedes")
                      .series(10)
                      .build()).build());

      userService.add(User.builder()
              .firstName("User3")
              .lastName("Lastname3")
              .email("user3@mail.ru")
              .car(Car.builder()
                      .model("Porsche")
                      .series(5)
                      .build()).build());

      userService.add(User.builder()
              .firstName("User4")
              .lastName("Lastname4")
              .email("user4@mail.ru")
              .car(Car.builder()
                      .model("Chrysler")
                      .series(4)
                      .build()).build());

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Mercedes", 10));


      context.close();
   }
}
