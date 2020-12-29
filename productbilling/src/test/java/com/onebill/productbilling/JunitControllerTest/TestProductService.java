package com.onebill.productbilling.JunitControllerTest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.onebill.productbilling.dao.ProductDao;
import com.onebill.productbilling.dto.PlanChargeRespDto;
import com.onebill.productbilling.dto.PlanDetailRespDto;
import com.onebill.productbilling.dto.PlanOverdueRespDto;
import com.onebill.productbilling.dto.PlanRespDto;
import com.onebill.productbilling.dto.ProductDto;
import com.onebill.productbilling.exception.BillingException;
import com.onebill.productbilling.service.ProductService;
import com.onebill.productbilling.service.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestProductService {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private ProductDao dao;

	@InjectMocks
	private static ProductService service = new ProductServiceImpl();
	
	/**
	 * 	Test Cases : Add Product
	 */
	
	@Test(expected = BillingException.class)
	public void testAddProductNameFailure() {
		ProductDto d1 = new ProductDto();
		d1.setProductType("Postpaid");
		when(dao.addProduct(d1)).thenReturn(d1);
		Assert.assertEquals("Failed to add product with Product Name : NULL", service.addProduct(d1));

	}

	@Test(expected = BillingException.class)
	public void testAddProductTypeFailure() {
		ProductDto d1 = new ProductDto();
		d1.setProductName("JIO");
		when(dao.addProduct(d1)).thenReturn(d1);
		Assert.assertEquals("Failed to add product with Product Type : NULL", service.addProduct(d1));
	}

	@Test(expected = BillingException.class)
	public void testAddProductBothFailure() {
		ProductDto d1 = new ProductDto();
		when(dao.addProduct(d1)).thenReturn(d1);
		service.addProduct(d1);
		Assert.assertEquals("Failed to add product with Product Name : NULL", service.addProduct(d1));
	}
	
	@Test
	public void testAddProductSuccess() {
		ProductDto d1 = new ProductDto();
		d1.setProductName("JIO");
		d1.setProductType("Postpaid");
		when(dao.addProduct(d1)).thenReturn(d1);
		Assert.assertEquals(d1, service.addProduct(d1));
	}

	/**
	 * 	Test Cases : Update Product
	 */
	
	@Test(expected = BillingException.class)
	public void testUpdateIdFailure() {
		ProductDto d1 = new ProductDto();
		d1.setProductName("JIO");
		d1.setProductType("Postpaid");
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(d1);
	}
	
	@Test(expected = BillingException.class)
	public void testUpdateNameFailure() {
		ProductDto d1 = new ProductDto();
		d1.setProductId(1);
		d1.setProductType("Postpaid");
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(d1);
	}
	
	@Test(expected = BillingException.class)
	public void testUpdateTypeFailure() {
		ProductDto d1 = new ProductDto();
		d1.setProductId(1);
		d1.setProductName("JIO");
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(d1);
	}
	
	@Test(expected = BillingException.class)
	public void testUpdateIdNameFailure() {
		ProductDto d1 = new ProductDto();
		d1.setProductType("Postpaid");
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(d1);
	}
	
	@Test(expected = BillingException.class)
	public void testUpdateIdTypeFailure() {
		ProductDto d1 = new ProductDto();
		d1.setProductName("JIO");
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(d1);
	}
	
	@Test(expected = BillingException.class)
	public void testUpdateNameTypeFailure() {
		ProductDto d1 = new ProductDto();
		d1.setProductId(1);
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(d1);
	}
	
	@Test
	public void testUpdateProductSuccess() {
		ProductDto d1 = new ProductDto();
		d1.setProductId(1);
		d1.setProductName("JIO");
		d1.setProductType("Postpaid");
		when(dao.updateProduct(d1)).thenReturn(d1);
		Assert.assertEquals(d1, service.updateProduct(d1));
	}

	/**
	 * 	Test Cases : Remove Product
	 */
	
	@Test(expected = BillingException.class)
	public void testRemoveFailure() {
		when(dao.removeproduct(1)).thenReturn(null);
		Assert.assertNull(service.removeproduct(1));
	}
	
	@Test
	public void testRemoveSuccess() {
		ProductDto d1 = new ProductDto();
		d1.setProductId(1);
		d1.setProductName("JIO");
		d1.setProductType("Postpaid");
		when(dao.removeproduct(1)).thenReturn(d1);
		Assert.assertNotNull(service.removeproduct(1));
	}
	
	/**
	 * 	Test Cases : get Product
	 */
	
	@Test(expected = BillingException.class)
	public void testGetFailure() {
		when(dao.getProduct("JIO")).thenReturn(null);
		Assert.assertNull(service.getProduct("JIO"));
	}
	
	@Test
	public void testGetSuccess() {
		ProductDto d1 = new ProductDto();
		d1.setProductId(1);
		d1.setProductName("JIO");
		d1.setProductType("Postpaid");
		when(dao.getProduct("JIO")).thenReturn(d1);
		Assert.assertEquals(d1,service.getProduct("JIO"));
	}
	
	@Test(expected = BillingException.class)
	public void testGetByIdFailure() {
		when(dao.getProductWithId(1)).thenReturn(null);
		Assert.assertNull(service.getProductWithId(1));
	}
	
	@Test
	public void testGetByIdSuccess() {
		ProductDto d1 = new ProductDto();
		d1.setProductId(1);
		d1.setProductName("JIO");
		d1.setProductType("Postpaid");
		when(dao.getProductWithId(1)).thenReturn(d1);
		Assert.assertEquals(d1,service.getProductWithId(1));
	}
	
	@Test(expected = BillingException.class)
	public void testGetAllFailure() {
		when(dao.getAllProducts()).thenReturn(new ArrayList<ProductDto>());
		Assert.assertNull(service.getAllProducts());
	}
	
	@Test
	public void testGetAllSuccess() {
		ProductDto d1 = new ProductDto();
		d1.setProductId(1);
		d1.setProductName("JIO");
		d1.setProductType("Postpaid");
		ProductDto d2 = new ProductDto();
		d2.setProductId(2);
		d2.setProductName("Airtel");
		d2.setProductType("Postpaid");
		when(dao.getAllProducts()).thenReturn(Arrays.asList(d1,d2));
		Assert.assertEquals(d1.getProductId(),service.getAllProducts().get(0).getProductId());
	}
	
	/**
	 * 	Test Cases : get Product Plan
	 */
	
	@Test(expected = BillingException.class)
	public void getProductPlanFailure() {
		when(dao.getProductPlan(1)).thenReturn(null);
		Assert.assertNull(service.getProductPlan(1));
	}
	
	@Test
	public void getProductPlanSuccess() {
		PlanRespDto p=new PlanRespDto();
		p.setPlanId(1);
		p.setType("Data");
		p.setPlanAmount(100);
		p.setPlanFrequency(28);
		when(dao.getProductPlan(1)).thenReturn(Arrays.asList(p));
		Assert.assertEquals(1,service.getProductPlan(1).size());
	}

	/**
	 * 	Test Cases : get Product Plan Detail
	 */
	
	@Test(expected = BillingException.class)
	public void getProductPlanDetailFailure() {
		when(dao.getPlanDetail(1,1)).thenReturn(null);
		Assert.assertNull(service.getPlanDetail(1,1));
	}
	
	@Test
	public void getProductPlanDetailSuccess() {
		PlanDetailRespDto p=new PlanDetailRespDto();
		p.setServiceType("Data");
		p.setDetail(20);
		p.setUnit("GB");
		when(dao.getPlanDetail(1,1)).thenReturn(Arrays.asList(p));
		Assert.assertEquals(1,service.getPlanDetail(1,1).size());
	}

	/**
	 * 	Test Cases : get Product Plan Charge
	 */
	
	@Test(expected = BillingException.class)
	public void getProductPlanChargeFailure() {
		when(dao.getPlanCharge(1,1)).thenReturn(null);
		Assert.assertNull(service.getPlanCharge(1,1));
	}
	
	@Test
	public void getProductPlanChargeSuccess() {
		PlanChargeRespDto p=new PlanChargeRespDto();
		p.setCharge(100);
		p.setChargeType("Activation");
		when(dao.getPlanCharge(1,1)).thenReturn(Arrays.asList(p));
		Assert.assertEquals(1,service.getPlanCharge(1,1).size());
	}
	/**
	 * 	Test Cases : get Product Plan Overdue
	 */
	
	@Test(expected = BillingException.class)
	public void getProductPlanOverdueFailure() {
		when(dao.getPlanOverdue(1,1)).thenReturn(null);
		Assert.assertNull(service.getPlanOverdue(1,1));
	}
	
	@Test
	public void getProductPlanOverdueSuccess() {
		PlanOverdueRespDto p=new PlanOverdueRespDto();
		p.setOverageService(1);
		p.setOverageType("Data");
		p.setServiceCost(40);
		p.setUnit("GB");
		when(dao.getPlanOverdue(1,1)).thenReturn(Arrays.asList(p));
		Assert.assertEquals(1,service.getPlanOverdue(1,1).size());
	}
}
