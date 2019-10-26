package damian.demo.admin;

import damian.demo.entity.Car;
import damian.demo.service.car.CarService;
import damian.demo.service.employee.EmployeeService;
import damian.demo.service.oldRent.OldRentalsService;
import damian.demo.service.rent.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")

public class AdminController {


    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CarService carService;
    @Autowired
    RentService rentService;
    @Autowired
    OldRentalsService oldRentalsService;

    @GetMapping("/showUsers")
    public String showUsers(Model theModel) {
        theModel.addAttribute("users", employeeService.findAll());
        return "admin/users";
    }

    @GetMapping("/showCars")
    public String showCars(Model theModel) {
        return "admin/cars";
    }


    @GetMapping("/showAllCars")
    public String showAllCars(Model theModel) {
        theModel.addAttribute("cars", carService.findAll());
        return "admin/showCars";
    }


    @GetMapping("/showAvailableCars")
    public String showAvailableCars(Model theModel) {
        theModel.addAttribute("cars", carService.findAvailableCars());
        return "admin/showCars";
    }


    @GetMapping("/showNotAvailableCars")
    public String showNotAvailableCars(Model theModel) {
        theModel.addAttribute("cars", carService.findNotAvailableCars());
        return "admin/showCars";
    }


    @GetMapping("/addCar")
    public String pageAddCar(Model theModel) {
        Car car = new Car();
        theModel.addAttribute("car", car);
        return "admin/addCar";
    }

    @GetMapping("/saveCar")
    public String saveCar(@ModelAttribute("car") Car car, Model theModel) {
        car.setAvalible(true);
        carService.save(car);
        theModel.addAttribute("cars", carService.findAvailableCars());
        return "admin/showCars";
    }

    @GetMapping("/showRents")
    public String showRents(Model theModel) {
        theModel.addAttribute("users", employeeService.findAll());
        return "admin/rents";
    }

    @GetMapping("/showCurrentRentals")
    public String showCurrentRentals(Model theModel) {
        theModel.addAttribute("rents",rentService.showCurrentRentals());

        return "admin/currentRentals";
    }

    @GetMapping("/showRentalStories")
    public String showRentalStories(Model theModel) {
        theModel.addAttribute("rents",oldRentalsService.showAllOldRentals());
        return "admin/currentRentals";
    }

}
