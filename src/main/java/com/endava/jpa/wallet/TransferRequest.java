package com.endava.jpa.wallet;

import java.math.BigDecimal;
import java.util.UUID;

public class TransferRequest {
    private final String transactionId = UUID.randomUUID().toString();
    private final String fromUserId;
    private final String toUserId;
    private final BigDecimal amount;

    public TransferRequest(String fromUserId, String toUserId, BigDecimal amount) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "transactionId='" + transactionId + '\'' +
                ", fromUserId='" + fromUserId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
