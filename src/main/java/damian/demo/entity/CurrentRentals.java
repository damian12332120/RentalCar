package damian.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CurrentRentals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "since_When")
    private LocalDate sinceWhen;

    @Column(name = "until_when")
    private LocalDate untilWhen;

    @Column(name = "price")
    private int price;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "biling")
    private Biling biling;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "rented_car")
    private Car car;

    @JoinColumn(name = "car_renal")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Employee employee;

    public CurrentRentals( LocalDate sinceWhen,LocalDate untilWhen, int price, Car car, Employee employee) {
        this.sinceWhen = sinceWhen;
        this.untilWhen = untilWhen;
        this.price = price;
        this.car = car;
        this.employee = employee;
    }

    public CurrentRentals(LocalDate sinceWhen, LocalDate untilWhen, int price, Biling biling, Car car, Employee employee) {
        this.sinceWhen = sinceWhen;
        this.untilWhen = untilWhen;
        this.price = price;
        this.biling = biling;
        this.car = car;
        this.employee = employee;
    }

    public CurrentRentals() {
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Biling getBiling() {
        return biling;
    }

    public void setBiling(Biling biling) {
        this.biling = biling;
    }

    public LocalDate getSinceWhen() {
        return sinceWhen;
    }

    public void setSinceWhen(LocalDate sinceWhen) {
        this.sinceWhen = sinceWhen;
    }

    public LocalDate getUntilWhen() {
        return untilWhen;
    }

    public void setUntilWhen(LocalDate untilWhen) {
        this.untilWhen = untilWhen;
    }

    @Override
    public String toString() {
        return "CurrentRentals{" +
                "id=" + id +
                ", sinceWhen=" + sinceWhen +
                ", untilWhen=" + untilWhen +
                ", price=" + price +
                ", biling=" + biling +
                ", car=" + car +
                ", employee=" + employee +
                '}';
    }
}
