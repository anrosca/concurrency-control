package com.endava.jpa.wallet;

import java.math.BigDecimal;

public class TransferRequestDto {
    private String fromUserId;
    private String toUserId;
    private BigDecimal amount;

    public TransferRequestDto() {
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransferRequest toTransferRequest() {
        return new TransferRequest(fromUserId, toUserId, amount);
    }

    @Override
    public String toString() {
        return "TransferRequestDto{" +
                "fromUserId='" + fromUserId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
