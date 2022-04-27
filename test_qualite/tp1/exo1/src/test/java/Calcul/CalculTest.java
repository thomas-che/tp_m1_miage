package Calcul;

import org.junit.Assert;
import org.junit.Test;

public class CalculTest {
    private ICalcul c = new Calcul();

    /**
     * x < -1
     */
    @Test
    public void calculerXinfMoins1() {
        double res = c.calculer(-2);
        Assert.assertEquals("x < -1", 0.5773502691896257, res, 0.01);
    }

    /**
     * x > 1
     */
    @Test
    public void calculerXsupp1() {
        double res = c.calculer(2);
        Assert.assertEquals("x > 1", 0.5773502691896257, res, 0.01);
    }

    /**
     * x = 1
     */
    @Test
    public void calculerXequal1() {
        double res = c.calculer(1);
        Assert.assertTrue("x = 1",Double.isInfinite(res));
    }

    /**
     * x = -1
     */
    @Test
    public void calculerXequalMoins1() {
        double res = c.calculer(-1);
        Assert.assertTrue("x = -1",Double.isInfinite(res));
    }

    /**
     * -1 < x < 1
     */
    @Test
    public void calculerXentre1EtMoins1() {
        double res = c.calculer(0.2);
        Assert.assertTrue("-1 < x < 1",Double.isNaN(res));
    }
}