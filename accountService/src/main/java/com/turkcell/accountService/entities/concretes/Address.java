package com.turkcell.accountService.entities.concretes;

import com.turkcell.accountService.core.entities.BaseEntity;
import com.turkcell.commonpackage.events.customer.CreatedCustomerEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "adresses")
public class Address extends BaseEntity<Integer> {

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house_flat_number", nullable = false)
    private String houseFlatNumber;

    @Column(name = "description", nullable = false)
    private String description;

    @JoinColumn(name = "customer_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany
    @JoinTable(name = "account_addresses"
    ,joinColumns = @JoinColumn(name = "address_id"),
    inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accounts;

}
