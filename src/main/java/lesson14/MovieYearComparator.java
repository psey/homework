package lesson14;

import java.util.Comparator;

public class MovieYearComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return (o1.year < o2.year) ? -1 : ((o1.year == o2.year) ? 0 : 1);
    }
}
