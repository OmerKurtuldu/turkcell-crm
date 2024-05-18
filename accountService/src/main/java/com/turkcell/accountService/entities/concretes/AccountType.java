package com.turkcell.accountService.entities.concretes;


import com.turkcell.corepackage.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "account_types")
public class AccountType extends BaseEntity<Integer> {
    @Column(name = "account_name")
    private String accountTypeName;
}
