package damian.demo.service.car;

import damian.demo.entity.Car;
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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest
public class CarsServiceImplTest {

    private Car car;
    private MockMvc mockMvc;

    @Autowired
    CarsServiceImpl carService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(carService).build();
        car = new Car();
    }

    @Test
    public void shouldSaveCar() {
        car.setBrand("VW");
        carService.save(car);
        assertEquals(car, carService.findById(car.getId()));
    }

    @Test
    public void shouldFindById() {
        car.setModel("Polo");
        carService.save(car);
        Car byId = carService.findById(car.getId());
        assertEquals(byId, car);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotFoundByWrongId() {
        carService.findById(-1);
    }


    @Test
    public void sholudFindAvailableCars() {
        car.setAvalible(true);
        carService.save(car);
        List<Car> availableCars = carService.findAvailableCars();
        assertTrue(!availableCars.isEmpty());
    }

    @Test
    public void sholudNotFindAvailableCars() {
        car.setAvalible(false);
        carService.save(car);
        List<Car> availableCars = carService.findNotAvailableCars();
        assertTrue(!availableCars.isEmpty());
    }

    @After
    public void tearDown() {
        carService.delete(car);
    }
}