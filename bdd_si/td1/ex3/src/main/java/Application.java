import dao.DaoMuseeImpl;
import facade.Facade;
import facade.FacadeImpl;
import model.Salle;

public class Application {
    public static void main(String[] args) {
        Facade facade = new FacadeImpl(new DaoMuseeImpl());
        //int idSalleCree = facade.creeSalle("La petite salle");
        //System.out.println(idSalleCree);

        Salle salle1 = facade.getSalleById(6);
        System.out.println(salle1);
    }
}
