package facade;

import modele.*;

public interface GestionAchatEnLigne {

    /**
     * Permet de créer un panier pour un client existant
     * @param idClient : id du client
     * @return identifiant du panier créé
     * @throws ClientNotFoundException : le client référencé
     * n'existe pas
     */
    long creerPanier(long idClient) throws ClientNotFoundException;

    /**
     * Permet de créer un client
     * @param nom
     * @param prenom
     * @return
     */

    long creerClient(String nom, String prenom) throws InformationManquanteException;


    Panier getPanierById(long idClient, long idPanier) throws ClientNotFoundException, PanierNotFoundException;

    void ajouterArticlePanier(long idClient, long idPanier, long idArticle, int quantiteDemandee) throws ClientNotFoundException, ArticleNotFoundException, PanierNotFoundException, StocksInsuffisantsException;

    void annulerPanier(long idClient, long idPanier) throws PanierNotFoundException, ClientNotFoundException;
}
