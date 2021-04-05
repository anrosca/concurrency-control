package com.endava.jpa.wallet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResultDto> transfer(@RequestBody TransferRequestDto transferRequestDto) {
        TransactionResult transactionResult = transactionService.transfer(transferRequestDto.toTransferRequest());
        TransactionResultDto responseBody = TransactionResultDto.from(transactionResult);
        return transactionResult.isSuccessful() ?  ResponseEntity.ok(responseBody):
                ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(responseBody);
    }
}
