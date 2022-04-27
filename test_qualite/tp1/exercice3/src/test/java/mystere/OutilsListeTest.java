package mystere;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OutilsListeTest {

    private OutilsListe outilsListe;

    @Test
    public void estElementDe() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(5);
        list.add(9);
        this.outilsListe = new OutilsListe(list);

        Assert.assertTrue("estElementDe", outilsListe.estElementDe(4));

    }

    @Test
    public void estElementDeDernierEleme() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(5);
        list.add(9);
        this.outilsListe = new OutilsListe(list);

        Assert.assertTrue("estElementDeDernierEleme", outilsListe.estElementDe(9));

        // dernier elem pas trouver alors que dans la liste

    }
}