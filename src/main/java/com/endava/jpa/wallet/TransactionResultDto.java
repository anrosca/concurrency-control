package com.endava.jpa.wallet;

public class TransactionResultDto {
    private String status;

    public TransactionResultDto() {
    }

    public TransactionResultDto(TransactionStatus status) {
        this.status = status.name();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static TransactionResultDto from(TransactionResult transactionResult) {
        return new TransactionResultDto(transactionResult.getStatus());
    }

    @Override
    public String toString() {
        return "TransactionResultDto{" +
                "status='" + status + '\'' +
                '}';
    }
}
