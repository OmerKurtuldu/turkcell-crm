package com.turkcell.accountService.entities.concretes;

import com.turkcell.accountService.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity<Integer> {
    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "account")
    private List<AccountTypes> accountTypes;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "account_address_id")
    private int accountAddressId;

}
