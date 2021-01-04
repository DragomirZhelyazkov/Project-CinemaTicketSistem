package io;

import data.Movie;
import data.consts.AdminOption;
import data.consts.ClientOption;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class ConsoleDevice {

    private final Scanner scanner;
    public ConsoleDevice() {
        this.scanner = new Scanner(System.in);
    }

    public void showLoginMessage() {
        print("No logged user. Please, login.");
    }

    public String getUsernameFromUser() {
        print("Enter username:");
        return getStringFromUser();
    }

    public String getPasswordFromUser() {
        print("Enter password:");
        return getStringFromUser();
    }

    public void showErrorWrongLoginCredentials() {
        print("Wrong username or password.");
    }

    public void showAdminOptions() {
        print("Press " + AdminOption.ADD_MOVIE.value() + " to add movie\n" +
                "Press " + AdminOption.LOGOUT.value() + " to logout");
    }

    public AdminOption getAdminOptionFromUser() {
        int userInput;
        do {
            print("Enter a valid admin option:");
            userInput = getNumberFromUser();
        } while (AdminOption.convertToAdminOption(userInput) == null);
        return AdminOption.convertToAdminOption(userInput);
    }

    public void showClientOptions() {
        print("Press " + ClientOption.BUY_TICKET.value() + " to buy a Ticket\n" +
                "Press " + ClientOption.LOGOUT.value() + " to Logout");
    }

    public ClientOption getClientOption() {
        int userInput;
        do {
            print("Enter a valid client option:");
            userInput = getNumberFromUser();
        } while (ClientOption.convertToClientOption(userInput) == null);
        return ClientOption.convertToClientOption(userInput);
    }

    public int getNumberFromUser() {
        int number;
        try {
            number = Integer.parseInt(scanner.nextLine());
            if(number <= 0){
                print("Not a valid number. Try again:");
                return getNumberFromUser();
            }
        } catch (NumberFormatException e) {
            print("Not a valid number. Try again:");
            return getNumberFromUser();
        }
        return number;
    }

    private String getStringFromUser() {
        return scanner.nextLine();
    }

    public String getMovieNameFromAdmin() {
        print("Input movie Name");
        String movieName = scanner.nextLine();
        if (movieName != null && !movieName.equals("")) {
            return movieName;
        }
        else {
            System.out.println("Not a valid name");
            return  getMovieNameFromAdmin();
        }
    }

    public double getMoviePriceFromAdmin() {
        double prise;
        print("Input movie Prise");
        prise = getNumberFromUser();
        if(prise > 0) {
            return prise;
        } else {
            print("Not a valid Prise. Try again:");
            return getMoviePriceFromAdmin();
        }
    }

    public int getHallNumberFromAdmin() {
        print("Input Hal Number");
        int number = getNumberFromUser();
        if(number == 1 || number == 2) {
            return number;
        } else {
            print("Not a valid number. Try again:");
            return getHallNumberFromAdmin();
        }
    }

    public ArrayList<Integer> getMovieDaysFromAdmin() {
        Calendar cal = Calendar.getInstance();
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int d, count;
        ArrayList<Integer> dates = new ArrayList<>();
        print("Input Number of days for a movie (between 1 and 7)");
        count = getNumberFromUser();
        print("Input Dates for a movie");
        for (int i = 0; i < count; i++) {
            d = getNumberFromUser();
            if (d > 0 && d <= days && !dates.contains(d)){
                dates.add(d);
            } else{
                print("Not a valid Day. Try again:");
                return getMovieDaysFromAdmin();
            }
        } return dates;
    }

    private double getValidHourFromUser(){
        double hour;
        try {
            hour = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            print("Not a valid hour. Try again:");
            return getValidHourFromUser();
        }
        if (hour > 0 && hour < 24) {
            return hour;
        } else {
            print("Not a valid Hour. Try again:");
            return getValidHourFromUser();
        }
    }

    public ArrayList<Double> getMovieHoursFromAdmin() {
        int count;
        ArrayList<Double> hours = new ArrayList<>();
        print("Input Number of projections for a day");
        count = getNumberFromUser();
        print("Input Hours of projections for a day");
        for (int i = 0; i < count; i++) {
            double hour = getValidHourFromUser();
            hours.add(hour);
        } return hours;
    }

    public String getMovieNameFromClient() {
        print("\nInput Name of movie from field 'Remaining movies' above");
        return getStringFromUser();
    }

    public void showInputHourMessage() {
        print("Input an Hour of projection:");
    }

    public double getHourFromClient() {
        return getValidHourFromUser();
    }

    public void showInputNumberOfTicketsMessage() {
        print("Input Number of Tickets:");
    }

    public void print(String text) {
        System.out.println(text);
        }
}
