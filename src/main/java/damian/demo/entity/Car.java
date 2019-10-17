package damian.demo.entity;

import javax.persistence.*;

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
