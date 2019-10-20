package damian.demo.controller.afterLoggingIn;

import damian.demo.online.User;
import damian.demo.service.car.CarService;
import damian.demo.service.oldRent.OldRentalsService;
import damian.demo.service.rent.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/afterLoggingInController")
public class AfterLoggingInController {

    @Autowired
    private CarService carService;
    @Autowired
    private RentService rentService;
    @Autowired
    private OldRentalsService oldRentlasService;


    public AfterLoggingInController() {
    }

    @GetMapping("/showAvailableCars")
    public String showAvailableCars(Model theModel) {

        theModel.addAttribute("employee", User.online());
        theModel.addAttribute("car", carService.findAvailableCars());
        return "optionsAfterLoggingIn/rent/rentCar";
    }


    @GetMapping("/showCustomerCars")
    public String showCustomerCars(Model theModel) {

        theModel.addAttribute("rentals", oldRentlasService.showOldRent(User.online()));
        theModel.addAttribute("car", rentService.showCustomersCar(User.online()));
        theModel.addAttribute("employee", User.online());
        return "optionsAfterLoggingIn/showOldRent/showOldRent";
    }

    @GetMapping("/dataChange")
    public String dataChange(Model theModel) {

        theModel.addAttribute("employee", User.online());

        return "optionsAfterLoggingIn/dataChange/change";
    }


    @GetMapping("/logout")
    public String logout() {
        User.loggingOut();
        return "redirect:/mainWindow/list";
    }
}
