package damian.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Biling {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="Nazwa")
    @NotEmpty(message = "Nazwa nie może być pusta")
    private String name;

    @Column(name="Adres")
    @NotEmpty(message = "Adres nie może być pusty")
    private String adress;

    @NotEmpty(message = "NIP nie może być pusty")
    @Column(name="NIP")
    private String nip;


    public Biling() {
    }

    public Biling(@NotEmpty(message = "Nazwa nie może być pusta") String name, @NotEmpty(message = "Adres nie może być pusty") String adress, @NotEmpty(message = "NIP nie może być pusty") String nip) {
        this.name = name;
        this.adress = adress;
        this.nip = nip;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
