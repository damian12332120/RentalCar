package damian.demo.controller.registration;

import damian.demo.entity.Employee;
import damian.demo.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ConfirmController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EmployeeService employeeService;

    public ConfirmController() {
    }

    @RequestMapping("/confirm")
    public String confirmEmail(@RequestParam(value = "id", required = true) String confirmationId, Model model) {
        Employee employee = employeeService.getEmployeeByConfirmationId(confirmationId);
            if (saveEmloyee(employee)) {
                model.addAttribute("employee", employee);
            }
        return "employees/loginForm";
    }


    private boolean saveEmloyee(Employee employee) {
        if (!employee.isConfirmationStatus()) {
            employee.setConfirmationStatus(true);
            employee.setConfirmationId(null);
            employeeService.save(employee);
            return true;
        }
        return false;
    }
}