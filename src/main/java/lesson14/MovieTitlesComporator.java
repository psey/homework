package lesson14;

import java.util.Comparator;

public class MovieTitlesComporator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.title.compareTo(o2.title);
    }
}
