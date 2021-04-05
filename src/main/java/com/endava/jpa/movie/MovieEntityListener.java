package com.endava.jpa.movie;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
public class MovieEntityListener {
    public MovieEntityListener() {
    }

    @PrePersist
    public void prePersist(Object entity) {
        log.info("About to persist movie: {}", entity);
    }

    @PostPersist
    public void postPersist(Object entity) {
        log.info("Persisted movie: {}", entity);
    }

    @PreRemove
    public void preRemove(Object entity) {
        log.info("About to remove movie: {}", entity);
    }

    @PostRemove
    public void postRemove(Object entity) {
        log.info("Removed movie: {}", entity);
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        log.info("About to update movie: {}", entity);
    }

    @PostUpdate
    public void postUpdate(Object entity) {
        log.info("Updated movie: {}", entity);
    }
}
