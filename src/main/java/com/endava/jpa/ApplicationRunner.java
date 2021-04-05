package com.endava.jpa;

import com.endava.jpa.wallet.User;
import com.endava.jpa.wallet.UserRepository;
import com.endava.jpa.wallet.Wallet;
import com.endava.jpa.wallet.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class ApplicationRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public ApplicationRunner(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

//    @Transactional
    @Override
    public void run(String... args) {
        walletRepository.deleteAll();
        userRepository.deleteAll();
        Wallet aliceWallet = walletRepository.save(new Wallet(new BigDecimal("100")));
        Wallet bobWallet = walletRepository.save(new Wallet());
        User alice = new User("Alice", "Smith", LocalDate.of(1994, 10, 3),aliceWallet);
        User bob = new User("Bob", "Vance", LocalDate.of(1993, 9, 2), bobWallet);
        userRepository.saveAll(List.of(alice, bob));
    }
}
