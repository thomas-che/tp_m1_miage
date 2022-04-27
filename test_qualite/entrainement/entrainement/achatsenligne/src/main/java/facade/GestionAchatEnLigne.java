package facade;

import modele.*;

public interface GestionAchatEnLigne {
    long creerPanier(long idClient) throws ClientNotFoundException;

    Panier getPanierById(long idClient, long idPanier) throws ClientNotFoundException, PanierNotFoundException;

    void ajouterArticlePanier(long idClient, long idPanier, long idArticle, int quantiteDemandee) throws ClientNotFoundException, ArticleNotFoundException, PanierNotFoundException, StocksInsuffisantsException;

    void annulerPanier(long idClient, long idPanier) throws PanierNotFoundException, ClientNotFoundException;
}
