package lesson14;

import java.util.Comparator;

public class MovieScoreComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return (o1.score < o2.score) ? -1 : ((o1.score == o2.score) ? 0 : 1);
    }
}

