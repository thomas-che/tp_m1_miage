package facade;

import modele.*;

import java.util.*;

public class GestionAchatEnLigneImpl implements GestionAchatEnLigne {

    private SIClient siClient;
    private SICatalogue siCatalogue;
    private Map<Client, Collection<Panier>> paniersClients;
    private Map<Long,Panier> paniers;
    private FabriquePanier fabriquePanier;

    public GestionAchatEnLigneImpl(SIClient siClient, SICatalogue siCatalogue, FabriquePanier fabriquePanier) {
        this.siClient = siClient;
        this.siCatalogue = siCatalogue;
        this.paniers = new HashMap<>();
        this.paniersClients = new HashMap<>();
        this.fabriquePanier = fabriquePanier;
    }



    @Override
    public long creerPanier(long idClient) throws ClientNotFoundException {
        Client client = siClient.getClientById(idClient);
        Panier panier = fabriquePanier.creer();

        Collection<Panier> paniers = this.paniersClients.get(client);
        if (Objects.isNull(paniers)) {
            paniers = new ArrayList<>();
            this.paniersClients.put(client,paniers);
        }

        paniers.add(panier);
        return panier.getId();
    }


    @Override
    public Panier getPanierById(long idClient, long idPanier) throws ClientNotFoundException, PanierNotFoundException {
        Client client = siClient.getClientById(idClient);
        Collection<Panier> paniers = paniersClients.get(client);

        for (Panier panier : paniers) {
            if (panier.getId() == idPanier) {
                return panier;
            }
        }
        throw new PanierNotFoundException();
    }

    @Override
    public void ajouterArticlePanier(long idClient, long idPanier, long idArticle, int quantiteDemandee) throws ClientNotFoundException, ArticleNotFoundException, PanierNotFoundException, StocksInsuffisantsException {
        Panier panier = this.getPanierById(idClient,idPanier);
        Article article = siCatalogue.reserverArticle(idArticle,quantiteDemandee);
        panier.addArticle(article);
    }

    @Override
    public void annulerPanier(long idClient, long idPanier) throws PanierNotFoundException, ClientNotFoundException {
        Panier panier = this.getPanierById(idClient,idPanier);
        for (Article article : panier.getArticles()) {
            siCatalogue.annulerReservation(article.getId(),article.getQuantiteDemandee());
        }
        Client client = this.siClient.getClientById(idClient);
        this.paniersClients.get(client).remove(panier);
    }






}
