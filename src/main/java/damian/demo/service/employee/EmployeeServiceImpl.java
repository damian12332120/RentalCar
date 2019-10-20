package damian.demo.service.employee;

import damian.demo.dao.employee.EmployeeRepository;
import damian.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public EmployeeServiceImpl() {
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }


    @Override
    public void save(Employee employee) {
        if (employee.getPassword().length() > 14) {
            passwordEncoder.upgradeEncoding(employee.getPassword());
        } else {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        employeeRepository.save(employee);
    }


    @Override
    public Optional<Employee> findByLogin(String login) {
        return employeeRepository.findByLogin(login);
    }


    @Override
    public Employee getEmployeeByConfirmationId(String confirmationId) {
        return employeeRepository.getEmployeeByConfirmationId(confirmationId);
    }

    @Override
    public Optional<Employee> findByLoginAndEmail(Employee theEmployee) {
        return employeeRepository.findByLogin(theEmployee.getLogin())
                .filter(e -> e.getEmail().equals(theEmployee.getEmail()));
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Optional<Employee> findByLoginAndPassword(Employee theEmployee) {
        return employeeRepository.findByLogin(theEmployee.getLogin())
                .filter(e -> passwordEncoder.matches(theEmployee.getPassword(), e.getPassword()));
    }
}






