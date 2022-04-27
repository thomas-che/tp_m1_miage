package ex2;

public class Fibonacci {
    public int suite(int iteration){
        if(iteration == 0){
            return 0;
        }
        if(iteration == 1){
            return 1;
        }
        int nbr1=0, nbr2=1, nbr3=0, i;
        for(i=2; i<iteration; ++i)
        {
            nbr3 = nbr1 + nbr2;
            nbr1 = nbr2;
            nbr2 = nbr3;
        }
        return nbr3;
    }
}
