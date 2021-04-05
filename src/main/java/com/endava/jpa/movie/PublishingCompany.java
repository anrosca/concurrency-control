package com.endava.jpa.movie;

import com.endava.jpa.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PublishingCompany extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Movie> movies = new HashSet<>();

    protected PublishingCompany() {
    }

    public PublishingCompany(String name) {
        this.name = name;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "PublishingCompany{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
