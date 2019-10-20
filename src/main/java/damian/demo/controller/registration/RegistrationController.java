package damian.demo.controller.registration;

import damian.demo.entity.Employee;
import damian.demo.service.employee.EmployeeService;
import damian.demo.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmailService emailService;


    public RegistrationController(){
    }

    @GetMapping("/page")
    public String showRegistrationPage(Model theModel) {
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/registrationForm";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") @Valid Employee theEmployee, BindingResult bindingResult) {

        if (checkingEmail(theEmployee, bindingResult)) {
            employeeService.save(theEmployee);
            return "redirect:/mainWindow/list";
        } else {
            return "employees/registrationForm";
        }
    }



    private boolean checkingEmail(Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return false;
        } else {
            employee.setConfirmationId(employee.createConfirmationID());
            try {
                emailService.sendMail(employee.getEmail(),
                        "Wypożyczalnia samochodów",
                        "<b>Witaj " + employee.getFirstName() + "!</b><br>" +
                                "<br> Właśnie założyłeś konto w naszej wypożyczalni.<br> Twój login: <b>" + employee.getLogin() + "</b> " +
                                "<br>Aby aktywować konto kliknij w podany link ->" +
                                " http://localhost:8085/confirm?id=" + employee.getConfirmationId() +
                                " <br>Masz już dostęp do sekcji wypożyczeń. Jesteśmy przekonani, iż czas spędzony w naszych autach" +
                                " będziesz wspominał fantastycznie.<br>" +
                                "Jeśli to nie Ty zakładałeś konto, zignoruj tego e-maila." +
                                "<br> Z wyrazami szacunku<br>" +
                                "<b> Zespół Wypożyczalnia Samochodowa<b>", true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}











