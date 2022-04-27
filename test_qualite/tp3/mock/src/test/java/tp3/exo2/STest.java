package tp3.exo2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class STest {

    private S s;

    @Before
    public void setup(){
        s = new S();
    }

    @Test
    public void exec_0() {
        int n = 0;
        int resAttenduS = 1;

        Assert.assertEquals(resAttenduS, s.exec(n));
    }
}