package damian.demo.validator;


import damian.demo.entity.Employee;
import damian.demo.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Service
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    @Autowired
    private EmployeeService employeeService;

    public UniqueLoginValidator() {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<Employee> list = employeeService.findByLogin(value);
        return !list.isPresent();
    }


}