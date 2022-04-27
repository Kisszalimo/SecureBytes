package hu.unideb.inf.model;

import java.util.List;

public interface FelhasznaloDAO extends AutoCloseable{
    public void saveFelhasznalo(Felhasznalo f);
    public void deleteFelhasznalo(Felhasznalo f);
    public void updateFelhasznalo(Felhasznalo f);
    public List<Felhasznalo> getFelhasznalok();
}
