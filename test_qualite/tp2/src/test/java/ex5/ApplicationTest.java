package ex5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicationTest {

    private Application application;
    private final static double PRECISION = 0.01;

    @Before
    public void setUp(){
        this.application = new Application();
    }

    @Test
    public void computeAverageVide() {
        List<INote> listNotes = new ArrayList<>();
        double res = application.computeAverage(listNotes);
        Assert.assertEquals("Collection vide", 0, res, PRECISION);
    }

    @Test
    public void computeAverage3Notes() {
        List<INote> listNotes = new ArrayList<>();
        listNotes.add(new Note(10));
        listNotes.add(new Note(10));
        listNotes.add(new Note(10));
        double res = application.computeAverage(listNotes);
        Assert.assertEquals("Moyenne sur 3 notes", 10, res, PRECISION);
    }

}