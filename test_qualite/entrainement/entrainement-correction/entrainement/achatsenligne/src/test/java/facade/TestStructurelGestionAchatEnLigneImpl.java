package facade;

import modele.*;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TestStructurelGestionAchatEnLigneImpl {


    @Test
    /**
     * Test où on rentre dans le IF
     */
    public void testCreerPanier1() throws ClientNotFoundException {
        SIClient mockSIClient = EasyMock.createMock(SIClient.class);
        FabriquePanier mockFabriquePanier = EasyMock.createMock(FabriquePanier.class);
        Panier mockPanier = EasyMock.createMock(Panier.class);
        Client mockClient = EasyMock.createMock(Client.class);
        Map<Client, Collection<Panier>> paniersClients = new HashMap<>();
        long idClient = 1;

        GestionAchatEnLigneImpl instance =
                EasyMock.partialMockBuilder(GestionAchatEnLigneImpl.class)
                        .addMockedMethod("getSiClient")
                        .addMockedMethod("getFabriquePanier")
                        .addMockedMethod("getPaniersClients")
                        .createMock();

        EasyMock.expect(instance.getSiClient()).andReturn(mockSIClient);
        EasyMock.expect(instance.getFabriquePanier()).andReturn(mockFabriquePanier);
        EasyMock.expect(instance.getPaniersClients())
                .andReturn(paniersClients)
                .times(2);

        EasyMock.expect(mockFabriquePanier.creer()).andReturn(mockPanier);
        EasyMock.expect(mockSIClient.getClientById(idClient)).andReturn(mockClient);
        EasyMock.expect(mockPanier.getId()).andReturn(123l);

        EasyMock.replay(mockClient,mockPanier,mockSIClient,
                mockFabriquePanier,instance);

        long idPanier =instance.creerPanier(idClient);
        Assert.assertEquals(123l,idPanier);
        Assert.assertNotNull(paniersClients.get(mockClient));
    }


    @Test(expected = ClientNotFoundException.class)
    /**
     * Test où ClientNotFoundException
     */
    public void testCreerPanier2() throws ClientNotFoundException {
        SIClient mockSIClient = EasyMock.createMock(SIClient.class);
        long idClient = 1;
        GestionAchatEnLigneImpl instance =
                EasyMock.partialMockBuilder(GestionAchatEnLigneImpl.class)
                        .addMockedMethod("getSiClient")
                        .createMock();

        EasyMock.expect(instance.getSiClient()).andReturn(mockSIClient);
        EasyMock.expect(mockSIClient.getClientById(idClient))
                .andThrow(new ClientNotFoundException());
        EasyMock.replay(mockSIClient,instance);

        long idPanier =instance.creerPanier(idClient);
     }




    @Test
    /**
     * Test où on ne rentre pas dans le IF
     */
    public void testCreerPanier3() throws ClientNotFoundException {
        SIClient mockSIClient = EasyMock.createMock(SIClient.class);
        FabriquePanier mockFabriquePanier = EasyMock.createMock(FabriquePanier.class);
        Panier mockPanier = EasyMock.createMock(Panier.class);
        Client mockClient = EasyMock.createMock(Client.class);
        Collection<Panier> panierCollection = new ArrayList<>();

        Map<Client, Collection<Panier>> paniersClients = new HashMap<>();
        paniersClients.put(mockClient,panierCollection);
        long idClient = 1;

        GestionAchatEnLigneImpl instance =
                EasyMock.partialMockBuilder(GestionAchatEnLigneImpl.class)
                        .addMockedMethod("getSiClient")
                        .addMockedMethod("getFabriquePanier")
                        .addMockedMethod("getPaniersClients")
                        .createMock();

        EasyMock.expect(instance.getSiClient()).andReturn(mockSIClient);
        EasyMock.expect(instance.getFabriquePanier()).andReturn(mockFabriquePanier);
        EasyMock.expect(instance.getPaniersClients())
                .andReturn(paniersClients)
                .times(1);

        EasyMock.expect(mockFabriquePanier.creer()).andReturn(mockPanier);
        EasyMock.expect(mockSIClient.getClientById(idClient)).andReturn(mockClient);
        EasyMock.expect(mockPanier.getId()).andReturn(123l);

        EasyMock.replay(mockClient,mockPanier,mockSIClient,
                mockFabriquePanier,instance);

        long idPanier =instance.creerPanier(idClient);
        Assert.assertEquals(123l,idPanier);
        Assert.assertEquals(1,paniersClients.get(mockClient).size());
    }


    /**
     * RunTimeException occured : NullPointer
     * On aurait dû déclencher PanierNotFoundException
     * Problème de conception !
     * @throws ClientNotFoundException
     * @throws PanierNotFoundException
     */
    @Test(expected = PanierNotFoundException.class)
    public void testGetPanierById() throws ClientNotFoundException, PanierNotFoundException {
        SIClient mockSIClient = EasyMock.createMock(SIClient.class);
        Client mockClient = EasyMock.createMock(Client.class);

        Map<Client, Collection<Panier>> paniersClients =
                new HashMap<>();
        long idPanier = 123l;
        long idClient = 1;

        GestionAchatEnLigneImpl instance =
                EasyMock.partialMockBuilder(GestionAchatEnLigneImpl.class)
                        .addMockedMethod("getSiClient")
                        .addMockedMethod("getPaniersClients")
                        .createMock();

        EasyMock.expect(instance.getSiClient()).andReturn(mockSIClient);
        EasyMock.expect(instance.getPaniersClients()).andReturn(paniersClients);
        EasyMock.expect(mockSIClient.getClientById(idClient)).andReturn(mockClient);

        EasyMock.replay(mockClient,mockSIClient,instance);
        instance.getPanierById(idClient,idPanier);
    }


    /**
     *
     * Utilisation du constructeur mais on garde le contrôle sur la map des paniers en mockant son getter
     * @throws ClientNotFoundException
     * @throws PanierNotFoundException
     */
    @Test(expected = PanierNotFoundException.class)
    public void testGetPanierById2() throws ClientNotFoundException, PanierNotFoundException {
        SIClient mockSIClient = EasyMock.createMock(SIClient.class);
        SICatalogue mockSICatalogue = EasyMock.createMock(SICatalogue.class);
        FabriquePanier mockFabriquePanier = EasyMock.createMock(FabriquePanier.class);

        Client mockClient = EasyMock.createMock(Client.class);

        Map<Client, Collection<Panier>> paniersClients =
                new HashMap<>();

        paniersClients.put(mockClient,new ArrayList<>());
        long idPanier = 123l;
        long idClient = 1;

        GestionAchatEnLigneImpl instance =
    EasyMock.partialMockBuilder(GestionAchatEnLigneImpl.class)
            .withConstructor(SIClient.class,SICatalogue.class,FabriquePanier.class)
            .withArgs(mockSIClient,mockSICatalogue,mockFabriquePanier)
            .addMockedMethods("getPaniersClients")
            .createMock();


        EasyMock.expect(instance.getPaniersClients()).andReturn(paniersClients);
        EasyMock.expect(mockSIClient.getClientById(idClient)).andReturn(mockClient);

        EasyMock.replay(mockClient,mockSIClient,instance,mockSICatalogue,mockFabriquePanier);
        instance.getPanierById(idClient,idPanier);
    }



    /**
     *
     * Utilisation du constructeur mais on garde le contrôle sur
     * la map des paniers en mockant son getter
     * @throws ClientNotFoundException
     * @throws PanierNotFoundException
     */
    @Test
    public void testGetPanierById3() throws ClientNotFoundException, PanierNotFoundException {
        SIClient mockSIClient = EasyMock.createMock(SIClient.class);
        SICatalogue mockSICatalogue = EasyMock.createMock(SICatalogue.class);
        FabriquePanier mockFabriquePanier = EasyMock.createMock(FabriquePanier.class);
        Panier mockPanier = EasyMock.createMock(Panier.class);


        Client mockClient = EasyMock.createMock(Client.class);

        Map<Client, Collection<Panier>> paniersClients =
                new HashMap<>();

        Collection<Panier> mesPaniers = new ArrayList<>();
        mesPaniers.add(mockPanier);

        paniersClients.put(mockClient,mesPaniers);
        long idPanier = 123l;
        long idClient = 1;

        GestionAchatEnLigneImpl instance =
                EasyMock.partialMockBuilder(GestionAchatEnLigneImpl.class)
                        .withConstructor(SIClient.class,SICatalogue.class,FabriquePanier.class)
                        .withArgs(mockSIClient,mockSICatalogue,mockFabriquePanier)
                        .addMockedMethods("getPaniersClients")
                        .createMock();

        EasyMock.expect(mockPanier.getId()).andReturn(idPanier);
        EasyMock.expect(instance.getPaniersClients()).andReturn(paniersClients);
        EasyMock.expect(mockSIClient.getClientById(idClient)).andReturn(mockClient);

        EasyMock.replay(mockClient,mockSIClient,instance,mockSICatalogue,mockFabriquePanier,mockPanier);
        Panier resultat = instance.getPanierById(idClient,idPanier);

        Assert.assertTrue(resultat == mockPanier);
    }


}
