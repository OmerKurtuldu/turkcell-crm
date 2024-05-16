package com.turkcell.accountService.business.dtos.response.updated;

import com.turkcell.accountService.entities.concretes.AccountTypes;
import com.turkcell.accountService.entities.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatedAccountResponse {

    private String accountName;
    private String AccountNumber;
    private Boolean status;
    private Action action;
    private Set<AccountTypes> accountTypes;
    private List<Integer> addressId;

}
