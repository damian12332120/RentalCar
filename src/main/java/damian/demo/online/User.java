package damian.demo.online;

import damian.demo.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class User {

    private static List<Employee> loggedInUser = new ArrayList<>(1);

    public User() {
    }

    public static void addLoggedIn(Employee employee) {
        loggedInUser.clear();
        loggedInUser.add(employee);

    }

    public static Employee online() {
        return loggedInUser.get(0);
    }

    public static void loggingOut(){
        loggedInUser.clear();
    }
}
