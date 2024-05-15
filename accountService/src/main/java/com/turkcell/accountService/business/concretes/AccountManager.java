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
import com.turkcell.accountService.dataAccess.abstracts.AccountAddressRepository;
import com.turkcell.accountService.dataAccess.abstracts.AccountRepository;
import com.turkcell.accountService.dataAccess.abstracts.AccountTypesRepository;
import com.turkcell.accountService.entities.concretes.Account;
import com.turkcell.accountService.entities.concretes.AccountAddresses;
import com.turkcell.accountService.entities.concretes.AccountTypes;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class AccountManager implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapperService modelMapperService;
    private final MessageService messageService;
    private final AccountTypesRepository accountTypesRepository; //todo konusalacak
    private final AccountBusinessRules accountBusinessRules;
    private final AccountAddressRepository accountAddressRepository;

    @Override
    public CreatedAccountResponse add(CreatedAccountRequest createdAccountRequest) {

        accountBusinessRules.checkCustomerAvailabilityForAccount(createdAccountRequest.getCustomerId());

        Account account = this.modelMapperService.forRequest().map(createdAccountRequest, Account.class);

        Set<AccountTypes> accountTypes  = accountTypeForControl(createdAccountRequest.getAccountTypes());
        account.setAccountTypes(accountTypes);

        accountRepository.save(account);
        AccountAddresses accountAddresses = this.modelMapperService.forRequest().map(createdAccountRequest,AccountAddresses.class);
        accountAddresses.setAccountID(account.getId());

        accountAddressRepository.save(accountAddresses);

        return this.modelMapperService.forResponse().map(account, CreatedAccountResponse.class);
    }
    @Override
    public UpdatedAccountResponse update(UpdatedAccountRequest updatedAccountRequest) {

        accountBusinessRules.accountShoulBeExist(updatedAccountRequest.getId());
        accountBusinessRules.checkCustomerAvailabilityForAccount(updatedAccountRequest.getCustomerId());

        Account account = this.modelMapperService.forRequest().map(updatedAccountRequest, Account.class);

        Set<AccountTypes> accountTypes  = accountTypeForControl(updatedAccountRequest.getAccountTypes());
        account.setAccountTypes(accountTypes);

        accountRepository.save(account);

        return this.modelMapperService.forResponse().map(account,UpdatedAccountResponse.class);
    }

    @Override
    public GetAccountResponse getById(int id) {
        accountBusinessRules.accountShoulBeExist(id);
        Optional<Account> account = accountRepository.findById(id);
        return this.modelMapperService.forResponse().map(account.get(),GetAccountResponse.class);
    }

    @Override
    public List<GetAllAccountResponse> getAll() {
        List<Account> accounts = accountRepository.findAll();
        List<GetAllAccountResponse> getAllAccountResponses = new ArrayList<GetAllAccountResponse>();
        for (var account : accounts){
            GetAllAccountResponse getAllAccountResponse = this.modelMapperService.forResponse().map(account,GetAllAccountResponse.class);
            getAllAccountResponses.add(getAllAccountResponse);
        }
        return getAllAccountResponses;
    }

    @Override
    public void delete(int id) {
        accountBusinessRules.accountShoulBeExist(id);
        accountRepository.deleteById(id);
    }

    public Set<AccountTypes> accountTypeForControl(Set<Integer> AccountTypes){

        Set<AccountTypes> accountTypes = new HashSet<AccountTypes>();
        for (Integer accountTypeId : AccountTypes) {
            AccountTypes accountType = accountTypesRepository.findById(accountTypeId).orElseThrow(()
                    -> new BusinessException(messageService.getMessage(Messages.AccountErrors.AccountTypeShouldBeExists)));
            accountTypes.add(accountType);
        }

        return accountTypes;
    }

}
