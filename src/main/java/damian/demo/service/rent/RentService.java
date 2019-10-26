package damian.demo.service.rent;

import damian.demo.entity.Car;
import damian.demo.entity.CurrentRentals;
import damian.demo.entity.Employee;

import java.time.LocalDate;
import java.util.List;


public interface RentService {

    void returnCar(Employee employee, Car car);

    void rent(CurrentRentals rent, Car car);


    int compareDateAndChargeTheCost(LocalDate loanFrom, LocalDate loanTo, Car car);

    List<Car> showCustomersCar(Employee employee);

    void proofOfPayment(String paymentMethod, int price);

    List<CurrentRentals> showCurrentRentals();
}
