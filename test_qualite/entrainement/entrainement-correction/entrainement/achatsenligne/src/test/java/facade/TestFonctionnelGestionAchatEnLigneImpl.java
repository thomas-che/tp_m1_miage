package facade;

import modele.Client;
import modele.ClientNotFoundException;
import modele.FabriquePanier;
import modele.Panier;
import org.easymock.EasyMock;

public class TestFonctionnelGestionAchatEnLigneImpl extends TestFonctionnelGestionAchatEnLigne {
    private SIClient siClient;
    private SICatalogue siCatalogue;
    private FabriquePanier fabriquePanier;


    @Override
    public GestionAchatEnLigne getInstance() throws ClientNotFoundException {
        this.siCatalogue = EasyMock.createMock(SICatalogue.class);
        this.siClient = EasyMock.createMock(SIClient.class);
        this.fabriquePanier = EasyMock.createMock(FabriquePanier.class);

        return new GestionAchatEnLigneImpl(siClient,siCatalogue,fabriquePanier);
    }

    @Override
    public void initialisationTestCreationPanier1(String nom, String prenom) {
        Client client = EasyMock.createMock(Client.class);
        Panier panier = EasyMock.createMock(Panier.class);

        EasyMock.expect(fabriquePanier.creer()).andReturn(panier);
        EasyMock.expect(panier.getId()).andReturn(123l);
        EasyMock.expect(this.siClient.creerClient(nom,prenom)).andReturn(1l);
        try {
            EasyMock.expect(this.siClient.getClientById(1l)).andReturn(client);
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }

        EasyMock.replay(this.siCatalogue,this.siClient,client,panier,this.fabriquePanier);


    }

    @Override
    public void initialisationTestCreationPanier2(long idClient) {

        try {
            EasyMock.expect(this.siClient.getClientById(12l)).andThrow(new ClientNotFoundException());
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }
        EasyMock.replay(this.siCatalogue,this.siClient,this.fabriquePanier);

    }

    @Override
    public void initialisationTestCreerClient1() {
        EasyMock.replay(this.siClient,this.siCatalogue,this.fabriquePanier);
    }

    @Override
    public void initialisationTestCreerClient5(String nom, String prenom) {
        EasyMock.expect(siClient.creerClient(nom,prenom)).andReturn(12l);
        EasyMock.replay(this.siClient,this.siCatalogue,this.fabriquePanier);
    }
}
