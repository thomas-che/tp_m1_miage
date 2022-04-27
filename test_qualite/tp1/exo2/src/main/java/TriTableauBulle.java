public class TriTableauBulle implements TriTableau{
    public Integer[] trier(Integer[] t) {

        int taille = t.length;
        int tmp = 0;
        for(int i=0; i < taille; i++)
        {
            for(int j=1; j < (taille-i); j++)
            {
                if(t[j-1] > t[j])
                {
                    //echanges des elements
                    tmp = t[j-1];
                    t[j-1] = t[j];
                    t[j] = tmp;
                }

            }
        }
        return t;
    }
}
