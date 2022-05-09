package hu.unideb.inf.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

class FelhasznaloTest {

    @Test
    void getFelhasznalonev() throws NoSuchFieldException, IllegalAccessException {
        final Felhasznalo felhasznalo = new Felhasznalo();
        final Field field = felhasznalo.getClass().getDeclaredField("felhasznalonev");
        field.setAccessible(true);
        field.set(felhasznalo, "Felhasznalo1");
        final String result = felhasznalo.getFelhasznalonev();
        assertEquals("field wasn't retrieved properly", result, "Felhasznalo1");
    }

    @Test
    void setFelhasznalonev() throws NoSuchFieldException, IllegalAccessException {
        final Felhasznalo felhasznalo=new Felhasznalo();
        felhasznalo.setFelhasznalonev("Felhasznalo1");
        final Field field=felhasznalo.getClass().getDeclaredField("felhasznalonev");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(felhasznalo), "Felhasznalo1");
    }

    @Test
    void getJelszo() throws NoSuchFieldException, IllegalAccessException {
        final Felhasznalo felhasznalo = new Felhasznalo();
        final Field field = felhasznalo.getClass().getDeclaredField("jelszo");
        field.setAccessible(true);
        field.set(felhasznalo, "TesztJelszó");
        final String result = felhasznalo.getJelszo();
        assertEquals("field wasn't retrieved properly", result, "TesztJelszó");
    }

    @Test
    void setJelszo() throws NoSuchFieldException, IllegalAccessException {
        final Felhasznalo felhasznalo=new Felhasznalo();
        felhasznalo.setJelszo("TesztJelszó");
        final Field field=felhasznalo.getClass().getDeclaredField("jelszo");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(felhasznalo), "TesztJelszó");
    }

    @Test
    void getId() throws NoSuchFieldException, IllegalAccessException {
        final Felhasznalo felhasznalo = new Felhasznalo();
        final Field field = felhasznalo.getClass().getDeclaredField("Id");
        field.setAccessible(true);
        field.set(felhasznalo, 1);
        final int result = felhasznalo.getId();
        assertEquals("field wasn't retrieved properly", result, 1);
    }

    @Test
    void setId() throws NoSuchFieldException, IllegalAccessException {
        final Felhasznalo felhasznalo=new Felhasznalo();
        felhasznalo.setId(1);
        final Field field=felhasznalo.getClass().getDeclaredField("Id");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(felhasznalo), 1);
    }

    @Test
    void setTema() throws NoSuchFieldException, IllegalAccessException {
        final Felhasznalo felhasznalo=new Felhasznalo();
        felhasznalo.setTema(1);
        final Field field=felhasznalo.getClass().getDeclaredField("tema");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(felhasznalo), 1);
    }

    @Test
    void getTema() throws NoSuchFieldException, IllegalAccessException {
        final Felhasznalo felhasznalo = new Felhasznalo();
        final Field field = felhasznalo.getClass().getDeclaredField("tema");
        field.setAccessible(true);
        field.set(felhasznalo, 1);
        final int result = felhasznalo.getTema();
        assertEquals("field wasn't retrieved properly", result, 1);
    }
}