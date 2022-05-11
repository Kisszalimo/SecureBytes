package hu.unideb.inf.model;

import org.jasypt.util.text.StrongTextEncryptor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Felhasznalo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String felhasznalonev;
    private String jelszo;
    private int tema = 0;
    private boolean isAutoThemeUsed;
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

    public int getId(){
        return Id;
    }

    public void setId(int i){
        Id = i;
    }

    public void setTema(int i){
        tema = i;
    }

    public int getTema(){
        return tema;
    }

    public boolean getAutoTheme() { return isAutoThemeUsed; }

    public void setAutoTheme(boolean isAutoThemeUsed){
        this.isAutoThemeUsed = isAutoThemeUsed;
    }
}
