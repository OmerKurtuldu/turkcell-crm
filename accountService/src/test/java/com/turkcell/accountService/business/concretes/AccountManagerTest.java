package com.turkcell.accountService.business.concretes;

import com.turkcell.accountService.business.abstracts.AccountTypeService;
import com.turkcell.accountService.business.dtos.request.created.CreatedAccountRequest;
import com.turkcell.accountService.business.dtos.request.updated.UpdatedAccountRequest;
import com.turkcell.accountService.business.dtos.response.created.CreatedAccountResponse;
import com.turkcell.accountService.business.dtos.response.get.GetAccountResponse;
import com.turkcell.accountService.business.dtos.response.getAll.GetAllAccountResponse;
import com.turkcell.accountService.business.dtos.response.updated.UpdatedAccountResponse;
import com.turkcell.accountService.business.rules.AccountBusinessRules;
import com.turkcell.accountService.dataAccess.abstracts.AccountRepository;
import com.turkcell.accountService.entities.concretes.Account;
import com.turkcell.accountService.entities.concretes.AccountType;
import com.turkcell.accountService.entities.enums.Action;
import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountManagerTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ModelMapperService modelMapperService;
    @Mock
    private AccountTypeService accountTypeService;
    @Mock
    private AccountBusinessRules accountBusinessRules;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private AccountManager accountManager;

    private void modelMapperForRequest() {
        when(modelMapperService.forRequest()).thenReturn(modelMapper);
    }

    private void modelMapperForResponse() {
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
    }

    @Test
    void addWhenSuccess() {
        // Given
        modelMapperForRequest();
        modelMapperForResponse();

        CreatedAccountRequest createdAccountRequest = new CreatedAccountRequest(
                "Savings Account", "1234567890", true, Action.DEFAULT, new HashSet<>(Arrays.asList(1, 2)), 1001, Arrays.asList(201, 202)
        );

        Account account = new Account(
                "Savings Account", "1234567890", true, Action.DEFAULT, new HashSet<>(), 1001, Arrays.asList(201, 202)
        );

        CreatedAccountResponse createdAccountResponse = new CreatedAccountResponse(
                "Savings Account", "1234567890", true, Action.DEFAULT, new HashSet<>(), 1001, Arrays.asList(201, 202)
        );

        when(modelMapper.map(any(CreatedAccountRequest.class), eq(Account.class))).thenReturn(account);
        when(accountTypeService.getById(anyInt())).thenReturn(new AccountType());
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(modelMapper.map(any(Account.class), eq(CreatedAccountResponse.class))).thenReturn(createdAccountResponse);

        // When
        CreatedAccountResponse response = accountManager.add(createdAccountRequest);

        // Then
        Assertions.assertEquals(createdAccountResponse.getAccountName(), response.getAccountName());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void updateWhenSuccess() {
        // Given
        modelMapperForRequest();
        modelMapperForResponse();

        UpdatedAccountRequest updatedAccountRequest = new UpdatedAccountRequest(
                1, "Savings Account", "1234567890", true, Action.EDIT, new HashSet<>(Arrays.asList(1, 2)), 1001, Arrays.asList(201, 202)
        );

        Account account = new Account(
                "Savings Account", "1234567890", true, Action.EDIT, new HashSet<>(), 1001, Arrays.asList(201, 202)
        );

        UpdatedAccountResponse updatedAccountResponse = new UpdatedAccountResponse(
                "Savings Account", "1234567890", true, Action.EDIT, new HashSet<>(), Arrays.asList(201, 202)
        );

        when(modelMapper.map(any(UpdatedAccountRequest.class), eq(Account.class))).thenReturn(account);
        when(accountTypeService.getById(anyInt())).thenReturn(new AccountType());
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(modelMapper.map(any(Account.class), eq(UpdatedAccountResponse.class))).thenReturn(updatedAccountResponse);

        // When
        UpdatedAccountResponse response = accountManager.update(updatedAccountRequest);

        // Then
        Assertions.assertEquals(updatedAccountResponse.getAccountName(), response.getAccountName());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void getByIdWhenSuccess() {
        // Given
        modelMapperForResponse();

        int accountId = 1;
        Account account = new Account(
                "Savings Account", "1234567890", true, Action.DEFAULT, new HashSet<>(), 1001, Arrays.asList(201, 202)
        );

        GetAccountResponse getAccountResponse = new GetAccountResponse(
                "Savings Account", "1234567890", true, Action.DEFAULT, new HashSet<>(), 1001, Arrays.asList(201, 202)
        );

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(modelMapper.map(any(Account.class), eq(GetAccountResponse.class))).thenReturn(getAccountResponse);

        // When
        GetAccountResponse response = accountManager.getById(accountId);

        // Then
        Assertions.assertEquals(getAccountResponse.getAccountName(), response.getAccountName());
    }

    @Test
    void getAllWhenSuccess() {
        // Given
        modelMapperForResponse();

        List<Account> accounts = Arrays.asList(
                new Account("Savings Account", "1234567890", true, Action.DEFAULT, new HashSet<>(), 1001, Arrays.asList(201, 202))
        );

        List<GetAllAccountResponse> getAllAccountResponses = Arrays.asList(
                new GetAllAccountResponse("Savings Account", "1234567890", true, Action.DEFAULT, new HashSet<>(), 1001, Arrays.asList(201, 202))
        );

        when(accountRepository.findAll()).thenReturn(accounts);
        when(modelMapper.map(any(Account.class), eq(GetAllAccountResponse.class))).thenReturn(getAllAccountResponses.get(0));

        // When
        List<GetAllAccountResponse> responses = accountManager.getAll();

        // Then
        Assertions.assertEquals(getAllAccountResponses.size(), responses.size());
        Assertions.assertEquals(getAllAccountResponses.get(0).getAccountName(), responses.get(0).getAccountName());
    }

    @Test
    void deleteWhenSuccess() {
        // Given
        int accountId = 1;

        doNothing().when(accountBusinessRules).accountShoulBeExist(accountId);
        doNothing().when(accountRepository).deleteById(accountId);

        // When
        accountManager.delete(accountId);

        // Then
        verify(accountRepository, times(1)).deleteById(accountId);
    }

    @Test
    void checkIfAccountAvailableWhenSuccess() {
        // Given
        int accountId = 1;
        ClientResponse clientResponse = new ClientResponse(true);

        doNothing().when(accountBusinessRules).accountShoulBeExist(accountId);

        // When
        ClientResponse response = accountManager.checkIfAccountAvailable(accountId);

        // Then
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    void checkIfAccountAvailableWhenFail() {
        // Given
        int accountId = 1;
        ClientResponse clientResponse = new ClientResponse(false);

        doThrow(BusinessException.class).when(accountBusinessRules).accountShoulBeExist(accountId);

        // When
        ClientResponse response = accountManager.checkIfAccountAvailable(accountId);

        // Then
        Assertions.assertFalse(response.isSuccess());
    }
}
