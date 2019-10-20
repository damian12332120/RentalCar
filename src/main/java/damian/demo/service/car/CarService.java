package damian.demo.service.car;

import damian.demo.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    Car findById(int theId);

    List<Car> findAvailableCars();

    void save(Car car);

    void delete(Car car);
}
