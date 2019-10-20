package damian.demo.service.oldRent;

import damian.demo.entity.Car;
import damian.demo.entity.Employee;
import damian.demo.entity.OldRentals;
import damian.demo.service.car.CarService;
import damian.demo.service.employee.EmployeeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest
public class OldRentalsServiceImplTest {

    private Employee employee;
    private Car car;
    private OldRentals oldRentals;

    @Autowired
    OldRentalsService oldRentlasService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CarService carService;

    @Before
    public void setUp() {
        employee = new Employee();
        employee.setEmail("test@wp.pl");
        employee.setPassword("password");
        car = new Car();
        employeeService.save(employee);
        carService.save(car);
        oldRentals = new OldRentals();
        oldRentals.setCar(car);
        oldRentals.setEmployee(employee);
    }

    @Test
    public void shouldAchive() {

        assertTrue(oldRentlasService.showOldRent(employee).isEmpty());
        oldRentlasService.archive(oldRentals);
        assertFalse(oldRentlasService.showOldRent(employee).isEmpty());
        oldRentlasService.delete(employee, car);
    }

    @Test
    public void shouldShowOldRent() {
        oldRentlasService.archive(oldRentals);
        assertFalse(oldRentlasService.showOldRent(employee).isEmpty());
        oldRentlasService.delete(employee, car);

    }

    @Test
    public void shouldDeleteOldRent() {
        oldRentlasService.archive(oldRentals);
        assertFalse(oldRentlasService.showOldRent(employee).isEmpty());
        oldRentlasService.delete(employee, car);
        assertTrue(oldRentlasService.showOldRent(employee).isEmpty());

    }

    @After
    public void tearDown() {
        employeeService.delete(employee);
        carService.delete(car);
    }


}