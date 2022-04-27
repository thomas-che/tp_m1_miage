package ex1;

public class Solde {

    public double solder(double prix, int categorie) throws NotCategorieExecption, PrixNegatifException {
        if(prix < 0){
            throw new PrixNegatifException();
        }
        switch (categorie){
            case 1: {
                return prix*0.8;
            }
            case 2: {
                return prix*0.5;
            }
            case 3: {
                return prix*0.3;
            }
            default:{
                throw new NotCategorieExecption();
            }
        }
    }
}
