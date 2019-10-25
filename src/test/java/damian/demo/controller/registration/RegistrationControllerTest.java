package damian.demo.controller.registration;

import damian.demo.entity.Employee;
import damian.demo.online.User;
import damian.demo.service.employee.EmployeeService;
import damian.demo.validator.UniqueLogin;
import damian.demo.validator.UniqueLoginValidator;
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

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest
public class RegistrationControllerTest {


    private MockMvc mockMvc;
    private Employee employee;

    @Autowired
    private RegistrationController registrationController;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    UniqueLoginValidator uniqueLoginValidator;


    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
        employee = new Employee();
        employee.setLogin("testwwaa");
        employee.setPassword("user!1");
        employee.setFirstName("test");
        employee.setLastName("lllllll");
        employee.setEmail("test@gmail.com");
    }

    @Test
    public void shouldOpenReginstrtionPage() throws Exception {
        this.mockMvc.perform(get("/registration/page"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees/registrationForm"));
    }
    @Test
    public void shouldSendRemainderEmailWhenUserHasAccount() throws Exception {

        mockMvc.perform(post("/registration/save")
                .param("login",employee.getLogin())
                .param("email", employee.getEmail())
                .param("firstName", employee.getFirstName())
                .param("lastName", employee.getLastName())
                .param("password", employee.getPassword()))
                .andExpect(status().isOk())
                .andExpect(view().name("/mainWindow/list"));
        employeeService.delete(employee);
    }

}