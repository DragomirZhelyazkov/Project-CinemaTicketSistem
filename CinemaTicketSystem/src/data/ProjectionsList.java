package data;

import data.models.Projection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjectionsList implements Serializable {
    private static final ProjectionsList instance = new ProjectionsList();
    public static ProjectionsList getInstance() {
        return instance;
    }

    private List<Projection> projections;

    private ProjectionsList() {
        projections = new ArrayList<>();
    }



    public boolean checkIfProjectionExist(int hallNumber, double hour, int day) {
        for(Projection proj : projections){
            if(hour == proj.getProjectionHour() && hallNumber == proj.getProjectionHallNumber() && day == proj.getDay()){
                return true;
            }
        } return false;
    }

    public boolean checkIfProjectionHasEnoughSeats(int hallNumber, double hour, int numberOfSeats, int day){
        Hall hall = new Hall(hallNumber);
        if(checkIfProjectionExist(hallNumber, hour, day)) {
            Projection projection = getProjectionByHallNumberAndHour(hallNumber, hour, day);
            String[][] arr = projection.getHallDiagram();
            int counter = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j].equals("O")) {
                        counter++;
                    } } }
            return numberOfSeats <= counter;
        }else {
            if(numberOfSeats > hall.getNumberOfSeats()) {
                System.out.println("The Hall has " + hall.getNumberOfSeats() + " seats.");
                return false;
            } return true; }
    }

    public boolean checkIfProjectionHasNoSeats(int hallNumber, double hour, int day) {
        if(checkIfProjectionExist(hallNumber, hour, day)) {
            Projection projection = getProjectionByHallNumberAndHour(hallNumber, hour, day);
            String[][] arr = projection.getHallDiagram();
            int counter = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j].equals("O")) {
                        counter++;
                    }
                }
            }
            return counter == 0;
        } return false;
    }

    public Projection getProjectionByHallNumberAndHour(int hallNumber, double hour, int day){
        Projection projection = null;
        for(Projection proj : projections){
            if(hour == proj.getProjectionHour() && hallNumber == proj.getProjectionHallNumber() && day == proj.getDay()){
                projection = proj;
            }
        } return projection;
    }

    public void changeProjectionDiagram(int hallNumber, double hour, int numberOfSeats, int day){
        Projection projection = getProjectionByHallNumberAndHour(hallNumber, hour, day);
        assert projection != null;
        projection.changeHallDiagram(numberOfSeats);
    }

    public void addProjection(Projection projection) {
        projections.add(projection);
    }

    public void clearProjections() {
        projections.clear();
    }

    public List<Projection> getProjections() {
        return projections;
    }

    public void setProjectionsList(ArrayList<Projection> list){
        this.projections = list;
    }
}
