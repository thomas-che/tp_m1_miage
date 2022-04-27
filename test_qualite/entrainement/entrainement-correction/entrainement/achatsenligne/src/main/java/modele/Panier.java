package modele;

import java.util.Collection;

public interface Panier {
    long getId();

    void addArticle(Article article);

    Collection<Article> getArticles();
}
