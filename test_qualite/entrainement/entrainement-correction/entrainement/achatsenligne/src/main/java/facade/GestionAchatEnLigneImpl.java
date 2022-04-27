package facade;

import modele.*;

import java.util.*;

public class GestionAchatEnLigneImpl implements GestionAchatEnLigne {

    private SIClient siClient;
    private SICatalogue siCatalogue;
    private Map<Client, Collection<Panier>> paniersClients;
    private Map<Long,Panier> paniers;
    private FabriquePanier fabriquePanier;

    public SIClient getSiClient() {
        return siClient;
    }

    public void setSiClient(SIClient siClient) {
        this.siClient = siClient;
    }

    public SICatalogue getSiCatalogue() {
        return siCatalogue;
    }

    public void setSiCatalogue(SICatalogue siCatalogue) {
        this.siCatalogue = siCatalogue;
    }

    public Map<Client, Collection<Panier>> getPaniersClients() {
        return paniersClients;
    }

    public void setPaniersClients(Map<Client, Collection<Panier>> paniersClients) {
        this.paniersClients = paniersClients;
    }

    public Map<Long, Panier> getPaniers() {
        return paniers;
    }

    public void setPaniers(Map<Long, Panier> paniers) {
        this.paniers = paniers;
    }

    public FabriquePanier getFabriquePanier() {
        return fabriquePanier;
    }

    public void setFabriquePanier(FabriquePanier fabriquePanier) {
        this.fabriquePanier = fabriquePanier;
    }

    public GestionAchatEnLigneImpl(SIClient siClient, SICatalogue siCatalogue, FabriquePanier fabriquePanier) {
        this.siClient = siClient;
        this.siCatalogue = siCatalogue;
        this.paniers = new HashMap<>();
        this.paniersClients = new HashMap<>();
        this.fabriquePanier = fabriquePanier;
    }



    @Override
    public long creerPanier(long idClient) throws ClientNotFoundException {
        Client client = getSiClient().getClientById(idClient);
        Panier panier = getFabriquePanier().creer();

        Collection<Panier> paniers = this.getPaniersClients().get(client);
        if (Objects.isNull(paniers)) {
            paniers = new ArrayList<>();
            this.getPaniersClients().put(client,paniers);
        }

        paniers.add(panier);
        return panier.getId();
    }

    @Override
    public long creerClient(String nom, String prenom) throws InformationManquanteException {
        if (Objects.isNull(nom) || Objects.isNull(prenom) ||
                nom.isBlank() || prenom.isBlank()) {
            throw new InformationManquanteException();
        }

        return this.siClient.creerClient(nom,prenom);
    }


    @Override
    public Panier getPanierById(long idClient, long idPanier) throws ClientNotFoundException, PanierNotFoundException {
        Client client = getSiClient().getClientById(idClient);
        Collection<Panier> paniers = getPaniersClients().get(client);

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
        Article article = getSiCatalogue().reserverArticle(idArticle,quantiteDemandee);
        panier.addArticle(article);
    }

    @Override
    public void annulerPanier(long idClient, long idPanier) throws PanierNotFoundException, ClientNotFoundException {
        Panier panier = this.getPanierById(idClient,idPanier);
        for (Article article : panier.getArticles()) {
            getSiCatalogue().annulerReservation(article.getId(),article.getQuantiteDemandee());
        }
        Client client = this.getSiClient().getClientById(idClient);
        this.getPaniersClients().get(client).remove(panier);
    }






}
