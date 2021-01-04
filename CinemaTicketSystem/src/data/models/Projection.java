package data.models;

import data.Hall;
import data.Movie;

import java.io.Serializable;
import java.util.*;

public class Projection implements Serializable {
    private Movie movie;
    private double projectionHour;
    private int projectionHallNumber;
    private String [][] hallDiagram;
    private Hall hall;
    private ArrayList<String> mySeats;
    private int day;

    public Projection(Movie movie, double projectionHour, int day) {
        this.movie = movie;
        this.projectionHour = projectionHour;
        this.projectionHallNumber = movie.getHallNumber();
        this.hall = new Hall(projectionHallNumber);
        this.hallDiagram = hall.getHallMap();
        this.mySeats = new ArrayList<>();
        this.day = day;
    }

    public String[][] getHallDiagram() {
        return hallDiagram;
    }

    public void changeHallDiagram(int numberOfSeats){
        int counter = 1;
        mySeats.clear();
        for (int i = 0; i < hallDiagram.length; i++) {
            for (int j = 0; j < hallDiagram[0].length; j++) {
                if(hallDiagram[i][j].equals("O")) {
                    hallDiagram[i][j] = "X";
                    mySeats.add("red:" + (i+1)  + ", " + "mqsto:" + (j+1) +"; ");
                    counter++;
                    if(counter > numberOfSeats)return;
                } } } }


    public void printProjectionDiagram() {
        for (int i = 0; i < hallDiagram.length; i++) {
            for (int j = 0; j < hallDiagram[0].length; j++) {
                System.out.print(hallDiagram[i][j]);
            }System.out.println();
        }
    }

    public ArrayList<String> getMySeats() {//какво точно да връща
        return mySeats;
    }

    public double getProjectionHour() {
        return projectionHour;
    }

    public int getProjectionHallNumber() {
        return projectionHallNumber;
    }

    public int getDay() {
        return day;
    }

}
