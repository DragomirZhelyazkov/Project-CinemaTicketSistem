package controllers;
import data.Movie;
import data.MovieRepository;
import data.ProjectionsList;
import data.consts.ClientOption;
import data.models.Projection;
import io.ConsoleDevice;
import services.AuthenticationService;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//-----------------------------------------------------------------------------------
public class ClientController implements Controller {

    private final AuthenticationService authenticationService;
    private final ConsoleDevice console;
    Movie movie;
    List<Movie> allMovies;
    List<Movie> remainingMoviesForToday;
    Projection projection;
    double hourNow = getCurrentTime();
    LocalDate currentDate = LocalDate.now();
    int today = currentDate.getDayOfMonth();

//-----------------------------------------------------------------------------------

    public ClientController(ConsoleDevice console, AuthenticationService authenticationService) {
        this.console = console;
        this.authenticationService = authenticationService;
        this.allMovies = new ArrayList<>();
        this.remainingMoviesForToday  = new ArrayList<>();
    }
//------------------------------------------------------------------------------------
    @Override
    public void start() {
        if(checkIfThereAreAnyProjectionsInFile()) {
            readAllProjectionsFromFile();//////////////////////////////////////////////////////
        }
        ClientOption chosenOption = null;
        while (chosenOption != ClientOption.LOGOUT) {
            console.showClientOptions();
            chosenOption = console.getClientOption();
            switch (chosenOption) {
                case LOGOUT:
                    onLogoutOptionChosen();
                    break;
                case BUY_TICKET:
                    onBuyTicketOptionChosen();
                    break;
            }
        }
    }
//----------------------------------------------------------------------

    private void onBuyTicketOptionChosen() {
        if(checkIfThereAreAnyMoviesToday()) {
            hourNow = getCurrentTime();
            allMovies = readAllMovies();
            showAllMovies();
            remainingMoviesForToday = remainingMoviesForToday();
            if (!remainingMoviesForToday.isEmpty()) {
                showRemainingMoviesForToday();
                movie = chooseMovie();
                double hour = chooseHour();

                if (checkIfProjectionHasEmptySeats(movie.getHallNumber(), hour, today)) {
                    int numberOfSeats = chooseNumberOfSeats(hour, today);
                    createProjection(movie.getHallNumber(), hour, today);
                    changeHallMap(movie.getHallNumber(), hour, numberOfSeats, today);
                    printHallMap(movie, hour, today);
                    createTicket(hour, projection.getMySeats(), numberOfSeats);
                }
            } else {
                System.out.println("\n No remaining movies for today. Come tomorrow \n");
            }
        }
    }
    //------------------------------------------------------------------

    private boolean checkIfThereAreAnyMoviesToday() {
        if(new File("c:\\Temp\\Movies1.dat").length() == 0){
            System.out.println("There are no Movies in the list! \nPlease Logout! \nThe Admin has to input Movies first! \n");
            return false;
        } else return true;
    }

    private boolean checkIfThereAreAnyProjectionsInFile() {////////////////////////////
        if(new File("c:\\Temp\\Projections.dat").length() != 0){
            return true;
        } else return false;
    }////////////////////////////////////////////////////////////////////////////////////

    private List<Movie> readAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        try{
            FileInputStream is = new FileInputStream("c:\\Temp\\Movies1.dat");
            ObjectInputStream ois = new ObjectInputStream(is);
            java.lang.Object obj = ois.readObject();
            movies = (ArrayList<Movie>)obj;
            ois.close();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private void readAllProjectionsFromFile() {//////////////////////////////////////////
        ArrayList<Projection> projections = new ArrayList<>();
        try{
            FileInputStream is = new FileInputStream("c:\\Temp\\Projections.dat");
            ObjectInputStream ois = new ObjectInputStream(is);
            java.lang.Object obj = ois.readObject();
            projections = (ArrayList<Projection>)obj;
            ois.close();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ProjectionsList.getInstance().setProjectionsList(projections);///////////////////
    }

    private void showAllMovies() {
        System.out.println("All movies");
        for (Movie m : allMovies) {
            System.out.print(m.getName() + ", Hall " + m.getHallNumber() + ", ( ");
            for(double hour : m.getHours()) {
                System.out.printf("%.2f", hour);
                System.out.print("h, ");
            } System.out.println(")");
        }
    }

    private List<Movie> remainingMoviesForToday(){
        ArrayList<Movie> remainingMovies = new ArrayList<>();
        ArrayList<Movie> remainingMoviesByHour = new ArrayList<>();
        for(Movie m : allMovies) {
            for (double hour : m.getHours()) {
                if (hour > hourNow) {
                    remainingMoviesByHour.add(m);
                    break;
                } } }
        for(Movie mov : remainingMoviesByHour){
            mov.getHours().removeIf(hour -> hour < hourNow);
            for(int day : mov.getDays()){
                if(day == today){
                    remainingMovies.add(mov); break;
                } } } return remainingMovies;
    }

    private void showRemainingMoviesForToday(){
        System.out.println(" \nRemaining movies for today");
        for (Movie m : remainingMoviesForToday) {
            System.out.print(m.getName() + ", Hall " + m.getHallNumber() + ", ( ");
            for(double hour : m.getHours()) {
                System.out.printf("%.2f", hour);
                System.out.print("h, ");
            } System.out.println(")");
        }
    }

    private Movie chooseMovie() {
        String movieName = console.getMovieNameFromClient();
        for (Movie m : remainingMoviesForToday) {
            if (movieName.equals(m.getName())) {
                return m;
            }
        }
        console.print("Not a valid name. Try again:");
        return chooseMovie();
    }
    
    private double chooseHour(){
        console.showInputHourMessage();
        double chosenHour = console.getHourFromClient();
        for (double hour : movie.getHours()) {
            if (chosenHour == hour && chosenHour > hourNow) {
                return chosenHour;
            }
        }
        console.print("Not a valid hour. Try again:");
        return chooseHour();
    }

    private boolean checkIfProjectionHasEmptySeats(int hallNumber, double hour, int day) {
        if (ProjectionsList.getInstance().checkIfProjectionHasNoSeats(hallNumber, hour, day)) {
            System.out.println("No empty seats for this projection! Choose another hour! \n ");
            return false;
        } else {
            System.out.println("There are empty seats for this projection!");
            return true;
        }
    }

    private void createProjection(int hallNumber, double hour, int day){
        if(ProjectionsList.getInstance().checkIfProjectionExist(hallNumber, hour, day)){
            System.out.println("exist");
            return;
        } else {
            Projection projection = new Projection(movie, hour, day);
            ProjectionsList.getInstance().addProjection(projection);
        }
    }

    private int chooseNumberOfSeats(double hour, int day) {
        console.showInputNumberOfTicketsMessage();
        int num = console.getNumberFromUser();
        if(ProjectionsList.getInstance().checkIfProjectionHasEnoughSeats(movie.getHallNumber(), hour, num, day)){
            return num;
        } else {
            System.out.println("Not enough seats, please enter new number of Tickets");
            return chooseNumberOfSeats(hour, day);
        }
    }

    private void changeHallMap(int hallNumber, double hour, int numberOfSeats, int day){
        ProjectionsList.getInstance().changeProjectionDiagram(hallNumber, hour, numberOfSeats, day);
    }

    private void printHallMap (Movie movie, double hour, int day) {
        projection = ProjectionsList.getInstance().getProjectionByHallNumberAndHour(movie.getHallNumber(),
                hour, day);
        System.out.println();
        if (projection != null) {
            projection.printProjectionDiagram();
        }
    }

    private void createTicket(double hour, ArrayList<String> arr, int numberOfSeats) {
        String strDouble = String.format("%.2f", hour);
        String ticket = "Your Ticket: Movie: " +  movie.getName() + ", " + "Date: " + today + ", " + "Hour: " + strDouble +
                ", " +
                "Hall: " + movie.getHallNumber() +
                ", " +
                "Your seats(" + numberOfSeats + "): " + arr.toString() + ", Price: " + (movie.getPrice())*numberOfSeats + "lv. \n ";

        String order = authenticationService.getLoggedUser().getUsername() + ", " + currentDate + ", " + hourNow + "h, " +
        "Movie: " +  movie.getName() +
                ", " +
                "Date: " + today + ", " + "Hour: " + strDouble + ", " +
                "Hall: " + movie.getHallNumber() +
                ", " +
                "Your seats(" + numberOfSeats + "): " + arr.toString() + ", Price: " + (movie.getPrice())*numberOfSeats + "lv.";
        System.out.println(ticket);

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("c:\\Temp\\ticketHistory.txt", true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(order);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveProjectionsListToFile(){//////////////////////////////////////////
        List<Projection> projections = ProjectionsList.getInstance().getProjections();
        try {
            OutputStream os = new FileOutputStream("c:\\Temp\\Projections.dat");
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(projections);
            oos.flush();
            oos.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }////////////////////////////////////////////////////////////////////////////////
    }

    private void onLogoutOptionChosen() {
        saveProjectionsListToFile();
        authenticationService.logout();
        NavigationController.getInstance(console).startLogin();
    }

    private double getCurrentTime() {
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        String hourWithMinutes = hour + "." + minute;
        return Double.parseDouble(hourWithMinutes);
    }
}



