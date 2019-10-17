package damian.demo.dao.employee;


import damian.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    Optional<Employee> findByLogin(String login);

    List<Employee> findAllByOrderByLastNameAsc();

    Employee getEmployeeByConfirmationId(String confirmationId);

}
