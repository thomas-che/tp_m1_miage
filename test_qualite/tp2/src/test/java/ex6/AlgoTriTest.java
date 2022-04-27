package ex6;

import ex1.Solde;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AlgoTriTest {

    private AlgoTri algoTri;

    @Before
    public void setUp(){
        this.algoTri = new AlgoTri();
    }

    @Test
    public void tri() {
        List<Integer> l = new ArrayList<>();
        l.add(8);
        l.add(12);
        l.add(2);
        l.add(4);
        l.add(22);

        List<Integer> res = this.algoTri.tri(new ArrayList<>(l));
        Collections.sort(l);
        Assert.assertEquals("Tri tu tableau", l, res);
    }
}