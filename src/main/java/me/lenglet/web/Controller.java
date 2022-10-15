package me.lenglet.web;

import me.lenglet.entity.Transaction;
import me.lenglet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class Controller {

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    @GetMapping
    public Long createTransaction() {
        final var transaction = new Transaction();
        transaction.setClientName("Jim Morrison");
        transaction.setTransactionDate(LocalDate.of(2022, 1, 1));
        this.transactionRepository.save(transaction);
        return transaction.getId();
    }

    @Transactional(readOnly = true)
    @GetMapping(path = "/{id}")
    public TransactionDto createTransaction(@PathVariable long id) {
        return this.transactionRepository.findById(id)
                .map(transaction -> new TransactionDto(
                        transaction.getId(),
                        transaction.getClientName().value(),
                        transaction.getTransactionDate().value()
                ))
                .orElse(null);
    }

    public record TransactionDto(
            Long id,
            String transactionName,
            LocalDate transactionDate
    ) {
    }
}
