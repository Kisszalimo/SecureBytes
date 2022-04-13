package hu.unideb.inf.model;

import java.util.List;

public interface AdatokDao extends AutoCloseable{
    public void saveAdatok(Adatok a);
    public void deleteAdatok(Adatok a);
    public void updateAdatok(Adatok a);
    public List<Adatok> getAdatok();
}
