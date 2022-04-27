package tp3.exo2;

public class G {
    private int coupe;
    private Generateur gen;
    private F f;
    private S s;

    public G(int coupe, Generateur gen, F f, S s) {
        this.coupe = coupe;
        this.gen = gen;
        this.f = f;
        this.s = s;
    }

    public int exec(){
        int n = gen.exec();
        int resF = f.exec(n);
        int resS = s.exec(n);
        if( (resF+resS) < coupe ){
            return (resF+resS);
        }
        else {
            return resF<resS ? resS : resF;
        }
    }
}
