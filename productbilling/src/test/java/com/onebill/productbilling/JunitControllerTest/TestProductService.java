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
	public ExpectedException exp = ExpectedException.none();

	@Mock
	private ProductDao dao;

	@InjectMocks
	private static ProductService service = new ProductServiceImpl();
	
	/**
	 * 	Test Cases : Add Product
	 */
	
	@Test
	public void testAddProductNameFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add product with Name : NULL");
		ProductDto d1 = new ProductDto();
		d1.setProductType("Postpaid");
		when(dao.addProduct(d1)).thenReturn(d1);
		service.addProduct(d1);

	}
	@Test
	public void testAddProductNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add product with null inputs");
		ProductDto d1 = new ProductDto();
		d1.setProductType("Postpaid");
		when(dao.addProduct(d1)).thenReturn(d1);
		service.addProduct(null);

	}

	@Test
	public void testAddProductTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add product with Product type : NULL");
		ProductDto d1 = new ProductDto();
		d1.setProductName("JIO");
		when(dao.addProduct(d1)).thenReturn(d1);
		service.addProduct(d1);
	}

	@Test
	public void testAddProductBothFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add product with Name : NULL");
		ProductDto d1 = new ProductDto();
		when(dao.addProduct(d1)).thenReturn(d1);
		service.addProduct(d1);
		service.addProduct(d1);
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
	
	@Test
	public void testUpdateNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update product with null input");
		ProductDto d1 = new ProductDto();
		d1.setProductName("JIO");
		d1.setProductType("Postpaid");
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(null);
	}
	
	@Test
	public void testUpdateIdFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update product with Product ID : NULL");
		ProductDto d1 = new ProductDto();
		d1.setProductName("JIO");
		d1.setProductType("Postpaid");
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(d1);
	}
	
	@Test
	public void testUpdateNameFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update product with Name : NULL");
		ProductDto d1 = new ProductDto();
		d1.setProductId(1);
		d1.setProductType("Postpaid");
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(d1);
	}
	
	@Test
	public void testUpdateTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update product with Product type : NULL");
		ProductDto d1 = new ProductDto();
		d1.setProductId(1);
		d1.setProductName("JIO");
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(d1);
	}
	
	@Test
	public void testUpdateIdNameFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update product with Name : NULL");
		ProductDto d1 = new ProductDto();
		d1.setProductType("Postpaid");
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(d1);
	}
	
	@Test
	public void testUpdateIdTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update product with Product type : NULL");
		ProductDto d1 = new ProductDto();
		d1.setProductName("JIO");
		when(dao.updateProduct(d1)).thenReturn(d1);
		service.updateProduct(d1);
	}
	
	@Test
	public void testUpdateNameTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update product with Name : NULL");
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
	
	@Test
	public void testRemoveFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to remove product of id : 1");
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
	
	@Test
	public void testGetFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No Product Found for JIO");
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
	
	@Test
	public void testGetByIdFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No Products found for given id 1");
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
	
	@Test
	public void testGetAllFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No product present to display");
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
	
	@Test
	public void getProductPlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No plan available for this product");
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
	
	@Test
	public void getProductPlanDetailFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No details available for this plan id (1) of product ID (1)");
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
	
	@Test
	public void getProductPlanChargeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No Extra charges available for this plan");
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
	
	@Test
	public void getProductPlanOverdueFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No overdue details available for this plan 1");
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
