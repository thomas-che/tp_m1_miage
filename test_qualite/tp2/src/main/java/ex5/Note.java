package ex5;

public class Note implements INote {

    double note;

    @Override
    public double getNote() {
        return note;
    }
    Note(double x){

        note=x;
    }

}
