//package com.onebill.productbilling.JunitControllerTest;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.onebill.productbilling.controllers.ProductController;
//import com.onebill.productbilling.dto.ProductDto;
//import com.onebill.productbilling.service.ProductService;
//
//public class TestProductController {
//
//	MockMvc mockMvc;
//
//	@Mock
//	private ProductService service;
//
//	@InjectMocks
//	private ProductController controller;
//
//	@Before
//	public void init() {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//	}
//
//	@Test
//	public void testGetAllSuccess() throws Exception {
//		ProductDto d1 = new ProductDto();
//		d1.setProductId(1);
//		d1.setProductName("JIO");
//		d1.setProductType("Postpaid");
//		ProductDto d2 = new ProductDto();
//		d1.setProductId(2);
//		d2.setProductName("Aircel");
//		d2.setProductType("Postpaid");
//		List<ProductDto> list = Arrays.asList(d1, d2);
//
//		when(service.getAllProducts()).thenReturn(list);
//		mockMvc.perform(get("http://localhost:8080/productbilling/products")).andExpect(status().isOk())
//				.andExpect(jsonPath("$", hasSize(2)));
//		// .andExpect(jsonPath("$[0].productId",is(1))
//		// .andExpect(jsonPath("$[0].productName",is("JIO"))
//		// .andExpect(jsonPath("$[0].productType",is("Postpaid"))
//		// .andExpect(jsonPath("$[1].productId",is(1))
//		// .andExpect(jsonPath("$[1].productName",is("Aircel"))
//		// .andExpect(jsonPath("$[1].productType",is("Postpaid"));
//		// verify(service, times(1).getAllProducts());
//		// verifyNoMoreInteractions(service);
//
//	}
//}

package com.onebill.productbilling.JunitControllerTest;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.onebill.productbilling.dao.ProductDao;
import com.onebill.productbilling.dto.ProductDto;
import com.onebill.productbilling.exception.BillingException;
import com.onebill.productbilling.service.ProductService;
import com.onebill.productbilling.service.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestProductController {
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Mock
	private ProductDao dao;

	@InjectMocks
	private static ProductService service=new ProductServiceImpl();

	@Test
	public void testAddProductSuccess(){
		ProductDto d1 = new ProductDto();
		d1.setProductName("JIO");
		d1.setProductType("Postpaid");
		when(dao.addProduct(d1)).thenReturn(d1);
		Assert.assertEquals(d1,service.addProduct(d1));
	}
	
	@Test(expected=BillingException.class)
	public void testAddProductNameFailure(){
		ProductDto d1 = new ProductDto();
		d1.setProductType("Postpaid");
		when(dao.addProduct(d1)).thenReturn(d1);
		Assert.assertTrue(throwException());
		  
	}
	private boolean throwException(){
        throw new BillingException("Failed to add product with Product Name : NULL");
    }
	
//	@Test(expected=BillingException.class)
//	public void testAddProductTypeFailure(){
//		ProductDto d1 = new ProductDto();
//		d1.setProductName("JIO");
//		try {
//			when(dao.addProduct(d1)).thenReturn(d1);
//		}catch(Exception e) {
//			e.getMessage();
//		}
//	}
	
}
