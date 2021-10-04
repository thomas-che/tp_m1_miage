package facade;

import model.Salle;

public interface Facade {
    int creeSalle(String nomSalle);

    Salle getSalleById(int idSalle);
}
