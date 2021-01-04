package data.models;

import data.Movie;
import org.junit.Test;
import java.util.ArrayList;

public class ProjectionTest {

    @Test
    public void testPrintHallDiagramWhenNumberOfSeatsIsThree(){
        ArrayList<Integer> arrDays = new ArrayList<>();
        arrDays.add(2);
        ArrayList<Double> arrHours = new ArrayList<>();
        arrDays.add(22);
        Movie movie = new Movie("Thor", 1, 1, arrDays, arrHours);
        Projection proj = new Projection(movie, 20, 2);
        proj.changeHallDiagram(3);
        proj.printProjectionDiagram();
    }

    /*  Не тестваме changeHallDiagram с параметър Стринг, Null, -1 или по голям от броя на местата в залата защото
    програмата няма да ни позволи да въведем параметър  */
}
