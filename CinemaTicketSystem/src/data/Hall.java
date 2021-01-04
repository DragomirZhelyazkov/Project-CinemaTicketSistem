package data;

import java.io.Serializable;

public class Hall implements Serializable {
    private final int numberOfTheHall;
    private String[][] hallMap;
    private int numberOfSeats;

    public Hall (int number) {
        this.numberOfTheHall = number;
        this.hallMap = createHallMap(number);
    }

    private String[][] createHallMap(int numberOfTheHall) {
        if(numberOfTheHall == 1) {
            numberOfSeats = 49;
            return createHall(7, 7);
        } else {
            numberOfSeats = 36;
            return createHall(6, 6);
        }
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    private String[][] createHall(int row, int column) {
        String [][] arr = new String[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                arr[i][j] = "O";
            }
        } return arr;
    }

    public String[][] getHallMap() {
        return hallMap;
    }

}
