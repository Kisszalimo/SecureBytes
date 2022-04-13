package hu.unideb.inf.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaFelhasznaloDAO implements FelhasznaloDAO {
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveFelhasznalo(Felhasznalo f) {
        entityManager.getTransaction().begin();
        entityManager.persist(f);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteFelhasznalo(Felhasznalo f) {
        entityManager.remove(f);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateFelhasznalo(Felhasznalo f) {
        saveFelhasznalo(f);
    }

    @Override
    public List<Felhasznalo> getFelhasznalok() {
        TypedQuery<Felhasznalo> query = entityManager.createQuery(
                "SELECT f FROM Felhasznalo f", Felhasznalo.class);
        List<Felhasznalo> felhasznalok = query.getResultList();
        return felhasznalok;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
        entityManager.close();
    }

    public boolean szerepelE(List<Felhasznalo> lista, String felhasznalonev)
    {
        boolean bennevan = false;

        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).getFelhasznalonev().equals(felhasznalonev))
            {
                bennevan = true;
                break;
            }
        }

        return bennevan;
    }

    public boolean jelszoMatch(List<Felhasznalo> lista, String felhasznalonev, String jelszo)
    {
        boolean megegyezik = false;

        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).getJelszo().equals(jelszo) && lista.get(i).getFelhasznalonev().equals(felhasznalonev))
            {
                megegyezik = true;
                break;
            }
        }

        return megegyezik;
    }
}
