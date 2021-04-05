package com.endava.jpa.wallet;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public class WalletRepository extends AbstractRepository<Wallet, String> {
    public WalletRepository() {
        super(Wallet.class);
    }

    @Transactional
    public void deposit(Wallet wallet, BigDecimal amount) {
        entityManager.createQuery("update Wallet w set w.balance = w.balance + :amount where w.id = :id")
                .setParameter("amount", amount)
                .setParameter("id", wallet.getId())
                .executeUpdate();
    }
}
