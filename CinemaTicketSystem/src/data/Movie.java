package data;

import java.io.Serializable;
import java.util.ArrayList;


public class Movie implements Serializable {
    private final String movieName;
    private final double moviePrice;
    private final int hallNumber;
    private final ArrayList<Integer> movieDays;
    private final ArrayList<Double> movieHours;


    public Movie(String Name, double Price, int hallNumber, ArrayList<Integer> Days, ArrayList<Double> Hours) {
        this.movieName = Name;
        this.moviePrice = Price;
        this.hallNumber = hallNumber;
        this.movieDays = Days;
        this.movieHours = Hours;
    }

    public String getName() {
        return movieName;
    }

    public double getPrice() {
        return moviePrice;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public ArrayList<Integer> getDays() {
        return movieDays;
    }

    public ArrayList<Double> getHours() {
        return movieHours;
    }
}