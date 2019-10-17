package damian.demo.dao.rent;

import damian.demo.entity.Biling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilingRepository extends JpaRepository<Biling, Integer> {
}
