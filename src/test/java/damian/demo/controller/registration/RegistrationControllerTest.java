package damian.demo.controller.registration;

import damian.demo.service.employee.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest
public class RegistrationControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private RegistrationController registrationController;
    @Autowired
    private EmployeeService employeeService;


    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    public void shouldOpenReginstrtionPage() throws Exception {
        this.mockMvc.perform(get("/registration/page"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees/registrationForm"));
    }

}