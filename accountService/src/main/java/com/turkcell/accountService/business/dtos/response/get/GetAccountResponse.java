package com.turkcell.accountService.business.dtos.response.get;

import com.turkcell.accountService.entities.concretes.AccountTypes;
import com.turkcell.accountService.entities.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAccountResponse {

    private String accountName;
    private String accountNumber;
    private Boolean status;
    private Action action;
    private Set<AccountTypes> accountTypes;
    private int customerId;

}
