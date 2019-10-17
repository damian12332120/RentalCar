package damian.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "old_rentals")
public class OldRentals {


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

    public OldRentals(CurrentRentals currentRentals) {
        this.sinceWhen = currentRentals.getSinceWhen();
        this.untilWhen = currentRentals.getUntilWhen();
        this.price = currentRentals.getPrice();
        this.biling = currentRentals.getBiling();
        this.car = currentRentals.getCar();
        this.employee = currentRentals.getEmployee();
    }

    public OldRentals() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Biling getBiling() {
        return biling;
    }

    public void setBiling(Biling biling) {
        this.biling = biling;
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

    @Override
    public String toString() {
        return "OldRentals{" +
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



