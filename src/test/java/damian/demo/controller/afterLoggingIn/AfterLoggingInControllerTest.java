package damian.demo.controller.afterLoggingIn;

import damian.demo.entity.Employee;
import damian.demo.online.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest
public class AfterLoggingInControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private AfterLoggingInController afterLoggingInController;

    @Before
    public void setUp() {
       mockMvc = MockMvcBuilders.standaloneSetup(afterLoggingInController).build();
        Employee employee = new Employee();
        employee.setLogin("test");
        employee.setPassword("user");
        User.addLoggedIn(employee);
    }

    @Test
    public void shouldOpenAfterLoginPageWhenHaveUser() throws Exception {
        mockMvc.perform(get("/afterLoggingInController/showAvailableCars")
                .param("employee", String.valueOf(User.online())))
                .andExpect(status().isOk())
                .andExpect(view().name("optionsAfterLoggingIn/rent/rentCar"));
    }

    @Test
    public void shouldOpenShowCustomerCarWhenHaveUser() throws Exception {
        mockMvc.perform(get("/afterLoggingInController/showCustomerCars")
                .param("employee", String.valueOf(User.online())))
                .andExpect(status().isOk())
                .andExpect(view().name("optionsAfterLoggingIn/showOldRent/showOldRent"));
    }

    @Test
    public void shouldOpenDataChangeWhenHaveUser() throws Exception {
        this.mockMvc.perform(get("/afterLoggingInController/dataChange")
                .param("employee", String.valueOf(User.online())))
                .andExpect(status().isOk())
                .andExpect(view().name("optionsAfterLoggingIn/dataChange/change"));
    }

    @Test
    public void shouldLogout() throws Exception {
        this.mockMvc.perform(get("/afterLoggingInController/logout")
                .param("employee", String.valueOf(User.online())))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/mainWindow/list"));
    }
}