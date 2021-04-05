package com.endava.jpa.wallet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class TransactionService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public TransactionService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    ///Concurrency control
    //pessimistic, optimistic
    //pessimistic locking - prevents conflicts
    //optimistic locking - detects conflicts
//    @Transactional
//    public TransactionResult transfer(TransferRequest request) {
//        User fromUser = userRepository.findByIdWithWallet(request.getFromUserId());
//        User toUser = userRepository.findByIdWithWallet(request.getToUserId());
//        Wallet fromWallet = fromUser.getWallet();
//        Wallet toWallet = toUser.getWallet();
//        BigDecimal amount = request.getAmount();
//        if (fromWallet.hasMoreThan(amount)) {
//            walletRepository.deposit(fromWallet, amount.negate());
//            walletRepository.deposit(toWallet, amount);
//            log.info("Successful transaction. Tx id: {}. " +
//                            "Transferred {} from wallet id: {} to wallet id: {}",
//                    request.getTransactionId(), request.getAmount(), fromWallet.getId(), toWallet.getId());
//            return TransactionResult.success();
//        } else {
//            log.info("Rejecting transaction with id: {}. " +
//                            "Insufficient funds. Wallet with id: {} has {} balance. Wanted amount = {}",
//                    request.getTransactionId(), fromWallet.getId(), fromWallet.getBalance(), request.getAmount());
//            return TransactionResult.rejected();
//        }
//    }

    @Transactional
    public TransactionResult transfer(TransferRequest request) {
        User fromUser = userRepository.findByIdWithWallet(request.getFromUserId());
        User toUser = userRepository.findByIdWithWallet(request.getToUserId());
        Wallet fromWallet = fromUser.getWallet();
        Wallet toWallet = toUser.getWallet();
        BigDecimal amount = request.getAmount();
        if (fromWallet.hasMoreThan(amount)) {
            fromWallet.withdraw(amount);
            toWallet.deposit(amount);
            log.info("Successful transaction. Tx id: {}. " +
                            "Transferred {} from wallet id: {} to wallet id: {}",
                    request.getTransactionId(), request.getAmount(), fromWallet.getId(), toWallet.getId());
            return TransactionResult.success();
        } else {
            log.info("Rejecting transaction with id: {}. " +
                            "Insufficient funds. Wallet with id: {} has {} balance. Wanted amount = {}",
                    request.getTransactionId(), fromWallet.getId(), fromWallet.getBalance(), request.getAmount());
            return TransactionResult.rejected();
        }
    }
}
