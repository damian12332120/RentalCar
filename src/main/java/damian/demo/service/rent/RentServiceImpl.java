package damian.demo.service.rent;

import damian.demo.dao.rent.RentRepository;
import damian.demo.entity.Car;
import damian.demo.entity.CurrentRentals;
import damian.demo.entity.Employee;
import damian.demo.entity.OldRentals;
import damian.demo.online.User;
import damian.demo.service.Car.CarService;
import damian.demo.service.email.EmailService;
import damian.demo.service.oldRent.OldRentlasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private OldRentlasService oldRentalsService;

    @Autowired
    public RentServiceImpl(){
    }

    @Override
    public int compareDateAndChargeTheCost(LocalDate loanFrom, LocalDate loanTo, Car car) {
        int howManyDays = loanTo.compareTo(loanFrom);
        return howManyDays * car.getPricePerDay();
    }

    @Override
    public void proofOfPayment(String paymentMethod, int price) {
        if (paymentMethod.equals("Paragon")) {
            sendMailWithReceipt(price);
        } else {
            sendMailWithInvoice(price);
        }
    }

    private void sendMailWithReceipt(int price) {
        try {
            emailService.sendMail(User.online().getEmail(), "Auto zostało wypożyczone ",
                    "<b>Witaj " + User.online().getFirstName() + "!</b><br>" +
                            "<br> Właśnie wypożyczyłeś samochód z naszej wypożyczalni.<br>" +
                            "<br>Auto będzie dostępne w naszym salonie w dniu wypożyczenia.<br>" +
                            "Przesyłamy dane do przelewu<br>" +
                            "PayU SA, ul. Grunwaldzka 186, 60-166 Poznań<br>" +
                            "Bank SA, O/REG. w Poznaniu<br>" +
                            "40 0000 0000 0000 0000 0000 0000<br>" +
                            "Kwota to " + price + "zł<br>" +
                            "Bez zaksięgowanej wpłaty wypożyczenie nie będzie możliwe" +
                            "<br>Jeśli to nie Ty wypożyczyłeś auto prosimy o kontakt." +
                            "<br><br> Z wyrazami szacunku,<br>" +
                            "<b> Zespół Wypożyczalnia Samochodowa<b>", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void sendMailWithInvoice(int price) {
        try {
            emailService.sendMail(User.online().getEmail(), "Auto zostało wypożyczone ",
                    "<b>Witaj " + User.online().getFirstName() + "!</b><br>" +
                            "<br> Właśnie wypożyczyłeś samochód z naszej wypożyczalni.<br>" +
                            "<br>Auto będzie dostępne w naszym salonie w dniu wypożyczenia.<br>" +
                            "W schowku znajdziesz fakturę na kwotę " + price + "zł" +
                            "<br>Jeśli to nie Ty wypożyczyłeś auto prosimy o kontakt." +
                            "<br><br> Z wyrazami szacunku,<br>" +
                            "<b> Zespół Wypożyczalnia Samochodowa<b>", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Car> showCustomersCar(Employee employee) {
        List<CurrentRentals> rentsRecord = rentRepository.findAll()
                .stream()
                .filter(e -> e.getEmployee().getId() == employee.getId())
                .collect(Collectors.toList());
        List<Car> customerCarList = new ArrayList<>();
        for (CurrentRentals r : rentsRecord) {
            customerCarList.add(r.getCar());
        }

        return customerCarList;
//        return retriveCarFromEmployee(rentsRecord);
    }
//
//
//    public List retriveCarFromEmployee(List rentsRecord) {
//        return rentsRecord
//                .stream()
//                .map(record::getCar)
//                .collect(Collectors.toList());
//    }


    @Override
    public void rent(CurrentRentals rent, Car car) {
        car.setAvalible(false);
        carService.save(car);
        rentRepository.save(rent);

    }

    @Override
    public void returnCar(Employee employee, Car car) {
        car.setAvalible(true);
        carService.save(car);
        Optional<CurrentRentals> rentsRecord = rentRepository.findAll()
                .stream()
                .filter(e -> e.getEmployee().getId() == employee.getId())
                .filter(e -> e.getCar().getId() == car.getId()).findFirst();

        OldRentals oldRentals = new OldRentals(rentsRecord.get());

        oldRentalsService.archive(oldRentals);
        rentRepository.delete(rentsRecord.get());
    }

}