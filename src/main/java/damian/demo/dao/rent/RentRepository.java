package damian.demo.dao.rent;


import damian.demo.entity.CurrentRentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository  extends JpaRepository<CurrentRentals, Integer> {
}
