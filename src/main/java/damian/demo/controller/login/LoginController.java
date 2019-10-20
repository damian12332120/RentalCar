package damian.demo.controller.login;

import damian.demo.entity.Employee;
import damian.demo.online.User;
import damian.demo.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EmployeeService employeeService;


    public LoginController(){
    }

    @GetMapping("/form")
    public String login(Model theModel) {
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/loginForm";
    }

    @GetMapping("/checkAndLoginUser")
    public String checkAndLoginUser(@ModelAttribute("employee") Employee theEmployee, Model model) {
        Optional<Employee> result = employeeService.findByLoginAndPassword(theEmployee);
        if (result.isPresent() && result.get().isConfirmationStatus()) {
                model.addAttribute("employee", result.get());
                User.addLoggedIn(result.get());
                return "mainWindow/pageAfterLoggingIn";
            } else {
                return "employees/loginForm";
            }
    }



}





