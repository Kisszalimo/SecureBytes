package hu.unideb.inf.model;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import static org.junit.Assert.*;

class AdatokTest {

    @Test
    void getFelhasznalonev() throws  NoSuchFieldException, IllegalAccessException {
        final Adatok adatok = new Adatok();
        final Field field = adatok.getClass().getDeclaredField("felhasznalonev");
        field.setAccessible(true);
        field.set(adatok, "Felhasznalo1");
        final String result = adatok.getFelhasznalonev();
        assertEquals("field wasn't retrieved properly", result, "Felhasznalo1");
    }

    @Test
    void setFelhasznalonev() throws NoSuchFieldException, IllegalAccessException {
        final Adatok adatok=new Adatok();
        adatok.setFelhasznalonev("Felhasznalo1");
        final Field field=adatok.getClass().getDeclaredField("felhasznalonev");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(adatok), "Felhasznalo1");
    }

    @Test
    void getJelszo() throws NoSuchFieldException, IllegalAccessException {
        final Adatok adatok = new Adatok();
        final Field field = adatok.getClass().getDeclaredField("jelszo");
        field.setAccessible(true);
        field.set(adatok, "TesztJelszó");
        final String result = adatok.getJelszo();
        assertEquals("field wasn't retrieved properly", result, "TesztJelszó");
    }

    @Test
    void setJelszo() throws IllegalAccessException, NoSuchFieldException {
        final Adatok adatok=new Adatok();
        adatok.setJelszo("TesztJelszó");
        final Field field=adatok.getClass().getDeclaredField("jelszo");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(adatok), "TesztJelszó");
    }

    @Test
    void getEmail() throws IllegalAccessException, NoSuchFieldException {
        final Adatok adatok = new Adatok();
        final Field field = adatok.getClass().getDeclaredField("email");
        field.setAccessible(true);
        field.set(adatok, "teszt@gmail.com");
        final String result = adatok.getEmail();
        assertEquals("field wasn't retrieved properly", result, "teszt@gmail.com");
    }

    @Test
    void setEmail() throws NoSuchFieldException, IllegalAccessException {
        final Adatok adatok=new Adatok();
        adatok.setEmail("teszt@gmail.com");
        final Field field=adatok.getClass().getDeclaredField("email");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(adatok), "teszt@gmail.com");
    }

    @Test
    void getWeboldal() throws NoSuchFieldException, IllegalAccessException {
        final Adatok adatok = new Adatok();
        final Field field = adatok.getClass().getDeclaredField("weboldal");
        field.setAccessible(true);
        field.set(adatok, "www.teszt.com");
        final String result = adatok.getWeboldal();
        assertEquals("field wasn't retrieved properly", result, "www.teszt.com");
    }

    @Test
    void setWeboldal() throws NoSuchFieldException, IllegalAccessException {
        final Adatok adatok=new Adatok();
        adatok.setWeboldal("www.teszt.com");
        final Field field=adatok.getClass().getDeclaredField("weboldal");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(adatok), "www.teszt.com");
    }

    @Test
    void getLeiras() throws NoSuchFieldException, IllegalAccessException {
        final Adatok adatok = new Adatok();
        final Field field = adatok.getClass().getDeclaredField("leiras");
        field.setAccessible(true);
        field.set(adatok, "Teszt leírás.");
        final String result = adatok.getLeiras();
        assertEquals("field wasn't retrieved properly", result, "Teszt leírás.");
    }

    @Test
    void setLeiras() throws NoSuchFieldException, IllegalAccessException {
        final Adatok adatok=new Adatok();
        adatok.setLeiras("Teszt leírás.");
        final Field field=adatok.getClass().getDeclaredField("leiras");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(adatok), "Teszt leírás.");
    }

    @Test
    void getId() throws NoSuchFieldException, IllegalAccessException {
        final Adatok adatok = new Adatok();
        final Field field = adatok.getClass().getDeclaredField("Id");
        field.setAccessible(true);
        field.set(adatok, 1);
        final int result = adatok.getId();
        assertEquals("field wasn't retrieved properly", result, 1);
    }

    @Test
    void setTulajdonos() throws NoSuchFieldException, IllegalAccessException {
        final Adatok adatok=new Adatok();
        adatok.setTulajdonos("Teszt József");
        final Field field=adatok.getClass().getDeclaredField("tulajdonos");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(adatok), "Teszt József");
    }

    @Test
    void getTulajdonos() throws NoSuchFieldException, IllegalAccessException {
        final Adatok adatok = new Adatok();
        final Field field = adatok.getClass().getDeclaredField("tulajdonos");
        field.setAccessible(true);
        field.set(adatok, "Teszt József");
        final String result = adatok.getTulajdonos();
        assertEquals("field wasn't retrieved properly", result, "Teszt József");
    }
}

