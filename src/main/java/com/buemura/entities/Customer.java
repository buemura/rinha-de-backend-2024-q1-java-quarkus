package com.buemura.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    public Integer id;

    @Column(name = "account_limit")
    public Integer accountLimit;

    @Column(name = "account_balance")
    public Integer accountBalance;
}
