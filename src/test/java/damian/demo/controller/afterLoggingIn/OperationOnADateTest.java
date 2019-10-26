package damian.demo.controller.afterLoggingIn;

import damian.demo.entity.Employee;
import damian.demo.online.User;
import damian.demo.service.car.CarsServiceImpl;
import damian.demo.service.employee.EmployeeServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest
public class OperationOnADateTest {


    @Autowired
    CarsServiceImpl carsService;
    private MockMvc mockMvc;
    private Employee employee;
    @Autowired
    private OperationOnADate operationOnADate;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(operationOnADate).build();
        employee = new Employee();
        employee.setLogin("test");
        employee.setPassword("user");
        employee.setFirstName("test");
        employee.setEmail("test@gmail.com");
        User.addLoggedIn(employee);
    }

    @Test
    public void shouldOpenDataChangeWhenHaveUser() throws Exception {
        mockMvc.perform(get("/optionOnADate/dataChange")
                .param("employee", String.valueOf(User.online())))
                .andExpect(status().isOk())
                .andExpect(view().name("optionsAfterLoggingIn/dataChange/change"));
    }

    @Test
    public void shouldUpdateData() throws Exception {
        Employee employeeBeforeChange = employee;
        mockMvc.perform(post("/optionOnADate/updateData")
                .param("firstName", "Alina")
                .param("lastName", "Kot")
                .param("email", employee.getEmail())
                .param("login", "nowyLogin")
                .param("password", employee.getPassword()))
                .andExpect(status().isOk())
                .andExpect(view().name("mainWindow/pageAfterLoggingIn"));
        Optional<Employee> employeeAfterChange = employeeService.findByLogin("nowyLogin");
        assertNotEquals(employeeBeforeChange.getFirstName(), employeeAfterChange.get().getFirstName());
        employeeService.delete(employeeAfterChange.get());
    }

    @Test
    public void shouldOpenForgotPassword() throws Exception {
        mockMvc.perform(get("/optionOnADate/forgotPassword")
                .param("employee", String.valueOf(User.online())))
                .andExpect(status().isOk())
                .andExpect(view().name("optionsAfterLoggingIn/dataChange/forgotPassword"));
    }

    @Test
    public void shouldntSendRemainderEmailWhenUserHasNoAccount() throws Exception {
        mockMvc.perform(get("/optionOnADate/sendAReminderEmail")
                .param("employee", String.valueOf(User.online())))
                .andExpect(status().isOk())
                .andExpect(view().name("optionsAfterLoggingIn/dataChange/forgotPassword"));
    }

    @Test
    public void shouldSendRemainderEmailWhenUserHasAccount() throws Exception {
        employeeService.save(employee);

        mockMvc.perform(get("/optionOnADate/sendAReminderEmail")
                .param("login", User.online().getLogin())
                .param("email", User.online().getEmail())
                .param("firstName", User.online().getFirstName()))
                .andExpect(status().isOk())
                .andExpect(view().name("mainWindow/main"));
    }

    @Test
    public void shouldSaveNewPassword() throws Exception {
        String newPasswordBeforeCoder = "ppppp";
        mockMvc.perform(post("/optionOnADate/updatePassword")
                .param("firstName", employee.getFirstName())
                .param("lastName", employee.getLastName())
                .param("email", employee.getEmail())
                .param("login", employee.getLogin())
                .param("password", newPasswordBeforeCoder))
                .andExpect(status().isOk())
                .andExpect(view().name("employees/loginForm"));
        Optional<Employee> employeeAfterChange = employeeService.findByLogin(employee.getLogin());
        assertTrue(passwordEncoder.matches(newPasswordBeforeCoder, employeeAfterChange.get().getPassword()));
        employeeService.delete(employeeAfterChange.get());
    }

    @After
    public void tearDown() throws Exception {
        employeeService.delete(employee);
    }
}