package damian.demo.service.car;

import damian.demo.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    List<Car> findAll();

    Car findById(int theId);

    List<Car> findAvailableCars();

    List<Car> findNotAvailableCars();

    void save(Car car);

    void delete(Car car);
}
