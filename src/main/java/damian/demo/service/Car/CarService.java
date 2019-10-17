package damian.demo.service.Car;

import damian.demo.entity.Car;


import java.util.List;

public interface CarService {

    Car findById(int theId);

    List<Car> findAvailableCars();

    void save(Car car);
}
