package tp3.exo1;

public class Employe {
    int annee;//anciennete dans l entreprise
    double salaireDeBase;//salaire de depart a l embauche

    public Employe(int a, double s){
        annee=a;
        salaireDeBase=s;
    }

    public int getAnnee(){return annee;}

    public double getSalaireDeBase() {
        return salaireDeBase;
    }

    public void setAnnee(int a){this.annee=a;}

    public void setSalaireDeBase(double s){this.salaireDeBase=s;}

    protected Categorie creerCategorie() {
        return new Categorie();
    }

    //salaire effectif de l employe calcule selon son anciennete sa categorie
    public double salaire() throws CategorieException {

        Categorie categorie = this.creerCategorie();

        switch (categorie.valCategorie(annee)){
            case 1: return salaireDeBase;
            case 2: return salaireDeBase*1.1;
            case 3: return salaireDeBase*1.2;
            default: throw new CategorieException();
        }
    }
}
