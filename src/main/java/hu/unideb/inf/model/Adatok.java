package hu.unideb.inf.model;

import org.jasypt.util.text.StrongTextEncryptor;

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
    private static final String JASYPT_PWD = "yV5hE8XEhkQ4qVG8";

    // Adatok titkosítása
    private String encrypt(String s) {
        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(JASYPT_PWD);
        return textEncryptor.encrypt(s);
    }

    // Adatok visszafejtése
    private String decrypt(String s) {
        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(JASYPT_PWD);
        return textEncryptor.decrypt(s);
    }

    public String getFelhasznalonev() {
        return decrypt(felhasznalonev);
    }

    public void setFelhasznalonev(String felhasznalonev) {
        this.felhasznalonev = encrypt(felhasznalonev);
    }

    public String getJelszo() {
        return decrypt(jelszo);
    }

    public void setJelszo(String jelszo) {
        this.jelszo = encrypt(jelszo);
    }

    public String getEmail() {
        return decrypt(email);
    }

    public void setEmail(String email) {
        this.email = encrypt(email);
    }

    public String getWeboldal() {
        return decrypt(weboldal);
    }

    public void setWeboldal(String weboldal) {
        this.weboldal = encrypt(weboldal);
    }

    public String getLeiras() {
        return decrypt(leiras);
    }

    public void setLeiras(String leiras) {
        this.leiras = encrypt(leiras);
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
