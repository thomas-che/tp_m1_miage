package dao;

import model.Salle;

public interface DaoMusee {

    int creeSalle(String nomSalle);

    Salle getSalleById(Integer idSalle);
}
