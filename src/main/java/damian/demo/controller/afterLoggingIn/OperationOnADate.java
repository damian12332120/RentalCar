package damian.demo.controller.afterLoggingIn;

import damian.demo.entity.Employee;
import damian.demo.online.User;
import damian.demo.service.car.CarService;
import damian.demo.service.employee.EmployeeService;
import damian.demo.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;

@Controller
@RequestMapping("/optionOnADate")
public class OperationOnADate {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private CarService carService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public OperationOnADate(){
    }

    @GetMapping("/dataChange")
    public String dataChange(Model theModel) {

        theModel.addAttribute("employee", User.online());
        return "optionsAfterLoggingIn/dataChange/change";
    }

    @PostMapping("/updateData")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            employee.setConfirmationStatus(true);
            employee.setPassword(encoder.encode(employee.getPassword()));
            employeeService.save(employee);
            return "mainWindow/pageAfterLoggingIn";
        } else {
            return "optionsAfterLoggingIn/dataChange/change";
        }
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model theModel) {
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "optionsAfterLoggingIn/dataChange/forgotPassword";
    }

    @GetMapping("/sendAReminderEmail")
    public String sendAReminderEmail(@ModelAttribute("employee") Employee theEmployee, Model model) {
        Optional<Employee> result = employeeService.findByLoginAndEmail(theEmployee);
        Employee employee;
        if (result.isPresent()) {
            employee = result.get();
            try {
                emailService.sendMail(employee.getEmail(),
                        "Wypożyczalnia samochodów",
                        "<b>Witaj " + employee.getFirstName() + "!</b><br>" +
                                "<br> Właśnie restartujesz swoje hasło do konta <b>" + employee.getLogin() + "</b> " +
                                "<br>Aby to zrobić kliknij w link  ->" +
                                " http://localhost:8085/optionOnADate/remaindPassword?id=" + employee.getLogin() +
                                " <br>Przejdziesz do sekcji ze zmianą hasła." +
                                "Jeśli to nie Ty zakładałeś konto, zignoruj tego e-maila." +
                                "<br> Z wyrazami szacunku<br>" +
                                "<b> Zespół Wypożyczalnia Samochodowa<b>", true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            model.addAttribute("car", carService.findAvailableCars());
            return "mainWindow/main";
        }

        return "optionsAfterLoggingIn/dataChange/forgotPassword";
    }

    @RequestMapping("/remaindPassword")
    public String message(@RequestParam(value = "id", required = true) String login, Model model) {
        Optional<Employee> result = employeeService.findByLogin(login);
        if (result.isPresent()) {
            model.addAttribute("employee", result.get());
            return "optionsAfterLoggingIn/dataChange/newPassword";
        }
        return "error";
    }

    @PostMapping("/updatePassword")
    public String savePassword(@ModelAttribute("employee") Employee theEmployee, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            theEmployee.setConfirmationStatus(true);
            theEmployee.setPassword(encoder.encode(theEmployee.getPassword()));
            employeeService.save(theEmployee);
            return "employees/loginForm";
        } else {
            return "optionsAfterLoggingIn/dataChange/newPassword";
        }

    }

}

