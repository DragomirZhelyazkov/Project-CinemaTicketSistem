package controllers;

import data.Movie;
import data.MovieRepository;
import data.consts.AdminOption;
import io.ConsoleDevice;
import services.AuthenticationService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class AdminController implements Controller {
    private final AuthenticationService authenticationService;
    private final ConsoleDevice console;

    public AdminController(ConsoleDevice console, AuthenticationService authenticationService) {
        this.console = console;
        this.authenticationService = authenticationService;
    }

    @Override
    public void start() {
        AdminOption chosenOption = null;
        while (chosenOption != AdminOption.LOGOUT) {
            console.showAdminOptions();
            chosenOption = console.getAdminOptionFromUser();
            switch (chosenOption) {
                case ADD_MOVIE:
                    onAddMovieOptionChosen();
                    break;
                case LOGOUT:
                    onLogoutOptionChosen();
                    break;
            }
        }
    }

    private void onAddMovieOptionChosen() {
        clearMoviesFile();
        clearProjectionsFile();//----------------------------------------
        String movieName = console.getMovieNameFromAdmin();
        double moviePrice = console.getMoviePriceFromAdmin();
        int hallNumber = console.getHallNumberFromAdmin();
        ArrayList<Integer> movieDays = console.getMovieDaysFromAdmin();
        ArrayList<Double> movieHours = console.getMovieHoursFromAdmin();
        if(checkIfMovieHallDayHourAreUnique(hallNumber, movieDays, movieHours)) {
            Movie movie = new Movie(movieName, moviePrice, hallNumber, movieDays, movieHours);
            MovieRepository.getInstance().addMovie(movie);
            System.out.println(MovieRepository.getInstance().getMovies().toString());//чек поинт------
        }
    }

    private void clearMoviesFile(){
        try {
            new FileOutputStream("c:\\Temp\\Movies1.dat", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearProjectionsFile(){//-----------------------------------
        try {
            new FileOutputStream("c:\\Temp\\Projections.dat", false);
        } catch (IOException e) {
            e.printStackTrace();
        }//------------------------------------------------------------------
    }

    private static void saveMoviesToFileMovies(ArrayList<Movie> movies) {
        try {
            OutputStream os = new FileOutputStream("c:\\Temp\\Movies1.dat");
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(movies);
            oos.flush();
            oos.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onLogoutOptionChosen() {
        ArrayList<Movie> movies = MovieRepository.getInstance().getMovies();
        saveMoviesToFileMovies(movies);
        authenticationService.logout();
        NavigationController.getInstance(console).startLogin();
    }

    private boolean checkIfMovieHallDayHourAreUnique(int hallNumber, ArrayList<Integer> movieDays,
                                                     ArrayList<Double> movieHours) {
        ArrayList<Movie> arr = MovieRepository.getInstance().getMovies();
        if(!arr.isEmpty()){
            for(Movie m : arr){
                if(m.getHallNumber() == hallNumber){
                    for (int day : movieDays){
                        if (m.getDays().contains(day)){
                            for (double hour : movieHours){
                                if(m.getHours().contains(hour)){
                                    System.out.println("There is already a projection on this day in this hall at that " +
                                            "hour \n");
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }return true;
    }
}


