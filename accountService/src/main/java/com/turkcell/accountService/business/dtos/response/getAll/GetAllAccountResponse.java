package com.turkcell.accountService.business.dtos.response.getAll;

import com.turkcell.accountService.entities.concretes.AccountType;
import com.turkcell.accountService.entities.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllAccountResponse {
    private String accountName;
    private String accountNumber;
    private Boolean status;
    private Action action;
    private Set<AccountType> accountTypes;
    private int customerId;
    private List<Integer> addressId;
}
