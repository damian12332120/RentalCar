package damian.demo.controller.welcome;

import damian.demo.entity.Car;
import damian.demo.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mainWindow")
public class MainController {

    @Autowired
    private CarService carService;


    public MainController() {
    }

    @GetMapping("/list")
    public String showMainMenu(Model theModel) {

        List<Car> car = carService.findAvailableCars();
        theModel.addAttribute("car", car);

        return "mainWindow/main";
    }

}
