package damian.demo.service.biling;

import damian.demo.dao.rent.BilingRepository;
import damian.demo.entity.Biling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BilingServiceImpl implements BilingService {

    @Autowired
    private BilingRepository bilingRepository;

    public BilingServiceImpl() {
    }

    @Override
    public void save(Biling biling) {
        bilingRepository.save(biling);
    }
}
