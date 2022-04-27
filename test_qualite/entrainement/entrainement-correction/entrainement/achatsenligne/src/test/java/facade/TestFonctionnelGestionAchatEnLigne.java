package facade;

import modele.ClientNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class TestFonctionnelGestionAchatEnLigne {

    private GestionAchatEnLigne instance;




    public abstract GestionAchatEnLigne getInstance() throws ClientNotFoundException;



    @Before
    public void initialisationInstance() {
        try {
            this.instance= this.getInstance();
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }
    }





    public abstract void initialisationTestCreationPanier1(String nom,String prenom);

    /**
     * Création d'un panier pour un utilisateur existant
     */
    @Test
    public void testCreationPanier1() throws InformationManquanteException, ClientNotFoundException {
        this.initialisationTestCreationPanier1("Boichut","Yohan");
        long idClient = instance.creerClient("Boichut","Yohan");

        Long idPanier = instance.creerPanier( idClient);
        Assert.assertNotNull(idPanier);
    }



    public abstract void initialisationTestCreationPanier2(long idClient);


    @Test(expected = ClientNotFoundException.class)
    public  void testCreationPanier2() throws ClientNotFoundException {
        this.initialisationTestCreationPanier2(12l);
        Long idPanier = instance.creerPanier(12);
    }



    public abstract void initialisationTestCreerClient1();

    @Test(expected = InformationManquanteException.class)
    public void testCreerClient1() throws InformationManquanteException {
        this.initialisationTestCreerClient1();
        this.instance.creerClient(null,"Yohan");
    }


    public abstract void initialisationTestCreerClient5(String nom, String prenom);

    @Test
    public void testCreerClient5() throws InformationManquanteException {
        this.initialisationTestCreerClient5("Boichut","Yohan");
        long id =this.instance.creerClient("Boichut","Yohan");
        Assert.assertEquals(12,id);
    }




}
