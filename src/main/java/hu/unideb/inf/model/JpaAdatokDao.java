package hu.unideb.inf.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaAdatokDao implements AdatokDao{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public Adatok find(int id){
        return entityManager.find(Adatok.class,id);
    }

    @Override
    public void saveAdatok(Adatok a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAdatok(Adatok a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAdatok(Adatok a) {
        saveAdatok(a);
    }

    @Override
    public List<Adatok> getAdatok() {
        TypedQuery<Adatok> query = entityManager.createQuery(
                "SELECT a FROM Adatok a", Adatok.class);
        List<Adatok> adatok = query.getResultList();
        return adatok;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
        entityManager.close();
    }
}
