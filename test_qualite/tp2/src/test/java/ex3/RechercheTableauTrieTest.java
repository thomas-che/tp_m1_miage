package ex3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RechercheTableauTrieTest {

    private RechercheTableauTrie rechercheTableauTrie;

    @Before
    public void setUp(){
        this.rechercheTableauTrie = new RechercheTableauTrie();
    }

    @Test
    public void isInTabFirst() {
        int tab[] = {1, 2, 3, 4, 5, 6, 7};
        int val = 1;
        int l = tab.length-1;
        boolean res = this.rechercheTableauTrie.isInTab(tab,0,l,val);
        Assert.assertTrue("En premiere position du tab", res);
    }

    @Test
    public void isInTabLast() {
        int tab[] = {1, 2, 3, 4, 5, 6, 7};
        int val = 7;
        int l = tab.length-1;
        boolean res = this.rechercheTableauTrie.isInTab(tab,0,l,val);
        Assert.assertTrue("En dernier position du tab", res);
    }

    @Test
    public void isInTabOut() {
        int tab[] = {1, 2, 3, 4, 5, 6, 7};
        int val = 8;
        int l = tab.length-1;
        boolean res = this.rechercheTableauTrie.isInTab(tab,0,l,val);
        Assert.assertFalse("En dehors tab", res);
    }


}