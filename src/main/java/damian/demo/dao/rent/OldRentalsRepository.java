package damian.demo.dao.rent;

import damian.demo.entity.OldRentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OldRentalsRepository extends JpaRepository<OldRentals, Integer> {
}
