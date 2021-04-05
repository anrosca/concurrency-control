package com.endava.jpa.movie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

@Component
@Slf4j
public class MovieRepository {
    private final EntityManager entityManager;

    public MovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void persist(Movie movie) {
        entityManager.persist(movie);
    }

    @Transactional
    public void remove(String id) {
        Movie movie = entityManager.getReference(Movie.class, id);
        entityManager.remove(movie);
    }

    @Transactional
    public Movie update(Movie movie) {
        return entityManager.merge(movie);
    }

    @Transactional
    public Movie find(String id) {
        return entityManager.find(Movie.class, id);
    }

    @Transactional
    public Movie findWithPublishingCompanies(String id) {
        return entityManager.createQuery("select m from Movie m join fetch m.publishingCompanies where m.id = :id", Movie.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public void updateName(String id, String newName) {
        Movie movie = entityManager.find(Movie.class, id, LockModeType.PESSIMISTIC_WRITE);
        sleep();
        movie.setName(newName);
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void clear() {
        entityManager.flush();
        entityManager.clear();
    }
}
