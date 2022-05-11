package hu.unideb.inf.model;

import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AdatokTest {

    private static final String JASYPT_PWD = "yV5hE8XEhkQ4qVG8";

    private String decrypt(String s) {
        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(JASYPT_PWD);
        return textEncryptor.decrypt(s);
    }
    private String encrypt(String s) {
        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(JASYPT_PWD);
        return textEncryptor.encrypt(s);
    }
    private Adatok adatok=new Adatok();

    @Test
    void getFelhasznalonev() throws  NoSuchFieldException, IllegalAccessException {
        final Field field = adatok.getClass().getDeclaredField("felhasznalonev");
        field.setAccessible(true);
        field.set(adatok, encrypt("Felhasznalo1"));
        final String result = adatok.getFelhasznalonev();
        assertEquals("Felhasznalo1", result);
    }

    @Test
    void setFelhasznalonev() throws NoSuchFieldException, IllegalAccessException {
        adatok.setFelhasznalonev("Felhasznalo1");
        final Field field=adatok.getClass().getDeclaredField("felhasznalonev");
        field.setAccessible(true);
        assertEquals( decrypt((String) field.get(adatok)), "Felhasznalo1");
    }

    @Test
    void getJelszo() throws NoSuchFieldException, IllegalAccessException {
        final Field field = adatok.getClass().getDeclaredField("jelszo");
        field.setAccessible(true);
        field.set(adatok, encrypt("TesztJelszó"));
        final String result = adatok.getJelszo();
        assertEquals( result, "TesztJelszó");
    }

    @Test
    void setJelszo() throws IllegalAccessException, NoSuchFieldException {
        adatok.setJelszo("TesztJelszó");
        final Field field=adatok.getClass().getDeclaredField("jelszo");
        field.setAccessible(true);
        assertEquals(decrypt((String) field.get(adatok)), "TesztJelszó");
    }

    @Test
    void getEmail() throws IllegalAccessException, NoSuchFieldException {
        final Field field = adatok.getClass().getDeclaredField("email");
        field.setAccessible(true);
        field.set(adatok, encrypt("teszt@gmail.com"));
        final String result = adatok.getEmail();
        assertEquals( result, "teszt@gmail.com");
    }

    @Test
    void setEmail() throws NoSuchFieldException, IllegalAccessException {
        adatok.setEmail("teszt@gmail.com");
        final Field field=adatok.getClass().getDeclaredField("email");
        field.setAccessible(true);
        assertEquals( decrypt((String) field.get(adatok)), "teszt@gmail.com");
    }

    @Test
    void getWeboldal() throws NoSuchFieldException, IllegalAccessException {
        final Field field = adatok.getClass().getDeclaredField("weboldal");
        field.setAccessible(true);
        field.set(adatok, encrypt("www.teszt.com"));
        final String result = adatok.getWeboldal();
        assertEquals( result, "www.teszt.com");
    }

    @Test
    void setWeboldal() throws NoSuchFieldException, IllegalAccessException {
        adatok.setWeboldal("www.teszt.com");
        final Field field=adatok.getClass().getDeclaredField("weboldal");
        field.setAccessible(true);
        assertEquals( decrypt((String) field.get(adatok)), "www.teszt.com");
    }

    @Test
    void getLeiras() throws NoSuchFieldException, IllegalAccessException {
        final Field field = adatok.getClass().getDeclaredField("leiras");
        field.setAccessible(true);
        field.set(adatok, encrypt("Teszt leírás."));
        final String result = adatok.getLeiras();
        assertEquals( result, "Teszt leírás.");
    }

    @Test
    void setLeiras() throws NoSuchFieldException, IllegalAccessException {
        adatok.setLeiras("Teszt leírás.");
        final Field field=adatok.getClass().getDeclaredField("leiras");
        field.setAccessible(true);
        assertEquals( decrypt((String) field.get(adatok)), "Teszt leírás.");
    }

    @Test
    void getId() throws NoSuchFieldException, IllegalAccessException {
        final Field field = adatok.getClass().getDeclaredField("Id");
        field.setAccessible(true);
        field.set(adatok, 1);
        final int result = adatok.getId();
        assertEquals(result,1);
    }

    @Test
    void setTulajdonos() throws NoSuchFieldException, IllegalAccessException {
        adatok.setTulajdonos("Teszt József");
        final Field field=adatok.getClass().getDeclaredField("tulajdonos");
        field.setAccessible(true);
        assertEquals( field.get(adatok), "Teszt József");
    }

    @Test
    void getTulajdonos() throws NoSuchFieldException, IllegalAccessException {
        final Field field = adatok.getClass().getDeclaredField("tulajdonos");
        field.setAccessible(true);
        field.set(adatok, "Teszt József");
        final String result = adatok.getTulajdonos();
        assertEquals(result, "Teszt József");
    }
}

