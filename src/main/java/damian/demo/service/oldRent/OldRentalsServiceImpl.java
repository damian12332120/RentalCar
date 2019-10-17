package damian.demo.service.oldRent;

import damian.demo.dao.rent.OldRentalsRepository;
import damian.demo.entity.Employee;
import damian.demo.entity.OldRentals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OldRentalsServiceImpl implements OldRentlasService {

    @Autowired
    private OldRentalsRepository oldRentalsRepository;


    public OldRentalsServiceImpl() {
    }

    @Override
    public void archive(OldRentals rent) {
        oldRentalsRepository.save(rent);
    }

    @Override
    public List<OldRentals> showRent(Employee employee) {
        return oldRentalsRepository.findAll()
                .stream()
                .filter(e -> e.getEmployee().getId() == employee.getId()).collect(Collectors.toList());

    }
}
