package ex4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RechercheIndiceTabTest {

    private RechercheIndiceTab rechercheIndiceTab;
    private int tab[] = {1, 2, 4, 1, 1, 3, 4, 5, 6, 7};

    @Before
    public void setUp(){
        this.rechercheIndiceTab = new RechercheIndiceTab();
    }

    @Test
    public void recherchePossitionFirst() throws IntergerNonTrouveExecption {
        int val = 1;
        int res = this.rechercheIndiceTab.recherchePossition(tab,val);
        Assert.assertEquals("Premier element", 0, res);
    }

    @Test
    public void recherchePossitionLast() throws IntergerNonTrouveExecption {
        int val = 7;
        int res = this.rechercheIndiceTab.recherchePossition(tab,val);
        Assert.assertEquals("Last element", 9, res);
    }

    @Test (expected = IntergerNonTrouveExecption.class)
    public void recherchePossitionNonTrouve() throws IntergerNonTrouveExecption {
        int val = -1;
        int res = this.rechercheIndiceTab.recherchePossition(tab,val);
        // leve execption
    }
}