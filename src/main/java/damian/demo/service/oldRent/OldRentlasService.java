package damian.demo.service.oldRent;


import damian.demo.entity.CurrentRentals;
import damian.demo.entity.Employee;
import damian.demo.entity.OldRentals;

import java.util.List;

public interface OldRentlasService {


    public List<OldRentals> showRent(Employee employee);

    void archive(OldRentals rent);
}
