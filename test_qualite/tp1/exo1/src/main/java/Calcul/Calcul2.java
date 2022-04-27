package Calcul;

public class Calcul2 implements ICalcul{

    @Override
    public double calculer(double... args) {
        assert args.length == 3;

        double x = args[0];
        double y = args[1];
        double z = args[2];

        return Math.sqrt( (y*(z-1))/(Math.pow(x,2)-1) );
    }
}
