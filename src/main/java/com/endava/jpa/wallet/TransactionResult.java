package com.endava.jpa.wallet;

public class TransactionResult {
    private final TransactionStatus status;

    private TransactionResult(TransactionStatus status) {
        this.status = status;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public boolean isSuccessful() {
        return status == TransactionStatus.SUCCESS;
    }

    public static TransactionResult success() {
        return new TransactionResult(TransactionStatus.SUCCESS);
    }

    public static TransactionResult rejected() {
        return new TransactionResult(TransactionStatus.REJECTED);
    }
}
