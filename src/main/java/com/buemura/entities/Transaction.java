package com.buemura.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "customer_id")
    public Integer customerId;

    @Column
    public Integer amount;

    @Column
    public Character type;

    @Column
    public String description;


    @Column(name = "created_at") @CreationTimestamp
    public Date createdAt;
}