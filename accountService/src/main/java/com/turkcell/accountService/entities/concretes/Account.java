package com.turkcell.accountService.entities.concretes;


import com.turkcell.accountService.entities.enums.Action;
import com.turkcell.corepackage.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Set;


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

    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private Action action;

    @ManyToMany
    @JoinTable(
            name = "account_account_types",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "account_type_id")
    )
    private Set<AccountTypes> accountTypes;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "account_address_id")
    private List<Integer> accountAddressId;

}
