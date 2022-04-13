package hu.unideb.inf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Adatok implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String felhasznalonev;
    private String jelszo;
    private String email;
    private String weboldal;
    private String leiras;
    private String tulajdonos;

    public String getFelhasznalonev() {
        return felhasznalonev;
    }

    public void setFelhasznalonev(String felhasznalonev) {
        this.felhasznalonev = felhasznalonev;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeboldal() {
        return weboldal;
    }

    public void setWeboldal(String weboldal) {
        this.weboldal = weboldal;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public int getId(){
        return Id;
    }

    public void setTulajdonos(String felhasznalonev){
        this.tulajdonos = felhasznalonev;
    }

    public String getTulajdonos(){
        return tulajdonos;
    }
}
