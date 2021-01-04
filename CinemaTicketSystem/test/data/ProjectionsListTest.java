package data;

import data.models.Projection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProjectionsListTest {
    ArrayList<Integer> arrDatesThor1 = new ArrayList<>();
    ArrayList<Integer> arrDatesThor2 = new ArrayList<>();
    ArrayList<Double> arrHoursThor1 = new ArrayList<>();
    ArrayList<Double> arrHoursThor2 = new ArrayList<>();
    Movie movie1 = new Movie("Thor", 1, 1, arrDatesThor1, arrHoursThor1);
    Movie movie2 = new Movie("Thor2", 1, 2, arrDatesThor2, arrHoursThor2);
    Projection proj1 = new Projection(movie1, 22, 2);
    Projection proj2 = new Projection(movie2, 20, 2);
    ProjectionsList projectionsList = ProjectionsList.getInstance();


    @Before
    public void initClassFields(){
        arrDatesThor1.add(2);
        arrHoursThor1.add(22.00);
        arrDatesThor2.add(2);
        arrHoursThor2.add(20.00);
        projectionsList.addProjection(proj1);
        projectionsList.addProjection(proj2);
    }
    // в реалната програма създавам ли тези 2 прожекции? трия ли ги? трябва ли???????????

    @After
    public void clear(){
        projectionsList.clearProjections();
    }

    @Test
    public void testCheckIfProjectionExistWhenCorrectParametersThenTrue(){
        Assert.assertTrue("Not expected result", projectionsList.checkIfProjectionExist(1, 22.00, 2));
    }

    @Test
    public void testCheckIfProjectionExistWhenIncorrectParametersThenFalse(){
        Assert.assertFalse("Not expected result", projectionsList.checkIfProjectionExist(1, 20.00, 2));
    }
    @Test
    public void testCheckIfProjectionExistWhenIncorrectParameters2ThenFalse(){
        Assert.assertFalse("Not expected result", projectionsList.checkIfProjectionExist(1, 22.00, 1));
    }

    @Test
    public void testCheckIfProjectionExistWhenIncorrectParameters3ThenFalse(){
        Assert.assertFalse("Not expected result", projectionsList.checkIfProjectionExist(3, 22.00, 2));
    }
}
