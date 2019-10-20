package damian.demo.service.employee;

import damian.demo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    List<Employee> findAll();


    void save(Employee theEmployee);


    Optional<Employee> findByLoginAndPassword(Employee employee);

    Optional<Employee> findByLogin(String login);

    Employee getEmployeeByConfirmationId(String confirmationId);

    Optional<Employee> findByLoginAndEmail(Employee theEmployee);

    void delete(Employee employee);
}
