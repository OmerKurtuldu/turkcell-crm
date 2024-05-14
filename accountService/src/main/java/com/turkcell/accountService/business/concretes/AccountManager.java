package com.turkcell.accountService.business.concretes;

import com.turkcell.accountService.business.abstracts.AccountService;
import com.turkcell.accountService.business.dtos.request.created.CreatedAccountRequest;
import com.turkcell.accountService.business.dtos.request.updated.UpdatedAccountRequest;
import com.turkcell.accountService.business.dtos.response.created.CreatedAccountResponse;
import com.turkcell.accountService.business.dtos.response.get.GetAccountResponse;
import com.turkcell.accountService.business.dtos.response.getAll.GetAllAccountResponse;
import com.turkcell.accountService.business.dtos.response.updated.UpdatedAccountResponse;
import com.turkcell.accountService.business.messages.Messages;
import com.turkcell.accountService.business.rules.AccountBusinessRules;
import com.turkcell.accountService.dataAccess.abstracts.AccountRepository;
import com.turkcell.accountService.dataAccess.abstracts.AccountTypesRepository;
import com.turkcell.accountService.entities.concretes.Account;
import com.turkcell.accountService.entities.concretes.AccountTypes;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AccountManager implements AccountService {
    private final AccountRepository accountRepository;
    private final ModelMapperService modelMapperService;
    private final MessageService messageService;
    private final AccountTypesRepository accountTypesRepository; //todo konusalacak
    private final AccountBusinessRules accountBusinessRules;



    @Override
    public CreatedAccountResponse add(CreatedAccountRequest createdAccountRequest) {
        accountBusinessRules.checkCustomerAvailabilityForAccount(createdAccountRequest.getCustomerId());

        Account account = this.modelMapperService.forRequest().map(createdAccountRequest, Account.class);

        Set<AccountTypes> accountTypes = new HashSet<AccountTypes>();
        for (Integer accountTypeId : createdAccountRequest.getAccountTypes()) {

            AccountTypes accountType = accountTypesRepository.findById(accountTypeId).orElseThrow(()
                    -> new BusinessException(messageService.getMessage(Messages.AccountErrors.AccountTypeShouldBeExists)));
            accountTypes.add(accountType);
        }
        account.setAccountTypes(accountTypes);

        accountRepository.save(account);

        return this.modelMapperService.forResponse().map(account, CreatedAccountResponse.class);
    }
    @Override
    public UpdatedAccountResponse update(UpdatedAccountRequest updatedAccountRequest) {
        return null;
    }

    @Override
    public GetAccountResponse getById(int id) {
        return null;
    }

    @Override
    public List<GetAllAccountResponse> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
