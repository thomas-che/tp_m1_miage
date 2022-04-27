public class TriTableauInsertion implements TriTableau{
    @Override
    public Integer[] trier(Integer[] t) {
        int taille = t.length;

        for (int i = 1; i < taille; i++)
        {
            int index = t[i];
            int j = i-1;

            while(j >= 0 && t[j] > index)
            {
                t[j+1] = t[j];
                j--;
            }
            t[j+1] = index;
        }
        return t;
    }
}
