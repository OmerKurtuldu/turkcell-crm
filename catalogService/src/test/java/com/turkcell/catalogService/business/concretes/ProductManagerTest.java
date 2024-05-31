package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.abstacts.CategoryService;
import com.turkcell.catalogService.business.abstacts.FeatureService;
import com.turkcell.catalogService.business.abstacts.ProductFeatureService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;
import com.turkcell.catalogService.business.dtos.request.create.ProductFeatureRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedProductRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;
import com.turkcell.catalogService.business.dtos.response.create.ProductFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetProductResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllProductResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedProductResponse;
import com.turkcell.catalogService.business.rules.ProductBusinessRules;
import com.turkcell.catalogService.dataAccess.abstracts.ProductRepository;
import com.turkcell.catalogService.entities.concretes.Feature;
import com.turkcell.catalogService.entities.concretes.Product;
import com.turkcell.catalogService.entities.concretes.ProductFeature;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductManagerTest {

    @Mock
    private ModelMapperService modelMapperService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private FeatureService featureService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private ProductFeatureService productFeatureService;
    @Mock
    private ProductBusinessRules productBusinessRules;
    @InjectMocks
    private ProductManager productManager;

    @Captor
    private ArgumentCaptor<Object> objectArgumentCaptor;

    //todo buna bakılacak
    private void modelMapperForRequest() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(any(), any())).thenAnswer(invocation -> {
            Object source = invocation.getArgument(0);
            Object destination = invocation.getArgument(1);
            // Add your own logic to map properties if needed
            return destination;
        });
    }

    private void modelMapperForResponse() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapper.map(any(), any())).thenAnswer(invocation -> {
            Object source = invocation.getArgument(0);
            Object destination = invocation.getArgument(1);
            // Add your own logic to map properties if needed
            return destination;
        });
    }

//    @Test
//    void addProductWhenSuccess() {
//        // Given
//        modelMapperForRequest();
//        modelMapperForResponse();
//
//        CreatedProductRequest createdProductRequest = new CreatedProductRequest();
//        createdProductRequest.setName("Product1");
//        createdProductRequest.setQuantity(10);
//        createdProductRequest.setPrice(new BigDecimal("100.00"));
//        createdProductRequest.setCategoryId(1);
//        createdProductRequest.setProductFeatures(Collections.singletonList(new ProductFeatureRequest(1, "Value1")));
//
//        Product product = new Product();
//        product.setId(0);
//        product.setProductName("Product1");
//        product.setQuantity(10);
//        product.setPrice(100.0);
//
//        Feature feature = new Feature();
//        feature.setId(1);
//        feature.setName("Feature1");
//
//        ProductFeature productFeature = new ProductFeature();
//        productFeature.setProduct(product);
//        productFeature.setFeature(feature);
//        productFeature.setValue("Value1");
//
//        CreatedProductResponse createdProductResponse = new CreatedProductResponse();
//        createdProductResponse.setId(1);
//        createdProductResponse.setName("Product1");
//        createdProductResponse.setQuantity(10);
//        createdProductResponse.setPrice(new BigDecimal("100.00"));
//        createdProductResponse.setProductFeatures(Collections.singletonList(new ProductFeatureResponse("Feature1", "Value1")));
//
//        when(modelMapperService.forRequest().map(any(CreatedProductRequest.class), eq(Product.class))).thenReturn(product);
//        when(productRepository.save(any(Product.class))).thenReturn(product);
//        when(modelMapperService.forResponse().map(any(Product.class), eq(CreatedProductResponse.class))).thenReturn(createdProductResponse);
//        when(modelMapperService.forResponse().map(any(ProductFeature.class), eq(ProductFeatureResponse.class))).thenReturn(new ProductFeatureResponse("Feature1", "Value1"));
//        when(featureService.getById(anyInt())).thenReturn(new GetFeatureResponse(1, "Feature1"));
//
//        // When
//        CreatedProductResponse response = productManager.addProduct(createdProductRequest);
//
//        // Then
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals("Product1", response.getName());
//        verify(productRepository, times(1)).save(any(Product.class));
//    }

    @Test
    void updateProductWhenSuccess() {
        // Given
        modelMapperForRequest();
        modelMapperForResponse();

        UpdatedProductRequest updatedProductRequest = new UpdatedProductRequest();
        updatedProductRequest.setId(1);
        updatedProductRequest.setName("UpdatedProduct");
        updatedProductRequest.setQuantity(20);
        updatedProductRequest.setPrice(new BigDecimal("200.00"));
        updatedProductRequest.setProductFeatures(Collections.singletonList(new ProductFeatureRequest(1, "UpdatedValue")));

        Product product = new Product();
        product.setId(1);
        product.setProductName("UpdatedProduct");
        product.setQuantity(20);
        product.setPrice(200.0);

        ProductFeatureResponse productFeatureResponse = new ProductFeatureResponse("Feature1", "UpdatedValue");

        UpdatedProductResponse updatedProductResponse = new UpdatedProductResponse();
        updatedProductResponse.setId(1);
        updatedProductResponse.setName("UpdatedProduct");
        updatedProductResponse.setQuantity(20);
        updatedProductResponse.setPrice(new BigDecimal("200.00"));
        updatedProductResponse.setProductFeatures(Collections.singletonList(productFeatureResponse));

        when(modelMapperService.forRequest().map(any(UpdatedProductRequest.class), eq(Product.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productFeatureService.updateFeatureForProduct(anyInt(), anyList())).thenReturn(Collections.singletonList(productFeatureResponse));
        when(modelMapperService.forResponse().map(any(Product.class), eq(UpdatedProductResponse.class))).thenReturn(updatedProductResponse);

        // When
        UpdatedProductResponse response = productManager.updateProduct(updatedProductRequest);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals("UpdatedProduct", response.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void getByIdWhenSuccess() {
        // Given
        modelMapperForResponse();

        int productId = 1;

        Product product = new Product();
        product.setId(productId);
        product.setProductName("Product1");
        product.setQuantity(10);
        product.setPrice(100.0);

        Feature feature = new Feature();
        feature.setId(1);
        feature.setName("Feature1");

        ProductFeature productFeature = new ProductFeature();
        productFeature.setFeature(feature);
        productFeature.setValue("Value1");

        GetProductResponse getProductResponse = new GetProductResponse();
        getProductResponse.setId(productId);
        getProductResponse.setName("Product1");
        getProductResponse.setQuantity(10);
        getProductResponse.setPrice(new BigDecimal("100.00"));
        getProductResponse.setProductFeatures(Collections.singletonList(new ProductFeatureResponse("Feature1", "Value1")));

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productFeatureService.findByProductId(productId)).thenReturn(Collections.singletonList(productFeature));
        when(modelMapperService.forResponse().map(any(Product.class), eq(GetProductResponse.class))).thenReturn(getProductResponse);

        // When
        GetProductResponse response = productManager.getById(productId);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Product1", response.getName());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void getAllWhenSuccess() {
        // Given
        modelMapperForResponse();

        Product product = new Product();
        product.setId(1);
        product.setProductName("Product1");
        product.setQuantity(10);
        product.setPrice(100.0);

        Feature feature = new Feature();
        feature.setId(1);
        feature.setName("Feature1");

        ProductFeature productFeature = new ProductFeature();
        productFeature.setFeature(feature);
        productFeature.setValue("Value1");

        GetAllProductResponse getAllProductResponse = new GetAllProductResponse();
        getAllProductResponse.setId(1);
        getAllProductResponse.setName("Product1");
        getAllProductResponse.setQuantity(10);
        getAllProductResponse.setPrice(new BigDecimal("100.00"));
        getAllProductResponse.setProductFeatures(Collections.singletonList(new ProductFeatureResponse("Feature1", "Value1")));

        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        when(productFeatureService.findByProductId(anyInt())).thenReturn(Collections.singletonList(productFeature));
        when(modelMapperService.forResponse().map(any(Product.class), eq(GetAllProductResponse.class))).thenReturn(getAllProductResponse);

        // When
        List<GetAllProductResponse> responses = productManager.getAll();

        // Then
        Assertions.assertNotNull(responses);
        Assertions.assertEquals(1, responses.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void deleteWhenSuccess() {
        // Given
        int productId = 1;

        doNothing().when(productBusinessRules).productShouldBeExist(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));

        // When
        productManager.delete(productId);

        // Then
        verify(productRepository, times(1)).deleteById(productId);
    }
}
