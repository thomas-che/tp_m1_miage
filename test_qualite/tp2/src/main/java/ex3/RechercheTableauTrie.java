package ex3;

public class RechercheTableauTrie {

    public boolean isInTab(int tab[], int f, int l, int val){
        int mid = (f + l)/2;
        while(f <= l){
            if (tab[mid] < val){
                f = mid + 1;
            }else if(tab[mid] == val){
                return true;
            }else{
                l = mid - 1;
            }
            mid = (f + l)/2;
        }
        return false;
    }
}
