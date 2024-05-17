package com.turkcell.CustomerService.business.rules;

import com.turkcell.CustomerService.business.messages.Messages;
import com.turkcell.CustomerService.dataAccess.abstracts.CustomerRepository;
import com.turkcell.CustomerService.dataAccess.abstracts.IndividualCustomerRepository;
import com.turkcell.CustomerService.entities.concretes.Customer;
import com.turkcell.CustomerService.entities.concretes.IndividualCustomer;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class IndividualCustomerBusinessRules {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final CustomerRepository customerRepository;
    private final MessageService messageService;

    public void individualCustomerShouldBeExist(int individualCustomerId) {
        Optional<IndividualCustomer> foundOptionalIndividualCustomer = individualCustomerRepository.findById(individualCustomerId);
        if (foundOptionalIndividualCustomer.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.CustomerErrors.IndividualCustomerShouldBeExists));
        }
    }

    public void nationalityNoCanNotBeDuplicated(String nationalityNo ){
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository.findByNationalityNo(nationalityNo);
        if(individualCustomer.isPresent()){
            throw new BusinessException(messageService.getMessage((Messages.CustomerErrors.IndividualCustomerWithThisIDNumberExist)));
        }
    }

    public void checkNatioanlityNo(String nationalityNo){
        String regex = "^[1-9]{1}[0-9]{9}[02468]{1}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nationalityNo);

        if(!matcher.matches()){
            throw new  BusinessException(messageService.getMessage(Messages.CustomerErrors.CheckNatioanlityNo));
        }
    }

    public void checkCustomerActive(int customerId){
        boolean isActiveCustomer = customerRepository.isActiveCustomer(customerId);
        if (!isActiveCustomer){
           throw new BusinessException(messageService.getMessage(Messages.CustomerErrors.CheckCustomerActive));
        }
    }

    public void checkCustomerPassive(int customerId){
        boolean isActiveCustomer = customerRepository.isActiveCustomer(customerId);
        if (isActiveCustomer){
            throw new BusinessException(messageService.getMessage(Messages.CustomerErrors.CheckCustomerActive));
        }
    }

}
