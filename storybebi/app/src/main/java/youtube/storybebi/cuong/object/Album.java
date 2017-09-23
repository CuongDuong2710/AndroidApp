package youtube.storybebi.cuong.object;

import java.util.ArrayList;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Album {
    private String name;
    private String imageUrl;
    private String numberOfMovies;
    private ArrayList<Movie> movies = new ArrayList<>();

    public Album() {
    }

    public Album(String name, String numberOfMovies, String imageUrl) {
        this.name = name;
        this.numberOfMovies = numberOfMovies;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(String numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", numOfVideos=" + numberOfMovies +
                ", imageUrl=" + imageUrl +
                '}';
    }
}
