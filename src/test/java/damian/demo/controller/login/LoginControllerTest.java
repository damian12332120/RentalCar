package damian.demo.controller.login;


import damian.demo.entity.Employee;
import damian.demo.service.employee.EmployeeServiceImpl;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest
public class LoginControllerTest {

    private MockMvc mockMvc;

    private Employee employee;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private LoginController loginController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        employee = new Employee();
    }

    @Test
    public void shouldOpenLoginPage() throws Exception {
        this.mockMvc.perform(get("/login/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees/loginForm"));
    }


    @Test
    public void shouldNotLogInWithRandomInformation() throws Exception {

        employee.setLogin("empty");
        employee.setPassword("user");

        mockMvc.perform(get("/login/checkAndLoginUser")
                .param("login", employee.getLogin())
                .param("password", employee.getPassword())
                .param("confirmationStatus", String.valueOf(employee.isConfirmationStatus())))
                .andExpect(view().name("employees/loginForm"))
                .andExpect(model().attributeExists("employee")).andReturn();

    }

    @Test
    public void shouldLogInWithTheCorrectInformation() throws Exception {
        employee.setLogin("arekArek");
        employee.setPassword("D303030d");
        employee.setConfirmationStatus(true);
        employeeService.save(employee);
        employee.setPassword("D303030d");

        mockMvc.perform(get("/login/checkAndLoginUser")
                .param("login", employee.getLogin())
                .param("password", employee.getPassword())
                .param("confirmationStatus", String.valueOf(employee.isConfirmationStatus())))
                .andExpect(view().name("mainWindow/pageAfterLoggingIn"))
                .andExpect(model().attributeExists("employee")).andReturn();
    }

    @Test
    public void shouldNotLogInWithAnUnConfirmedAccount() throws Exception {

        employee.setLogin("arek1111");
        employee.setPassword("D303030d");
        employee.setConfirmationStatus(false);
        employeeService.save(employee);

        mockMvc.perform(get("/login/checkAndLoginUser")
                .param("login", employee.getLogin())
                .param("password", employee.getPassword())
                .param("confirmationStatus", String.valueOf(employee.isConfirmationStatus())))
                .andExpect(view().name("employees/loginForm"))
                .andExpect(model().attributeExists("employee")).andReturn();

    }

    @Test
    public void shouldNotLogInWithEmptyLogin() throws Exception {
        employee.setLogin("");
        employee.setPassword("D303030d");
        employee.setConfirmationStatus(true);
        employeeService.save(employee);

        mockMvc.perform(get("/login/checkAndLoginUser")
                .param("login", employee.getLogin())
                .param("password", employee.getPassword())
                .param("confirmationStatus", String.valueOf(employee.isConfirmationStatus())))
                .andExpect(view().name("employees/loginForm"))
                .andExpect(model().attributeExists("employee")).andReturn();

    }

    @Test
    public void shouldNotLogInWithEmptyPassword() throws Exception {

        employee.setLogin("arek1111");
        employee.setPassword("");
        employee.setConfirmationStatus(true);
        employeeService.save(employee);

        mockMvc.perform(get("/login/checkAndLoginUser")
                .param("login", employee.getLogin())
                .param("password", employee.getPassword())
                .param("confirmationStatus", String.valueOf(employee.isConfirmationStatus())))
                .andExpect(view().name("employees/loginForm"))
                .andExpect(model().attributeExists("employee")).andReturn();

    }

    @Test
    public void shouldNotLogInWithEmptyObject() throws Exception {

        employee.setLogin("");
        employee.setPassword("");
        employeeService.save(employee);

        mockMvc.perform(get("/login/checkAndLoginUser")
                .param("login", employee.getLogin())
                .param("password", employee.getPassword())
                .param("confirmationStatus", String.valueOf(employee.isConfirmationStatus())))
                .andExpect(view().name("employees/loginForm"))
                .andExpect(model().attributeExists("employee")).andReturn();

    }


    @After
    public void tearDown() {
        employeeService.delete(employee);
    }

}