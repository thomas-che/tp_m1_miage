package Calcul;

public class Calcul implements ICalcul{
    @Override
    public double calculer(double... args) {

        assert args.length == 1;

        double x = args[0];

        return Math.sqrt( 1 / (Math.pow(x, 2) -1));
    }
}
