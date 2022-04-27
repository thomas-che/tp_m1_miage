package tp3.exo2;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GTest {

    private G g;
    private Generateur genMocked;
    private F fMocked;
    private S sMocked;

    @Before
    public void setup(){
        genMocked = EasyMock.createMockBuilder(Generateur.class)
                .addMockedMethod("exec")
                .createMock();
        fMocked = EasyMock.createMockBuilder(F.class)
                .addMockedMethod("exec")
                .createMock();
        sMocked = EasyMock.createMockBuilder(S.class)
                .addMockedMethod("exec")
                .createMock();
    }

    /**
     * Methode general pour tester
     * @param n
     * @param resF
     * @param resS
     * @param resAttenduG
     * @param coupe
     * @return
     */
    private void runTest(int n, int resF, int resS, int resAttenduG, int coupe){
        g = EasyMock.partialMockBuilder(G.class)
                .withConstructor(int.class, Generateur.class, F.class, S.class)
                .withArgs(coupe, genMocked, fMocked, sMocked)
                .createMock();

        EasyMock.expect(genMocked.exec()).andReturn(n);
        EasyMock.expect(fMocked.exec(n)).andReturn(resF);
        EasyMock.expect(sMocked.exec(n)).andReturn(resS);

        EasyMock.replay(genMocked, fMocked, sMocked, g);

        Assert.assertEquals(resAttenduG, g.exec());
    }

    @Test
    public void exec_coupe1_0() {
        int n = 0;
        int coupe = 1;
        int resF = 1;
        int resS = 1;
        int resAttenduG = 1;

        runTest(n, resF, resS, resAttenduG, coupe);
    }



    @Test
    public void exec_coupe1_1() {
        int n = 1;
        int coupe = 1;

        g = EasyMock.partialMockBuilder(G.class)
                .withConstructor(int.class, Generateur.class, F.class, S.class)
                .withArgs(coupe, genMocked, fMocked, sMocked)
                .createMock();

        int resF = 1;
        int resS = 4;
        int resAttenduG = 4;

        EasyMock.expect(genMocked.exec()).andReturn(n);
        EasyMock.expect(fMocked.exec(n)).andReturn(resF);
        EasyMock.expect(sMocked.exec(n)).andReturn(resS);

        EasyMock.replay(genMocked, fMocked, sMocked, g);

        Assert.assertEquals(resAttenduG, g.exec());
    }

    @Test
    public void exec_coupe1_2() {
        int n = 2;
        int coupe = 1;

        g = EasyMock.partialMockBuilder(G.class)
                .withConstructor(int.class, Generateur.class, F.class, S.class)
                .withArgs(coupe, genMocked, fMocked, sMocked)
                .createMock();

        int resF = 2;
        int resS = 2;
        int resAttenduG = 2;

        EasyMock.expect(genMocked.exec()).andReturn(n);
        EasyMock.expect(fMocked.exec(n)).andReturn(resF);
        EasyMock.expect(sMocked.exec(n)).andReturn(resS);

        EasyMock.replay(genMocked, fMocked, sMocked, g);

        Assert.assertEquals(resAttenduG, g.exec());
    }

    @Test
    public void exec_coupe1_10() {
        int n = 10;
        int coupe = 1;

        g = EasyMock.partialMockBuilder(G.class)
                .withConstructor(int.class, Generateur.class, F.class, S.class)
                .withArgs(coupe, genMocked, fMocked, sMocked)
                .createMock();

        int resF = 89;
        int resS = 4;
        int resAttenduG = 89;

        EasyMock.expect(genMocked.exec()).andReturn(n);
        EasyMock.expect(fMocked.exec(n)).andReturn(resF);
        EasyMock.expect(sMocked.exec(n)).andReturn(resS);

        EasyMock.replay(genMocked, fMocked, sMocked, g);

        Assert.assertEquals(resAttenduG, g.exec());
    }

    @Test
    public void exec_coupe1_19() {
        int n = 19;
        int coupe = 1;

        g = EasyMock.partialMockBuilder(G.class)
                .withConstructor(int.class, Generateur.class, F.class, S.class)
                .withArgs(coupe, genMocked, fMocked, sMocked)
                .createMock();

        int resF = 6765;
        int resS = 4;
        int resAttenduG = 6765;

        EasyMock.expect(genMocked.exec()).andReturn(n);
        EasyMock.expect(fMocked.exec(n)).andReturn(resF);
        EasyMock.expect(sMocked.exec(n)).andReturn(resS);

        EasyMock.replay(genMocked, fMocked, sMocked, g);

        Assert.assertEquals(resAttenduG, g.exec());
    }

    @Test
    public void exec_coupe1_20() {
        int n = 20;
        int coupe = 1;

        g = EasyMock.partialMockBuilder(G.class)
                .withConstructor(int.class, Generateur.class, F.class, S.class)
                .withArgs(coupe, genMocked, fMocked, sMocked)
                .createMock();

        int resF = 10946;
        int resS = 2;
        int resAttenduG = 10946;

        EasyMock.expect(genMocked.exec()).andReturn(n);
        EasyMock.expect(fMocked.exec(n)).andReturn(resF);
        EasyMock.expect(sMocked.exec(n)).andReturn(resS);

        EasyMock.replay(genMocked, fMocked, sMocked, g);

        Assert.assertEquals(resAttenduG, g.exec());
    }

}