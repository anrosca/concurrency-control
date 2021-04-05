package com.endava.jpa.wallet;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<T, ID> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected final Class<T> entityType;

    public AbstractRepository(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityType);
//        query.from(entityType);
//        return entityManager.createQuery(query)
//                .getResultList();
        return entityManager.createQuery("from" + entityType.getSimpleName(), entityType)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public T findById(ID id) {
        return entityManager.find(entityType, id);
    }

    @Transactional
    public T save(T entity) {
        T savedEntity = entityManager.merge(entity);
        entityManager.flush();
        return savedEntity;
    }

    @Transactional
    public void delete(ID id) {
        entityManager.remove(findById(id));
    }

    @Transactional
    public void deleteAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> query = criteriaBuilder.createCriteriaDelete(entityType);
        query.from(entityType);
        entityManager.createQuery(query)
                .executeUpdate();
    }

    @Transactional
    public List<T> saveAll(List<T> entities) {
        List<T> savedEntities = new ArrayList<>();
        for (T entity : entities) {
            savedEntities.add(save(entity));
        }
        return savedEntities;
    }
}
