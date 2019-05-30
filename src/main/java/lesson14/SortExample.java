package lesson14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortExample {
    public static void main(String[] args) {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("a", 2));
        movies.add(new Movie("b", 2.02));
        movies.add(new Movie("c", 6.4));

        Collections.sort(movies);

        for (Movie movie : movies) {
            System.out.println(movie);
        }

        Collections.sort(movies, new MovieTitlesComporator());
        System.out.println(movies);
// Сортируем фильмы по Оценке
        Collections.sort(movies, new MovieScoreComparator());
        System.out.println(movies);


    }

}
