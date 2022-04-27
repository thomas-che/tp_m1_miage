package tp3.exo1;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tp3.exo1.Categorie;
import tp3.exo1.CategorieException;
import tp3.exo1.Employe;

public class EmployeTest {

    private final static Double PRECISION = 0.001;


    private Categorie mockCategorie;
    private Employe testedEmploe;

    @Before
    public void setup(){
        mockCategorie = EasyMock.createMockBuilder(Categorie.class)
                            .addMockedMethod("valCategorie")
                            .createMock();

        // ou autre methode
        testedEmploe = new Employe(12, 2000) {
            protected Categorie creerCategorie() {
                return mockCategorie;
            }
        };



    }



    @Test
    public void salaire_OK_categorie1() throws CategorieException {
        // Arrange
        double expected = 2000d;

        // en mode mock partiel
        Integer annee = 12;
        Double salaire = 2000d;

        // Categorie
        EasyMock.expect(mockCategorie.valCategorie(EasyMock.anyInt())).andReturn(1);

        // ou avec un mock partiel sur employe
        testedEmploe = EasyMock.partialMockBuilder(Employe.class)
                .withConstructor(int.class, double.class)
                .withArgs(annee, salaire)
                .addMockedMethod("creerCategorie")
                .createMock();

        EasyMock.expect(testedEmploe.creerCategorie()).andReturn(mockCategorie);
        EasyMock.replay(mockCategorie, testedEmploe);


        // Act
        double actual = testedEmploe.salaire();

        // Assert
        Assert.assertEquals("Categorie 1 : ", expected, actual, PRECISION);
        EasyMock.verify();
    }

    @Test
    public void salaire_OK_categorie2() throws CategorieException {
        // Arrange
        double expected = 2200d;

        // Categorie
        EasyMock.expect(mockCategorie.valCategorie(EasyMock.anyInt())).andReturn(2);
        EasyMock.replay(mockCategorie);

        // Act
        double actual = testedEmploe.salaire();

        // Assert
        Assert.assertEquals("Categorie 2 : ", expected, actual, PRECISION);
        EasyMock.verify();
    }

    @Test
    public void salaire_OK_categorie3() throws CategorieException {
        // Arrange
        double expected = 2400d;

        // Categorie
        EasyMock.expect(mockCategorie.valCategorie(EasyMock.anyInt())).andReturn(3);
        EasyMock.replay(mockCategorie);

        // Act
        double actual = testedEmploe.salaire();

        // Assert
        Assert.assertEquals("Categorie 3 : ", expected, actual, PRECISION);
        EasyMock.verify();
    }

    @Test (expected = CategorieException.class)
    public void salaire_OK_categorie4() throws CategorieException {
        // Arrange
        double expected = 2000d;

        // Categorie
        EasyMock.expect(mockCategorie.valCategorie(EasyMock.anyInt())).andReturn(4);
        EasyMock.replay(mockCategorie);

        // Act
        double actual = testedEmploe.salaire();

        // Assert
        Assert.assertEquals("Categorie 4 : ", expected, actual, PRECISION);
        EasyMock.verify();
    }
}