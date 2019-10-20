package damian.demo.service.oldRent;

import damian.demo.dao.rent.OldRentalsRepository;
import damian.demo.entity.Car;
import damian.demo.entity.Employee;
import damian.demo.entity.OldRentals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OldRentalsServiceImpl implements OldRentalsService {

    @Autowired
    private OldRentalsRepository oldRentalsRepository;


    public OldRentalsServiceImpl() {
    }

    @Override
    public void archive(OldRentals rent) {
        oldRentalsRepository.save(rent);
    }

    @Override
    public List<OldRentals> showOldRent(Employee employee) {
        return oldRentalsRepository.findAll()
                .stream()
                .filter(e -> e.getEmployee().getId() == employee.getId()).collect(Collectors.toList());

    }

    @Override
    public void delete(Employee employee, Car car){
        Optional<OldRentals> oldRentals= oldRentalsRepository.findAll()
                .stream()
                .filter(e -> e.getEmployee().getId() == employee.getId())
                .filter(e -> e.getCar().getId() == car.getId()).findFirst();

       oldRentalsRepository.delete(oldRentals.get());
    }
}
