package com.endava.jpa.movie;

import com.endava.jpa.AbstractEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@EntityListeners({MovieEntityListener.class})
public class Movie extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @LazyCollection(LazyCollectionOption.EXTRA)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "movies", cascade = CascadeType.PERSIST)
    private Set<PublishingCompany> publishingCompanies = new HashSet<>();

    @Version
    private long version;

    protected Movie() {}

    private Movie(MovieBuilder builder) {
        this.name = builder.name;
    }

    public void addPublishingCompany(PublishingCompany publishingCompany) {
        publishingCompanies.add(publishingCompany);
    }

    public void removePublishingCompany(PublishingCompany publishingCompany) {
        publishingCompanies.remove(publishingCompany);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PublishingCompany> getPublishingCompanies() {
        return publishingCompanies;
    }

    public void setPublishingCompanies(Set<PublishingCompany> publishingCompanies) {
        this.publishingCompanies = publishingCompanies;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static MovieBuilder builder() {
        return new MovieBuilder();
    }

    public static class MovieBuilder {
        private String name;

        public MovieBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
