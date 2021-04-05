package com.endava.jpa.wallet;

import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public class UserRepository extends AbstractRepository<User, String> {
    public UserRepository() {
        super(User.class);
    }

    public User findByIdWithWallet(String id) {
        return entityManager.createQuery("select u from User u join fetch u.wallet where u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
