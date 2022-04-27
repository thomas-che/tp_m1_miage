package ex1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SoldeTest {

    private Solde solde;
    private final static double PRECISION = 0.01;

    @Before
    public void setUp(){
        this.solde = new Solde();
    }

    @Test
    public void solderCategorie1() throws NotCategorieExecption, PrixNegatifException {
        double res = solde.solder(100, 1);
        Assert.assertEquals("Catégorie 1 ", 80, res, PRECISION);
    }

    @Test
    public void solderCategorie2() throws NotCategorieExecption, PrixNegatifException {
        double res = solde.solder(100, 2);
        Assert.assertEquals("Catégorie 2 ", 50, res, PRECISION);
    }

    @Test
    public void solderCategorie3() throws NotCategorieExecption, PrixNegatifException {
        double res = solde.solder(100, 3);
        Assert.assertEquals("Catégorie 3 ", 30, res, PRECISION);
    }

    @Test (expected = NotCategorieExecption.class)
    public void solderCategorieInconnut() throws NotCategorieExecption, PrixNegatifException {
        double res = solde.solder(100, 4);
        // leve exception
        Assert.assertEquals("Catégorie 4", 30, res, PRECISION);
    }

    @Test (expected = PrixNegatifException.class)
    public void solderPrixNegatif() throws NotCategorieExecption, PrixNegatifException {
        double res = solde.solder(-100, 3);
        // leve exception
        Assert.assertEquals("Catégorie 4", 30, res, PRECISION);
    }
}