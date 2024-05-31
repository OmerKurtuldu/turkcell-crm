package com.turkcell.customerService.business.concretes;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.commonpackage.utils.enums.GenderType;
import com.turkcell.corepackage.entities.BaseEntity;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import com.turkcell.customerService.business.abstracts.CustomerService;
import com.turkcell.customerService.business.dtos.request.update.UpdatedCustomerRequest;
import com.turkcell.customerService.business.dtos.request.update.UpdatedIndividualCustomerRequest;
import com.turkcell.customerService.business.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.customerService.business.dtos.response.getAll.GetAllIndividualCustomerResponse;
import com.turkcell.customerService.business.dtos.response.updated.UpdatedIndividualCustomerResponse;
import com.turkcell.customerService.business.rules.IndividualCustomerBusinessRules;
import com.turkcell.customerService.dataAccess.abstracts.IndividualCustomerRepository;
import com.turkcell.customerService.entities.concretes.Customer;
import com.turkcell.customerService.entities.concretes.IndividualCustomer;
import com.turkcell.customerService.kafka.producer.CustomerProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndividualCustomerManagerTest {
    @Mock
    private ModelMapperService modelMapperService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private IndividualCustomerRepository individualCustomerRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;
    @Mock
    private CustomerProducer customerProducer;
    @InjectMocks
    private IndividualCustomerManager individualCustomerManager;

    private void modelMapperForResponse(){
        Mockito.when(modelMapperService.forResponse()).thenReturn(modelMapper);
    }
    private void modelMapperForRequest(){
        Mockito.when(modelMapperService.forRequest()).thenReturn(modelMapper);
    }
    @Test
    void getByIdWhenSuccess() {
        // given
        this.modelMapperForResponse();
        List<IndividualCustomer> individualCustomers = this.initCustomerAndIndividualCustomer();
        IndividualCustomer foundIndividualCustomer = individualCustomers.get(0);
        GetIndividualCustomerResponse getIndividualCustomerResponse = new GetIndividualCustomerResponse(1, null,"Yasin",null,"Özyazıcı", LocalDate.now(), GenderType.MALE,"Hasan","Ayşe","46165855550","05075979691","burakkolay487@gmail.com","05075979691");
        // when
        doNothing().when(individualCustomerBusinessRules).individualCustomerShouldBeExist(anyInt());
        when(individualCustomerRepository.findByIdActiveIndividualCustomers(1))
                .thenReturn(Optional.of(foundIndividualCustomer));
        when(modelMapperService.forResponse().map(foundIndividualCustomer,GetIndividualCustomerResponse.class))
                .thenReturn(getIndividualCustomerResponse);
        // then
        GetIndividualCustomerResponse byId = individualCustomerManager.getById(1);
        Assertions.assertEquals(getIndividualCustomerResponse.getId(),byId.getId());
        Assertions.assertEquals(getIndividualCustomerResponse.getEmail(),byId.getEmail());
    }

    @Test
    void getByIdWhenIndividualCustomerShouldBeExist(){
        // when
        doThrow(BusinessException.class).when(individualCustomerBusinessRules).individualCustomerShouldBeExist(anyInt());
        // then
        Assertions.assertThrows(BusinessException.class,() ->individualCustomerManager.getById(1));
    }

    @Test
    void getByIdWhenIndividualCustomerShouldBeActive(){
        // when
        doThrow(BusinessException.class).when(individualCustomerBusinessRules).individualCustomerShouldBeActive(anyInt());
        // then
        Assertions.assertThrows(BusinessException.class,() ->individualCustomerManager.getById(1));
        Assertions.assertThrows(BusinessException.class,() ->individualCustomerManager.getById(1),"There is a customer with this ID number");
    }

    @Test
    void getAll() {
        // given
        this.modelMapperForResponse();
        List<IndividualCustomer> individualCustomerList = this.initCustomerAndIndividualCustomer();

        List<GetAllIndividualCustomerResponse> getAllIndividualCustomerResponses = new ArrayList<>();
        GetAllIndividualCustomerResponse getAllIndividualCustomerResponse = new GetAllIndividualCustomerResponse(1, null,"Yasin",null,"Özyazıcı", LocalDate.now(), GenderType.MALE,"Hasan","Ayşe","46165855550","05075979691","burakkolay487@gmail.com","05075979691");
        getAllIndividualCustomerResponses.add(getAllIndividualCustomerResponse);
        // when
        Mockito.when(individualCustomerRepository.findAllActiveIndividualCustomers())
                .thenReturn(individualCustomerList);
        Mockito.when(modelMapperService.forResponse().map(Mockito.any(),Mockito.any()))
                .thenReturn(getAllIndividualCustomerResponse);
        // then
        List<GetAllIndividualCustomerResponse> byId = individualCustomerManager.getAll();
        Assertions.assertEquals(getAllIndividualCustomerResponse.getId(),byId.get(0).getId());
        Assertions.assertEquals(getAllIndividualCustomerResponse.getEmail(),byId.get(0).getEmail());
    }

    @Test
    void update(){
        this.modelMapperForResponse();
        this.modelMapperForRequest();
        List<IndividualCustomer> individualCustomers = this.initCustomerAndIndividualCustomer();
        // given
        UpdatedCustomerRequest updatedCustomerRequest = new UpdatedCustomerRequest("burakkolay487@gmail.com","05075979692");
        UpdatedIndividualCustomerRequest updatedIndividualCustomerRequest = new UpdatedIndividualCustomerRequest(1,"Yasin",
                null,"Özyazıcı", LocalDate.now(),
                GenderType.MALE,"Hasan","Ayşe",
                "05075979692",updatedCustomerRequest);
        IndividualCustomer individualCustomer = individualCustomers.get(0);
        Customer customer = new Customer("burakkolay487@gmail.com","05075979692",true,null,null);
        customer.setId(1);
        individualCustomer.setCustomer(customer);
        UpdatedIndividualCustomerResponse updatedIndividualCustomerResponse = new UpdatedIndividualCustomerResponse("Yasin",
                null,"Özyazıcı", LocalDate.now(),
                GenderType.MALE,"Hasan","Ayşe",
                "burakkolay487@gmail.com","05075979692","05075979692");

        // when
        when(modelMapperService.forRequest().map(any(),eq(IndividualCustomer.class))).thenReturn(individualCustomer);
        when(modelMapperService.forRequest().map(updatedIndividualCustomerRequest.getUpdatedCustomerRequest(),Customer.class)).thenReturn(customer);
        when(modelMapperService.forResponse().map(any(),eq(UpdatedIndividualCustomerResponse.class))).thenReturn(updatedIndividualCustomerResponse);

        // then
        UpdatedIndividualCustomerResponse update = individualCustomerManager.update(updatedIndividualCustomerRequest);
        System.out.println(update);

    }


    private List<IndividualCustomer> initCustomerAndIndividualCustomer(){
        List<IndividualCustomer> individualCustomerList = new ArrayList<>();
        Customer customer1 = new Customer("burakkolay487@gmail.com","05075979691",true,null,null);
        customer1.setId(1);
        IndividualCustomer foundIndividualCustomer1 = new IndividualCustomer("Yasin",
                null,"Özyazıcı", LocalDate.now(),
                GenderType.MALE,"Hasan","Ayşe",
                "46165855550","05075979692",customer1);
        individualCustomerList.add(foundIndividualCustomer1);
        Customer customer2 = new Customer("burakkolay487@gmail.com","05075979691",true,null,null);
        customer2.setId(2);
        IndividualCustomer foundIndividualCustomer2 = new IndividualCustomer("Yasin",
                null,"Özyazıcı", LocalDate.now(),
                GenderType.MALE,"Hasan","Ayşe",
                "46165855550","05075979691",customer2);
        individualCustomerList.add(foundIndividualCustomer2);
        Customer customer3 = new Customer("burakkolay487@gmail.com","05075979691",true,null,null);
        customer3.setId(3);
        IndividualCustomer foundIndividualCustomer3 = new IndividualCustomer("Yasin",
                null,"Özyazıcı", LocalDate.now(),
                GenderType.MALE,"Hasan","Ayşe",
                "46165855550","05075979691",customer3);
        individualCustomerList.add(foundIndividualCustomer3);
        return individualCustomerList;
    }
}