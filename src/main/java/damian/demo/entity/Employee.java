package damian.demo.entity;
import damian.demo.validator.UniqueLogin;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login")
    @Size(min = 6, max = 14, message = "login powinien zawierać od 6 do 14 znaków")
    @NotBlank(message = "login nie może mieć białych znaków")
    @NotEmpty(message = "login nie może być pusty")
    @UniqueLogin
    private String login;

    @Column(name = "first_name")
    @NotEmpty(message = "imie nie może być puste")
    @Pattern(regexp = "[A-Z][a-zA-Z]*", message = "imie nie może zawierać cyfr i znaków specjalnych")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "nazwisko nie może być puste")
    @Pattern(regexp = "[a-zA-z]+(['-][a-zA-Z]+)*", message = "nazwisko nie może zawierać cyfr i znaków specjalnych")
    private String lastName;

    @Email(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "nieprawidłowy format adresu email")
    @NotEmpty(message = "email nie może być pusty")
    private String email;


    @NotEmpty(message = "hasło nie może być puste")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,14}$", message = "hasło musi posiadać 8 znaków zawierać dużą i małą literę i cyfrę")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "confirm_Email")
    private boolean confirmationStatus;


    private String confirmationId;

    public Employee() {
    }

    public Employee(String login, String firstName, String lastName, String email, String password) {


        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        confirmationStatus = false;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(boolean confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }


    public String createConfirmationID() {
        return java.util.UUID.randomUUID().toString();
    }


    @Override
    public String toString() {
        return "employee{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmationStatus=" + confirmationStatus +
                ", confirmationId='" + confirmationId + '\'' +
                '}';
    }
}











