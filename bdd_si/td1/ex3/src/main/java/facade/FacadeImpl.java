package facade;

import dao.DaoMusee;
import model.Salle;

public class FacadeImpl implements Facade{

    private DaoMusee daoMusee;

    public FacadeImpl(DaoMusee daoMusee) {
        this.daoMusee = daoMusee;
    }

    @Override
    public int creeSalle(String nomSalle) {
        return this.daoMusee.creeSalle(nomSalle);
    }

    @Override
    public Salle getSalleById(int idSalle) {
        return this.daoMusee.getSalleById(idSalle);
    }
}
