package ex4;

public class RechercheIndiceTab {
    public int recherchePossition(int tab[], int val) throws IntergerNonTrouveExecption {

        for (int i=0 ; i<tab.length ; i++){
            if(tab[i] == val){
                return i;
            }
        }
        throw new IntergerNonTrouveExecption();
    }
}
