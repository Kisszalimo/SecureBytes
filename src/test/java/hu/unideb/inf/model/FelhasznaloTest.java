package hu.unideb.inf.model;

import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FelhasznaloTest {
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
    private Felhasznalo felhasznalo=new Felhasznalo();

    @Test
    void getFelhasznalonev() throws NoSuchFieldException, IllegalAccessException {
        final Field field = felhasznalo.getClass().getDeclaredField("felhasznalonev");
        field.setAccessible(true);
        field.set(felhasznalo, encrypt("Felhasznalo1"));
        final String result = felhasznalo.getFelhasznalonev();
        assertEquals(result, "Felhasznalo1");
    }

    @Test
    void setFelhasznalonev() throws NoSuchFieldException, IllegalAccessException {
        felhasznalo.setFelhasznalonev("Felhasznalo1");
        final Field field=felhasznalo.getClass().getDeclaredField("felhasznalonev");
        field.setAccessible(true);
        assertEquals(decrypt((String) field.get(felhasznalo)), "Felhasznalo1");
    }

    @Test
    void getJelszo() throws NoSuchFieldException, IllegalAccessException {
        final Field field = felhasznalo.getClass().getDeclaredField("jelszo");
        field.setAccessible(true);
        field.set(felhasznalo, encrypt("TesztJelszó"));
        final String result = felhasznalo.getJelszo();
        assertEquals(result, "TesztJelszó");
    }

    @Test
    void setJelszo() throws NoSuchFieldException, IllegalAccessException {
        felhasznalo.setJelszo("TesztJelszó");
        final Field field=felhasznalo.getClass().getDeclaredField("jelszo");
        field.setAccessible(true);
        assertEquals(decrypt((String) field.get(felhasznalo)), "TesztJelszó");
    }

    @Test
    void getId() throws NoSuchFieldException, IllegalAccessException {
        final Field field = felhasznalo.getClass().getDeclaredField("Id");
        field.setAccessible(true);
        field.set(felhasznalo, 1);
        final int result = felhasznalo.getId();
        assertEquals( result, 1);
    }

    @Test
    void setId() throws NoSuchFieldException, IllegalAccessException {
        felhasznalo.setId(1);
        final Field field=felhasznalo.getClass().getDeclaredField("Id");
        field.setAccessible(true);
        assertEquals( field.get(felhasznalo), 1);
    }

    @Test
    void setTema() throws NoSuchFieldException, IllegalAccessException {
        final Felhasznalo felhasznalo=new Felhasznalo();
        felhasznalo.setTema(1);
        final Field field=felhasznalo.getClass().getDeclaredField("tema");
        field.setAccessible(true);
        assertEquals( field.get(felhasznalo), 1);
    }

    @Test
    void getTema() throws NoSuchFieldException, IllegalAccessException {
        final Field field = felhasznalo.getClass().getDeclaredField("tema");
        field.setAccessible(true);
        field.set(felhasznalo, 1);
        final int result = felhasznalo.getTema();
        assertEquals( result, 1);
    }

}