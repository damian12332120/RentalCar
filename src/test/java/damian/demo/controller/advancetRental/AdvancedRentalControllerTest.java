package damian.demo.controller.advancetRental;

import damian.demo.entity.Car;
import damian.demo.entity.Employee;
import damian.demo.online.User;
import damian.demo.service.car.CarService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest
public class AdvancedRentalControllerTest {

    private MockMvc mockMvc;
    private Employee employee;
    private Car car;

    @Autowired
    AdvancedRentalController advancedRentalController;
    @Autowired
    CarService carService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(advancedRentalController).build();
        employee = new Employee();
        employee.setLogin("test");
        employee.setPassword("user");
        employee.setFirstName("test");
        employee.setEmail("test@gmail.com");
        User.addLoggedIn(employee);
        car = new Car();
        car.setModel("test");
        car.setAvalible(true);
        carService.save(car);
    }

    @Test
    public void shouldOpenAdvancedRentalOptionsWhenHaveUser() throws Exception {

        Optional<Car> carFromTheDatabase = carService.findAvailableCars().stream().filter(e -> e.getModel().equals(car.getModel())).findFirst();

        mockMvc.perform(get("/advanceRental/options")
                .param("carsId", String.valueOf(carFromTheDatabase.get().getId()))
                .param("employee", String.valueOf(User.online())))
                .andExpect(status().isOk())
                .andExpect(view().name("optionsAfterLoggingIn/rent/advancedRentalOptions"));

    }

    @After
    public void tearDown() throws Exception {
        carService.delete(car);
    }
}