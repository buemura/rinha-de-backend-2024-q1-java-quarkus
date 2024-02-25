package com.buemura.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.buemura.dtos.TransactionRequestDto;
import com.buemura.dtos.TransactionResponseDto;
import com.buemura.entities.Customer;
import com.buemura.entities.Transaction;
import com.buemura.exceptions.CustomerNotFoundException;
import com.buemura.exceptions.NotEnoughLimitException;
import com.buemura.repositories.CustomerRepository;
import com.buemura.repositories.TransactionRepository;
import jakarta.persistence.LockModeType;

import java.util.Date;

@ApplicationScoped
public class TransactionService {
    @Inject
    CustomerRepository customerRepository;
    @Inject
    TransactionRepository transactionRepository;

    public TransactionResponseDto insertCreditTransaction(Integer customerId, TransactionRequestDto trx) {
        Customer customer = customerRepository.findById(customerId.longValue(), LockModeType.PESSIMISTIC_WRITE);
        if (customer == null) throw new CustomerNotFoundException();

        customer.accountBalance += Integer.parseInt(trx.amount());
        customerRepository.persist(customer);

        Transaction transaction = buildTransaction(customerId, trx);
        transactionRepository.persist(transaction);

        return new TransactionResponseDto(customer.accountLimit, customer.accountBalance);
    }

    public TransactionResponseDto insertDebitTransaction(Integer customerId, TransactionRequestDto trx) {
        Customer customer = customerRepository.findById(customerId.longValue(), LockModeType.PESSIMISTIC_WRITE);
        if (customer == null) throw new CustomerNotFoundException();

        customer.accountBalance -= Integer.parseInt(trx.amount());
        if (customer.accountLimit + customer.accountBalance < 0) throw new NotEnoughLimitException();

        customerRepository.persist(customer);

        Transaction transaction = buildTransaction(customerId, trx);
        transactionRepository.persist(transaction);

        return new TransactionResponseDto(customer.accountLimit, customer.accountBalance);
    }

    private Transaction buildTransaction(Integer customerId, TransactionRequestDto trx) {
        Transaction transaction = new Transaction();
        transaction.setCustomerId(customerId);
        transaction.setAmount(Integer.parseInt(trx.amount()));
        transaction.setType(trx.type());
        transaction.setDescription(trx.description());
        transaction.setCreatedAt(new Date());
        return transaction;
    }

}
