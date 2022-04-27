package facade;

import modele.Client;
import modele.ClientNotFoundException;

import java.util.Collection;

public interface SIClient {


    Client getClientById(long id) throws ClientNotFoundException;


    /**
     * Permet de retourner la collection de tous les étudiants inscrits
     * @return tous les étudiants inscrits
     */
    Collection<Client> getAllClients();

    long creerClient(String nom, String prenom);
}
