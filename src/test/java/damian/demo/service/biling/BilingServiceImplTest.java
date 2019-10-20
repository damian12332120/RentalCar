package damian.demo.service.biling;

import damian.demo.dao.rent.BilingRepository;
import damian.demo.entity.Biling;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient()
@SpringBootTest
public class BilingServiceImplTest {

    private Biling biling;

    @Autowired
    BilingService bilingService;
    @Autowired
    BilingRepository bilingRepository;

    @Before
    public void setUp() {
        biling = new Biling();
        biling.setNip("99-88-777-77");
        biling.setAdress("ul.długa 5");
        biling.setName("Test");
    }

    @Test
    public void shouldSaveBill() {
        bilingService.save(biling);
        Optional<Biling> bilingWithDataBase = bilingRepository.findAll()
                .stream()
                .filter(e -> e.getName().equals(biling.getName()))
                .filter(e -> e.getAdress().equals(biling.getAdress()))
                .filter(e -> e.getNip().equals(biling.getNip())).findFirst();
        assertEquals(bilingWithDataBase.get(), biling);
    }

    @After
    public void tearDown() {
        bilingRepository.delete(biling);
    }
}