package damian.demo.service.biling;

import damian.demo.entity.Biling;
import org.springframework.stereotype.Service;

@Service
public interface BilingService {

    void save(Biling biling);
}
