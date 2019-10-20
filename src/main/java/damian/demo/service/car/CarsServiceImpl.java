package damian.demo.service.car;

import damian.demo.dao.cars.CarsRepository;
import damian.demo.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarsServiceImpl implements CarService {


    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    public CarsServiceImpl() {
    }

    @Override
    public Car findById(int theId) {
        Optional<Car> result = carsRepository.findById(theId);

        return result.orElseThrow(() -> new RuntimeException("Did not find employee id - " + theId));
    }

    @Override
    public List<Car> findAvailableCars() {
        return carsRepository.findAll()
                .stream()
                .filter(Car::isAvalible)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Car car) {
        carsRepository.save(car);
    }

    @Override
    public void delete(Car car){
        carsRepository.delete(car);
    }
}
