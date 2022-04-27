import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class TriTableauTest {

    // ca nous fournie l'impl du tri à tester
    abstract protected TriTableau supplyShorter();

    @Test
    public void  trierTableauAleatoir(){

        Integer[] aTrier = {3,9,5,2,4};
        Integer[] trier = {2,3,4,5,9};

        Integer[] leTabTrie = supplyShorter().trier(aTrier);

        Assert.assertArrayEquals("Pb trierTableauAleatoir", trier,leTabTrie);

    }

    @Test
    public void  trierTableauALEnvers(){

        Integer[] aTrier = {9,5,4,3,2};
        Integer[] trier = {2,3,4,5,9};

        Integer[] leTabTrie = supplyShorter().trier(aTrier);

        Assert.assertArrayEquals("Pb trierTableauALEnvers", trier,leTabTrie);

    }

    @Test
    public void  trierTableauDejaTrie(){

        Integer[] aTrier = {2,3,4,5,9};
        Integer[] trier = {2,3,4,5,9};

        Integer[] leTabTrie = supplyShorter().trier(aTrier);

        Assert.assertArrayEquals("Pb trierTableauDejaTrie", trier,leTabTrie);

    }


}