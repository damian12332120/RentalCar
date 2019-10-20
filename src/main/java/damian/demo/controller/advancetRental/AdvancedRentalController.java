package damian.demo.controller.advancetRental;


import damian.demo.entity.Biling;
import damian.demo.entity.Car;
import damian.demo.entity.CurrentRentals;
import damian.demo.online.User;
import damian.demo.service.biling.BilingService;
import damian.demo.service.car.CarService;
import damian.demo.service.rent.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
@RequestMapping("/advanceRental")
public class AdvancedRentalController {

    private Car car;
    @Autowired
    private CarService carService;
    @Autowired
    private RentService rentService;
    @Autowired
    private BilingService bilingService;

    public AdvancedRentalController(){
    }

    @GetMapping("/options")
    public String advancedRentalOptions(@RequestParam("carsId") int theId, Model model) {
        car = carService.findById(theId);
        model.addAttribute("employee", User.online());
        model.addAttribute("car", car);
        return "optionsAfterLoggingIn/rent/advancedRentalOptions";
    }


    @GetMapping("/car/rent")
    public String takeCar(HttpServletRequest request, Model model) {

        LocalDate loanFrom = LocalDate.parse(request.getParameter("loanFrom"));
        LocalDate loanTo = LocalDate.parse(request.getParameter("loanTo"));

        int price = rentService.compareDateAndChargeTheCost(loanFrom, loanTo, car);
        rentService.proofOfPayment(request.getParameter("proofOfPayment"), price);

        String bilingNazwa = request.getParameter("bilingNazwa");
        String bilingAdres = request.getParameter("bilingAdres");
        String bilingNIP = request.getParameter("bilingNIP");

        CurrentRentals rent;
        if (bilingNazwa != null) {
            Biling biling = new Biling(bilingNazwa, bilingAdres, bilingNIP);
            rent = new CurrentRentals(loanFrom, loanTo, price, biling, car, User.online());
            bilingService.save(biling);
        } else {
            rent = new CurrentRentals(loanFrom, loanTo, price, car, User.online());
        }
        rentService.rent(rent, car);
        model.addAttribute("employee", User.online());
        return "mainWindow/pageAfterLoggingIn";
    }


    @GetMapping("/car/return")
    public String returnCar(@RequestParam("carsId") int theId, Model model) {

        Car car = carService.findById(theId);

        rentService.returnCar(User.online(), car);

        model.addAttribute("car", car);
        model.addAttribute("employee", User.online());

        return "mainWindow/pageAfterLoggingIn";
    }


}
