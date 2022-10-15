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
    public void createTransaction(@PathVariable long id) {
        this.transactionRepository.findById(id).get();
    }
}
