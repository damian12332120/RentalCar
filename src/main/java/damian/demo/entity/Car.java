package damian.demo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String brand;

    private String model;

    @Column(name="year_of_production")
    private int yearOfproduction;

    @Column(name="engine_capacity")
    private int engineCapacity;

    private String horsepower;

    @Column(name="price_per_day")
    private int pricePerDay;

    @Column(name="is_Avalible")
    private boolean isAvalible;


    public Car() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfproduction() {
        return yearOfproduction;
    }

    public void setYearOfproduction(int yearOfproduction) {
        this.yearOfproduction = yearOfproduction;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(String horsepower) {
        this.horsepower = horsepower;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvalible() {
        return isAvalible;
    }

    public void setAvalible(boolean avalible) {
        isAvalible = avalible;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return id == car.id &&
                yearOfproduction == car.yearOfproduction &&
                engineCapacity == car.engineCapacity &&
                pricePerDay == car.pricePerDay &&
                isAvalible == car.isAvalible &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(model, car.model) &&
                Objects.equals(horsepower, car.horsepower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, yearOfproduction, engineCapacity, horsepower, pricePerDay, isAvalible);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfproduction=" + yearOfproduction +
                ", engineCapacity=" + engineCapacity +
                ", horsepower='" + horsepower + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", isAvalible=" + isAvalible +
                '}';
    }
}
