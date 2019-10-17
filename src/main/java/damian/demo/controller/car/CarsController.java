package damian.demo.controller.car;

import damian.demo.service.Car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    private CarService carService;

    public CarsController(){
    }

    @GetMapping("/list")
    public String listCars(Model theModel) {

        theModel.addAttribute("car", carService.findAvailableCars());

        return "/employees/mainWindow";
    }

    public CarService getCarService() {
        return carService;
    }
}
