package com.turkcell.customerService.business.concretes;

import com.turkcell.customerService.business.abstracts.CustomerService;
import com.turkcell.customerService.business.abstracts.IndividualCustomerService;
import com.turkcell.customerService.business.dtos.request.create.CreatedIndividualCustomerRequest;
import com.turkcell.customerService.business.dtos.request.update.UpdatedIndividualCustomerRequest;
import com.turkcell.customerService.business.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.customerService.business.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.customerService.business.dtos.response.getAll.GetAllIndividualCustomerResponse;
import com.turkcell.customerService.business.dtos.response.updated.UpdatedIndividualCustomerResponse;
import com.turkcell.customerService.business.rules.IndividualCustomerBusinessRules;
import com.turkcell.customerService.dataAccess.abstracts.IndividualCustomerRepository;
import com.turkcell.customerService.entities.concretes.Customer;
import com.turkcell.customerService.entities.concretes.IndividualCustomer;
import com.turkcell.customerService.kafka.producer.CustomerProducer;
import com.turkcell.commonpackage.events.customer.CreatedCustomerEvent;
import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class IndividualCustomerManager implements IndividualCustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final ModelMapperService modelMapperService;
    private final CustomerService customerService;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private final CustomerProducer customerProducer;


    @Override
    public CreatedIndividualCustomerResponse add(CreatedIndividualCustomerRequest createIndividualCustomerRequest) {
        individualCustomerBusinessRules.checkNatioanlityNo(createIndividualCustomerRequest.getNationalityNo());
        individualCustomerBusinessRules.nationalityNoCanNotBeDuplicated(createIndividualCustomerRequest.getNationalityNo());

        Customer customer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest.getCreateCustomerRequest(), Customer.class);

        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
        individualCustomerBusinessRules.checkIfRealPerson(individualCustomer);
        individualCustomer.setCustomer(customer);

        customerService.saveCustomer(customer);
        individualCustomerRepository.save(individualCustomer);

        CreatedCustomerEvent createdCustomerEvent = this.modelMapperService.forResponse().map(individualCustomer,CreatedCustomerEvent.class);
        createdCustomerEvent.setMessages("customer status is in pending state");
        createdCustomerEvent.setStatus("PENDING");
        customerProducer.sendMessage(createdCustomerEvent);

        return this.modelMapperService.forResponse().map(individualCustomer, CreatedIndividualCustomerResponse.class);
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UpdatedIndividualCustomerRequest updatedIndividualCustomerRequest) {
        individualCustomerBusinessRules.individualCustomerShouldBeExist(updatedIndividualCustomerRequest.getId());

        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updatedIndividualCustomerRequest, IndividualCustomer.class);

        Customer customer = this.modelMapperService.forRequest().map(updatedIndividualCustomerRequest.getUpdatedCustomerRequest(), Customer.class);
        int customerId = individualCustomer.getCustomer().getId();
        customer.setId(customerId);

        individualCustomer.setCustomer(customer);
        //todo : updateCustomerEvent
        customerService.saveCustomer(customer);
        individualCustomerRepository.save(individualCustomer);

        return this.modelMapperService.forResponse().map(individualCustomer, UpdatedIndividualCustomerResponse.class);
    }

    @Override
    public GetIndividualCustomerResponse getById(int id) {
        individualCustomerBusinessRules.individualCustomerShouldBeExist(id);
        individualCustomerBusinessRules.individualCustomerShouldBeActive(id);
        Optional<IndividualCustomer> foundIndividualCustomer = individualCustomerRepository.findByIdActiveIndividualCustomers(id);
        GetIndividualCustomerResponse getIndividualCustomerResponse =
                this.modelMapperService.forResponse().map(foundIndividualCustomer.get(), GetIndividualCustomerResponse.class);
        return getIndividualCustomerResponse;
    }

    @Override
    public List<GetAllIndividualCustomerResponse> getAll() {
        List<IndividualCustomer> individualCustomers = individualCustomerRepository.findAllActiveIndividualCustomers();
        List<GetAllIndividualCustomerResponse> getAllIndividualCustomerResponses = new ArrayList<GetAllIndividualCustomerResponse>();
        for (var customer : individualCustomers){
            GetAllIndividualCustomerResponse getAllIndividualCustomerResponse = this.modelMapperService.forResponse().map(customer,GetAllIndividualCustomerResponse.class);
            getAllIndividualCustomerResponses.add(getAllIndividualCustomerResponse);
        }
        return getAllIndividualCustomerResponses;
    }

    @Override
    public void delete(int id) {
        customerService.deleteCustomer(id);
    }

    @Override
    public void setStatus(int id) {
        individualCustomerBusinessRules.individualCustomerShouldBeExist(id);
        individualCustomerBusinessRules.checkCustomerPassive(id);
        customerService.setActiveCustomer(id);
    }

    //todo : kullanım değerlendirilecek
    @Override
    public ClientResponse checkIfCustomerAvailable(int id) {
        var response = new ClientResponse();
        validateCustomerAvailability(id,response);
        return response;
    }

    private void validateCustomerAvailability(int id, ClientResponse response) {
        try {
            individualCustomerBusinessRules.individualCustomerShouldBeExist(id);
            individualCustomerBusinessRules.checkCustomerActive(id);
            response.setSuccess(true);
        } catch (BusinessException exception) {
            response.setSuccess(false);
        }
    }
}
