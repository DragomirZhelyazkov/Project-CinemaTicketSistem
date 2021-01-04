package data;

import java.io.Serializable;
import java.util.ArrayList;


public class MovieRepository implements Serializable {
    private static final MovieRepository instance = new MovieRepository();
    public static MovieRepository getInstance() {
        return instance;
    }

    private final ArrayList<Movie> movies;

    private MovieRepository() {
        movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}
