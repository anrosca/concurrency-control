package com.endava.jpa.wallet;

import com.endava.jpa.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Version;
import java.math.BigDecimal;

@Entity
public class Wallet extends AbstractEntity {

    private BigDecimal balance = BigDecimal.ZERO;

    public Wallet() {
    }

    public Wallet(BigDecimal initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(BigDecimal depositAmount) {
        balance = balance.add(depositAmount);
    }

    public void withdraw(BigDecimal withdrawAmount) {
        balance = balance.subtract(withdrawAmount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal amount) {
        this.balance = amount;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id='" + id + '\'' +
                ", amount=" + balance +
                '}';
    }

    public boolean hasMoreThan(BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }
}
