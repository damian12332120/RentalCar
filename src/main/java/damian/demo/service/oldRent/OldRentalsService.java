package damian.demo.service.oldRent;


import damian.demo.entity.Car;
import damian.demo.entity.Employee;
import damian.demo.entity.OldRentals;

import java.util.List;

public interface OldRentalsService {


    List<OldRentals> showOldRent(Employee employee);

    void archive(OldRentals rent);

    void delete(Employee employee, Car car);
}
