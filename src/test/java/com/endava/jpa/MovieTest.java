package com.endava.jpa;

import com.endava.jpa.movie.Movie;
import com.endava.jpa.util.AbstractEqualityCheckTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieTest extends AbstractEqualityCheckTest<Movie> {

    @Test
    public void equalsAndHashCodeShouldBeJpaCompliant() {
        Movie movie = Movie.builder().name("Pulp fiction").build();

        assertEqualityConsistency(Movie.class, movie);
    }
}
