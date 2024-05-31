package com.turkcell.customerService.business.concretes;

import com.turkcell.corepackage.utils.exceptions.types.BusinessException;

import com.turkcell.customerService.business.rules.AddressBusinessRules;
import com.turkcell.customerService.business.abstracts.CustomerService;
import com.turkcell.customerService.business.abstracts.CityService;
import com.turkcell.customerService.business.dtos.request.create.CreatedAddressRequest;
import com.turkcell.customerService.business.dtos.request.update.UpdatedAddressRequest;
import com.turkcell.customerService.business.dtos.response.create.CreatedAddressResponse;
import com.turkcell.customerService.business.dtos.response.updated.UpdatedAddressResponse;
import com.turkcell.customerService.business.dtos.response.get.GetAddressResponse;
import com.turkcell.customerService.business.dtos.response.getAll.GetAllAddressResponse;
import com.turkcell.customerService.dataAccess.abstracts.AddressRepository;
import com.turkcell.customerService.entities.concretes.Address;
import com.turkcell.customerService.entities.concretes.City;
import com.turkcell.customerService.entities.concretes.Customer;
import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressManagerTest {

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private CityService cityService;

    @Mock
    private AddressBusinessRules addressBusinessRules;

    @InjectMocks
    private AddressManager addressManager;

    private void modelMapperForResponse(){
        Mockito.when(modelMapperService.forResponse()).thenReturn(mock(ModelMapper.class));
    }

    private void modelMapperForRequest(){
        Mockito.when(modelMapperService.forRequest()).thenReturn(mock(ModelMapper.class));
    }

    @Test
    void addWhenSuccess() {
        // Given
        modelMapperForRequest();
        modelMapperForResponse();

        CreatedAddressRequest createdAddressRequest = new CreatedAddressRequest(1, "725 nolu sk", "5", "Bahçelievler mahallesi", 1);

        Address address = new Address();
        address.setId(1);
        address.setStreet("Street");

        Customer customer = new Customer("omerkurtuldu11@gmail.com", "05075979691", true, null, null);
        City city = new City();
        city.setId(1);

        when(customerService.getById(anyInt())).thenReturn(customer);
        when(cityService.getById(anyInt())).thenReturn(city);
        when(modelMapperService.forRequest().map(any(CreatedAddressRequest.class), eq(Address.class))).thenReturn(address);
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        CreatedAddressResponse createdAddressResponse = new CreatedAddressResponse("725 nolu sk", "5", "Bahçelievler mahallesi", "Ömer", "Kurtuldu", "Sakarya");
        when(modelMapperService.forResponse().map(any(Address.class), eq(CreatedAddressResponse.class))).thenReturn(createdAddressResponse);

        // When
        CreatedAddressResponse response = addressManager.add(createdAddressRequest);

        // Then
        Assertions.assertEquals(createdAddressResponse.getStreet(), response.getStreet());
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void updateWhenSuccess() {
        // Given
        modelMapperForRequest();
        modelMapperForResponse();

        UpdatedAddressRequest updatedAddressRequest = new UpdatedAddressRequest(1, 54, "725 nolu sk", "5", "Bahçelievler mahallesi", 1);

        Address address = new Address();
        address.setId(1);
        address.setStreet("Updated Street");

        Customer customer = new Customer("omerkurtuldu11@gmail.com", "05075979691", true, null, null);
        City city = new City();
        city.setId(1);

        doNothing().when(addressBusinessRules).addressShouldBeExist(anyInt());
        when(customerService.getById(anyInt())).thenReturn(customer);
        when(cityService.getById(anyInt())).thenReturn(city);
        when(modelMapperService.forRequest().map(any(UpdatedAddressRequest.class), eq(Address.class))).thenReturn(address);
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        UpdatedAddressResponse updatedAddressResponse = new UpdatedAddressResponse("725 nolu sk", "5", "Bahçelievler mahallesi", "Ömer", "Kurtuldu", "Sakarya");
        when(modelMapperService.forResponse().map(any(Address.class), eq(UpdatedAddressResponse.class))).thenReturn(updatedAddressResponse);

        // When
        UpdatedAddressResponse response = addressManager.update(updatedAddressRequest);

        // Then
        Assertions.assertEquals(updatedAddressResponse.getStreet(), response.getStreet());
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void getByIdWhenSuccess() {
        // Given
        modelMapperForResponse();

        Address address = new Address();
        address.setId(1);
        address.setStreet("Street");

        doNothing().when(addressBusinessRules).addressShouldBeExist(anyInt());
        when(addressRepository.findById(anyInt())).thenReturn(Optional.of(address));
        GetAddressResponse getAddressResponse = new GetAddressResponse("725 nolu sokak", "5", "bahçelievler mahallesi", "Ömer", "Kurtuldu", "Sakarya");
        when(modelMapperService.forResponse().map(any(Address.class), eq(GetAddressResponse.class))).thenReturn(getAddressResponse);

        // When
        GetAddressResponse response = addressManager.getById(1);

        // Then
        Assertions.assertEquals(getAddressResponse.getStreet(), response.getStreet());
        verify(addressRepository, times(1)).findById(anyInt());
    }

    @Test
    void getAllWhenSuccess() {
        // Given
        modelMapperForResponse();

        Address address = new Address();
        address.setId(1);
        address.setStreet("Street");
        List<Address> addressList = List.of(address);

        when(addressRepository.findAll()).thenReturn(addressList);
        GetAllAddressResponse getAllAddressResponse = new GetAllAddressResponse("725 nolu sokak", "5", "bahçelievler mahallesi", "Ömer", "Kurtuldu", "Sakarya");
        when(modelMapperService.forResponse().map(any(Address.class), eq(GetAllAddressResponse.class))).thenReturn(getAllAddressResponse);

        // When
        List<GetAllAddressResponse> responseList = addressManager.getAll();

        // Then
        Assertions.assertFalse(responseList.isEmpty());
        Assertions.assertEquals(getAllAddressResponse.getStreet(), responseList.get(0).getStreet());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void deleteWhenSuccess() {
        // When
        doNothing().when(addressBusinessRules).addressShouldBeExist(anyInt());
        doNothing().when(addressRepository).deleteById(anyInt());

        // Then
        Assertions.assertDoesNotThrow(() -> {
            addressManager.delete(1);
        });
        verify(addressRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void checkIfAddressAvailableWhenSuccess() {
        // When
        doNothing().when(addressBusinessRules).addressShouldBeExist(anyInt());

        // Then
        ClientResponse response = addressManager.checkIfAddressAvailable(1);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    void checkIfAddressAvailableWhenFail() {
        // When
        doThrow(BusinessException.class).when(addressBusinessRules).addressShouldBeExist(anyInt());

        // Then
        ClientResponse response = addressManager.checkIfAddressAvailable(1);
        Assertions.assertFalse(response.isSuccess());
    }
}
