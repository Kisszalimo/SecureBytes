package hu.unideb.inf.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JpaAdatokDaoTest {
    private JpaAdatokDao jpaAdatokDao=new JpaAdatokDao();
    private Adatok tesztadat=new Adatok();

    @Test
    void saveAdatok() {
        jpaAdatokDao.saveAdatok(tesztadat);
        assertEquals(tesztadat,jpaAdatokDao.find(tesztadat.getId()));
    }

    @Test
    void deleteAdatok() {
        jpaAdatokDao.deleteAdatok(tesztadat);
        assertNotEquals(tesztadat,jpaAdatokDao.find(tesztadat.getId()));
    }

    @Test
    void updateAdatok() {
        Adatok updateadat=new Adatok();
        jpaAdatokDao.updateAdatok(tesztadat);
        assertEquals(tesztadat,jpaAdatokDao.find(tesztadat.getId()));
        jpaAdatokDao.updateAdatok(updateadat);
        assertEquals(updateadat,jpaAdatokDao.find(updateadat.getId()));
    }

    @Test
    void getAdatok() {
        int a=jpaAdatokDao.getAdatok().size();
        jpaAdatokDao.saveAdatok(tesztadat);
        int b=jpaAdatokDao.getAdatok().size();
        assertEquals(a+1,b);
    }
}