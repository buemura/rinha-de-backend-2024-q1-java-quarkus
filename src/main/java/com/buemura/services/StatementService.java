package com.buemura.services;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.buemura.dtos.BalanceDto;
import com.buemura.dtos.StatementDto;
import com.buemura.dtos.TransactionDto;
import com.buemura.entities.Customer;
import com.buemura.entities.Transaction;
import com.buemura.exceptions.CustomerNotFoundException;
import com.buemura.repositories.CustomerRepository;
import com.buemura.repositories.TransactionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class StatementService {
    @Inject
    CustomerRepository customerRepository;
    @Inject
    TransactionRepository transactionRepository;

    public StatementDto getStatement(Integer customerId) {
        Customer customer = customerRepository.findByIdOptional(Long.valueOf(customerId)).orElseThrow(CustomerNotFoundException::new);

        PanacheQuery<Transaction> query = transactionRepository.findByCustomerIdOrderByCreatedAtDesc(customerId, 0, 10);
        List<Transaction> transactions = query.list();

        List<TransactionDto> transactionsDto = new ArrayList<>();
        transactions.forEach(trx -> transactionsDto.add(new TransactionDto(
                trx.amount,
                trx.type,
                trx.description,
                trx.createdAt
        )));

        BalanceDto balanceDto = new BalanceDto(customer.accountBalance, new Date(), customer.accountLimit);
        return new StatementDto(balanceDto,transactionsDto);
    }
}
