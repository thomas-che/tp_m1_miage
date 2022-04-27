package facade;

import facade.exceptions.*;
import modele.Match;
import modele.Pari;
import modele.Utilisateur;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

public interface FacadeParis {

    /****
     * Nous aurons dans toutes les futures instances les données suivantes :
     * Deux utilisateurs yo/yo et fred/fred déjà enregistrés. Fred aura le rôle d'admin.
     *
     * 4 matchs déjà enregistrés (sport/ match/ date de l'évènement)
     * foot/Allemagne-France/date d'execution du programme + 5 minutes
     * rugby/France-Australie/date d'exécution du programme - 7 jours
     * foot/ France-Australie / date d'exécution du programme + 5 mois
     * foot/ France-Perou / date d'exécution du programme + 4 mois
     * foot/ Danemark - France / date d'exécution du programme + 9 mois
     *
     *
     * yo a parié nul sur le premier match avec la somme de 35 euros
     * Fred a parié France sur ce même match pour la somme de 20 euros et la victoire de la France contre l'allemagne.
     * yo a parié la somme de 110 euros pour la victoire de la France contre l'australie en foot
     * Fred aura validé en tant qu'admin le résultat nul du premier match.
     */



    /**
     * Permet de se connecter à l'application si le couple login/mdp est valide
     * @param login
     * @param mdp
     * @throws UtilisateurDejaConnecteException
     * @throws InformationsSaisiesIncoherentesException
     * @return L'utilisateur connecté
     */
    Utilisateur connexion(String login, String mdp) throws UtilisateurDejaConnecteException, InformationsSaisiesIncoherentesException;

    /**
     * Permet de retourner la liste des matchs sur lesquels on peut encore parier.
     * @return les matchs qui ne sont pas encore commencés
     */
    Collection<Match> getMatchsPasCommences();

    /**
     * Permet de parier sur un match
     * @param login : le login de l'utilisateur qui parie
     * @param idMatch : l'id du match sur lequel porte le pari
     * @param vainqueur : le nom du vainqueur
     * @param montant : le montant parié
     * @return l'id unique du Pari enregistré
     * @throws MatchClosException si le match est déjà commencé ou fini
     * @throws ResultatImpossibleException si le vainqueur n'est pas correct (nul, equipe 1 ou 2)
     */
    long parier(String login, long idMatch, String vainqueur, double montant) throws MatchClosException, ResultatImpossibleException;

    /**
     * Permet d'annuler un pari sur un match
     * @param login : le login de l'utilisateur qui parie
     * @param idPari : l'id du pari à annuler
     * @throws OperationNonAuthoriseeException : si l'utilisateur n'a pas le droit (pas admin et ce n'est pas lui le parieur)
     * ou si le match est commencé/passé
     */
    void annulerPari(String login, long idPari) throws OperationNonAuthoriseeException;

    /**
     * Permet de récupérer un match par son id.
     * @param idMatch : l'id du match sur lequel porte le pari
     * @return le match
     */
    Match getMatch(long idMatch);

    /**
     * Permet de récupérer un pari par son id.
     * @param idPari : l'id du pari
     * @return le pari
     */
    Pari getPari(long idPari);

    /**
     * Permet de retourner la liste des paris d'un utilisateur.
     * @return tous les paris d'un utilisateur (terminés et en cours)
     */
    Collection<Pari> getMesParis(String login);

    /**
     * Permet d'ajouter un match sur lequel parier
     * l'utilisateur doit être admin
     * @param login
     * @param sport
     * @param equipe1
     * @param equipe2
     * @param quand
     * @throws UtilisateurNonAdminException si l'utilisateur qui ajoute le match n'est pas admin
     * @return l'idMatch unique du match ajouté
     */
    long ajouterMatch(String login, String sport, String equipe1, String equipe2, LocalDateTime quand)
            throws UtilisateurNonAdminException;


    /**
     * Permet de fixer le résultat d'un match et de distribuer les gains aux parieurs
     * l'utilisateur doit être admin
     * @param login
     * @param idMatch
     * @param resultat
     * @throws UtilisateurNonAdminException si l'utilisateur qui ajoute le match n'est pas admin
     * @throws ResultatImpossibleException si le resultat n'est pas correct (nul, equipe 1 ou 2)
     */
    void resultatMatch(String login, long idMatch, String resultat) throws UtilisateurNonAdminException, ResultatImpossibleException;


    /**
     * Permet de déconnecter proprement l'utilisateur (login) de l'application
     * @param login
     */
    void deconnexion(String login);


    /**
     * Permet de retourner la liste de tous les paris.
     * @return les paris
     */
    Collection<Pari> getAllParis();

    /**
     * Permet de retourner la liste de tous les matchs.
     * @return les matchs
     */
    Collection<Match> getAllMatchs();

}
