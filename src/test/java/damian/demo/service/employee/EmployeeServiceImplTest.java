package damian.demo.service.employee;

import damian.demo.entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest
public class EmployeeServiceImplTest {

    private Employee employee;
    private String passwordBeforeSaveInDatabase;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        employee = new Employee();
        employee.setLogin("test");
        passwordBeforeSaveInDatabase = "PPPPP";
        employee.setPassword(passwordBeforeSaveInDatabase);
        employee.setEmail("damian@wp.pl");
        employee.setConfirmationId(java.util.UUID.randomUUID().toString());
        employeeService.save(employee);
    }

    @Test
    public void shouldFindAllEmployee() {
        List<Employee> employees = employeeService.findAll();
        assertTrue(!employees.isEmpty());
    }


    @Test
    public void shouldSaveWhenPasswordIsCorrectAndEncode() {
        List<Employee> allEmployees = employeeService.findAll();
        assertTrue(!allEmployees.isEmpty());
        assertTrue(passwordEncoder.matches(passwordBeforeSaveInDatabase, employee.getPassword()));

        assertNotEquals(passwordBeforeSaveInDatabase, employee.getPassword());
    }

    @Test
    public void shouldFindByLogin() {

        Optional<Employee> employeeFromTheDatabase = employeeService.findByLogin(employee.getLogin());
        assertEquals(employeeFromTheDatabase.get(), employee);
    }

    @Test
    public void shouldFindByConfirmationId() {
        Employee employeeByConfirmationId = employeeService.getEmployeeByConfirmationId(employee.getConfirmationId());
        assertEquals(employeeByConfirmationId, employee);
    }

    @Test
    public void shouldFindByLoginAndEmail() {
        Optional<Employee> employeebyLoginAndEmail = employeeService.findByLoginAndEmail(employee);
        assertEquals(employeebyLoginAndEmail.get(), employee);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldDeleteUser() {
        employeeService.delete(employee);
        Optional<Employee> userByLogin = employeeService.findByLogin(employee.getLogin());
        assertEquals(true, userByLogin.get());
    }


    @Test
    public void shouldFindByLoginAndPasworrd() {
        Optional<Employee> employeebyLoginAndPassword = employeeService.findByLoginAndPassword(employee);
        assertTrue(passwordEncoder.matches(passwordBeforeSaveInDatabase, employee.getPassword()));
    }


    @After
    public void tearDown() {
        employeeService.delete(employee);
    }


}