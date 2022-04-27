package facade;

import modele.Article;
import modele.ArticleNotFoundException;
import modele.StocksInsuffisantsException;

import java.util.Collection;

public interface SICatalogue {




    Article getArticleById(long id) throws ArticleNotFoundException;

    Collection<Article> getAllArticles();

    Article reserverArticle(long idArticle, int quantiteDemandee) throws StocksInsuffisantsException;

    void annulerReservation(long id, int quantiteDemandee);
}
