package damian.demo.service.rent;


import damian.demo.dao.rent.RentRepository;
import damian.demo.entity.Car;
import damian.demo.entity.CurrentRentals;
import damian.demo.entity.Employee;
import damian.demo.online.User;
import damian.demo.service.car.CarService;
import damian.demo.service.employee.EmployeeService;
import damian.demo.service.oldRent.OldRentalsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest

public class RentServiceImplTest {

    private Employee employee;
    private Car car;

    @Autowired
    RentServiceImpl rentService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CarService carService;
    @Autowired
    RentRepository rentRepository;
    @Autowired
    OldRentalsService oldRentlasService;


    @Before
    public void setUp() {
        employee = new Employee();
        employee.setEmail("test@wp.pl");
        employee.setPassword("password");
        car = new Car();
        User.addLoggedIn(employee);
    }

    @Test
    public void shouldHaveAGoodResult() {
        LocalDate loanDate = LocalDate.now();
        LocalDate loanTo = LocalDate.now().plusDays(3);
        car.setPricePerDay(60);
        int price = rentService.compareDateAndChargeTheCost(loanDate, loanTo, car);
        assertEquals(price, 180);
    }


    @Test
    public void shouldEnableAMethodNamedSendMailWithReceipt() {
        RentServiceImpl spy = spy(rentService);
        spy.proofOfPayment("bill", 60);
        verify(spy).sendMailWithReceipt(60);
    }


    @Test
    public void shouldEnableAMethodNamedSendMailWithInvoice() {

        RentServiceImpl spy = spy(rentService);
        spy.proofOfPayment("invoice", 60);
        verify(spy).sendMailWithInvoice(60);
    }

    @Test
    public void shouldNotEnableAnyMethod() {

        RentServiceImpl spy = spy(rentService);
        spy.proofOfPayment("randomName", 10);
        verify(spy, never()).sendMailWithInvoice(10);
    }


    private CurrentRentals createObjectToMethotfithConnectDatabase() {
        employeeService.save(employee);
        CurrentRentals rent = new CurrentRentals();
        rent.setCar(car);
        rent.setEmployee(employee);
        rentService.rent(rent, car);
        return rent;
    }

    @Test
    public void shouldRentCar() {
        CurrentRentals rent = createObjectToMethotfithConnectDatabase();
        assertFalse(rentService.showCustomersCar(employee).isEmpty());
        assertFalse(car.isAvalible());
        removingItems(rent);

    }

    @Test
    public void shouldShowCustomerCar() {
        CurrentRentals rent = createObjectToMethotfithConnectDatabase();
        assertFalse(rentService.showCustomersCar(employee).isEmpty());
        removingItems(rent);
    }

    private void removingItems(CurrentRentals rent) {
        rentRepository.delete(rent);
        carService.delete(car);
        employeeService.delete(employee);
    }

    @Test
    public void shouldReturnCar() {

        CurrentRentals rent = createObjectToMethotfithConnectDatabase();
        assertFalse(rentService.showCustomersCar(employee).isEmpty());
        rentService.returnCar(employee, car);
        assertTrue(rentService.showCustomersCar(employee).isEmpty());
        assertFalse(oldRentlasService.showOldRent(employee).isEmpty());

        oldRentlasService.delete(employee,car);
        carService.delete(car);
        employeeService.delete(employee);
    }


    @After
    public void tearDown(){
        User.loggingOut();
    }
}