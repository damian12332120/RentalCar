package damian.demo.controller.welcome;

import damian.demo.entity.Car;
import damian.demo.service.car.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest
public class MainControllerTest {

    private MockMvc mockMvc;
    @Mock
    private Model model;

    @Autowired
    private MainController mainController;
    @Autowired
    private CarService carService;


    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void shouldOpenMainWebsite() throws Exception {
        this.mockMvc.perform(get("/mainWindow/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("mainWindow/main"));
    }

    @Test
    public void shouldReturnMainWebsite() {

        List<Car> car = carService.findAvailableCars();
        String returnValue = mainController.showMainMenu(model);
        verify(model, times(1)).addAttribute("car", car);
        assertEquals("mainWindow/main", returnValue);
    }


}


