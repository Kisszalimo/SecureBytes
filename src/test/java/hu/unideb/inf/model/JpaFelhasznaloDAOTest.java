package hu.unideb.inf.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JpaFelhasznaloDAOTest {
    private JpaFelhasznaloDAO jpaFelhasznaloDAO=new JpaFelhasznaloDAO();
    private Felhasznalo felhasznalo=new Felhasznalo();

    @Test
    void saveFelhasznalo(){
        jpaFelhasznaloDAO.saveFelhasznalo(felhasznalo);
        assertEquals(felhasznalo,jpaFelhasznaloDAO.find(felhasznalo.getId()));
    }
    @Test
    void deleteFelhasznalo() {
        jpaFelhasznaloDAO.deleteFelhasznalo(felhasznalo);
        assertNotEquals(felhasznalo,jpaFelhasznaloDAO.find(felhasznalo.getId()));
    }

    @Test
    void updateFelhasznalo(){
        Felhasznalo teszt = new Felhasznalo();
        jpaFelhasznaloDAO.updateFelhasznalo(felhasznalo);
        assertEquals(felhasznalo,jpaFelhasznaloDAO.find(felhasznalo.getId()));
        jpaFelhasznaloDAO.updateFelhasznalo(teszt);
        assertEquals(teszt,jpaFelhasznaloDAO.find(teszt.getId()));
    }
    @Test
    void getFelhasznalok() {
        int a=jpaFelhasznaloDAO.getFelhasznalok().size();
        jpaFelhasznaloDAO.saveFelhasznalo(felhasznalo);
        int b=jpaFelhasznaloDAO.getFelhasznalok().size();
        assertEquals(a+1,b);
    }

    @Test
    void szerepelE() {
        List<Felhasznalo> tesztlista=new ArrayList<>();
        felhasznalo.setFelhasznalonev("Teszt");
        tesztlista.add(felhasznalo);
        assertTrue(jpaFelhasznaloDAO.szerepelE(tesztlista,felhasznalo.getFelhasznalonev()));
    }

    @Test
    void jelszoMatch() {
        List<Felhasznalo> tesztlista=new ArrayList<>();
        felhasznalo.setFelhasznalonev("Teszt");
        felhasznalo.setJelszo("Jelszó");
        tesztlista.add(felhasznalo);
        assertTrue(jpaFelhasznaloDAO.jelszoMatch(tesztlista,felhasznalo.getFelhasznalonev(), felhasznalo.getJelszo()));
    }
}