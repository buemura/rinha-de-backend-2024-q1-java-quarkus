package com.buemura.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import com.buemura.entities.Customer;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
}
