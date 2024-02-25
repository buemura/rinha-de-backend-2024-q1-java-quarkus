package com.buemura.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import com.buemura.entities.Transaction;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transaction> {

    public PanacheQuery<Transaction> findByCustomerIdOrderByCreatedAtDesc(Integer customerId, int page, int pageSize) {
        return find("customerId = :customerId", Sort.by("createdAt").descending(), Parameters.with("customerId", customerId))
                .page(page, pageSize);
    }
}