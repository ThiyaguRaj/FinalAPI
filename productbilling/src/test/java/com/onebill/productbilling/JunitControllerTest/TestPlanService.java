package com.onebill.productbilling.JunitControllerTest;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.onebill.productbilling.Entities.Plan;
import com.onebill.productbilling.Entities.Product;
import com.onebill.productbilling.dao.PlanDao;
import com.onebill.productbilling.dto.PlanDetailDto;
import com.onebill.productbilling.dto.PlanDetailRespDto;
import com.onebill.productbilling.dto.PlanDto;
import com.onebill.productbilling.dto.PlanRespDto;
import com.onebill.productbilling.exception.BillingException;
import com.onebill.productbilling.service.PlanService;
import com.onebill.productbilling.service.PlanServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestPlanService {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private PlanDao dao;

	@InjectMocks
	private static PlanService service = new PlanServiceImpl();

	private Product pro;

	private PlanDto dto;

	private PlanRespDto p;

	private Plan plan;

	private PlanDetailDto det;

	private PlanDetailRespDto res;

	@Before
	public void set() {
		pro = new Product();
		pro.setProductId(1);
		dto = new PlanDto();
		dto.setPlanAmount(200);
		dto.setType("Data");
		dto.setProduct(pro);
		dto.setPlanFrequency(28);
		p = new PlanRespDto();
		BeanUtils.copyProperties(dto, p);
		plan = new Plan();
		p.setPlanId(1);
		det = new PlanDetailDto();
		det.setDetail(20);
		det.setServiceType("Data");
		det.setUnit("GB");
		det.setPlan(plan);
		res = new PlanDetailRespDto();
		BeanUtils.copyProperties(det, res);
	}

	/**
	 * Test Cases : Add Plan
	 */

	@Test(expected = BillingException.class)
	public void testAddPlanAmountFailure() {
		dto.setPlanAmount(0.0);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Amount should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanDurationFailure() {
		dto.setPlanFrequency(0);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Duration should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanTypeFailure() {
		dto.setType(null);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Type should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanProductFailure() {
		dto.setProduct(null);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Product should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanProductAmountFailure() {
		dto.setProduct(null);
		dto.setPlanAmount(0.0);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Amount should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanAmountDurationFailure() {
		dto.setPlanFrequency(0);
		dto.setPlanAmount(0.0);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Amount should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanAmountTypeFailure() {
		dto.setType(null);
		dto.setPlanAmount(0.0);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Amount should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanProductTypeFailure() {
		dto.setType(null);
		dto.setProduct(null);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Type should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanProductDurationFailure() {
		dto.setPlanFrequency(0);
		dto.setProduct(null);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Duration should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanTypeDurationFailure() {
		dto.setPlanFrequency(0);
		dto.setType(null);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Duration should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanOnlyType() {
		PlanDto dto = new PlanDto();
		dto.setType("Data");
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan amount should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanOnlyAmount() {
		PlanDto dto = new PlanDto();
		dto.setPlanAmount(100);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Duration should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanOnlyDuration() {
		PlanDto dto = new PlanDto();
		dto.setPlanFrequency(20);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan amount should not be null", service.addPlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanOnlyProduct() {
		PlanDto dto = new PlanDto();
		dto.setProduct(pro);
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan amount should not be null", service.addPlan(dto));
	}

	@Test
	public void testAddPlanSuccess() {
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals(p, service.addPlan(dto));
	}

	/**
	 * Test Cases : Get Plan
	 */

	@Test(expected = BillingException.class)
	public void testGetPlanFailure() {
		when(dao.getPlan(1)).thenReturn(null);
		Assert.assertNull(service.addPlan(dto));
	}

	@Test
	public void testGetPlanSuccess() {
		when(dao.getPlan(1)).thenReturn(Arrays.asList(p));
		Assert.assertEquals(1, service.getPlan(1).size());
	}

	@Test(expected = BillingException.class)
	public void testGetAllPlansFailure() {
		when(dao.getAllPlans()).thenReturn(null);
		Assert.assertNull(service.getAllPlans());
	}

	@Test
	public void testGetAllPlansSuccess() {
		when(dao.getAllPlans()).thenReturn(Arrays.asList(p));
		Assert.assertEquals(1, service.getAllPlans().size());
	}

	/**
	 * Test Cases : Update Plan
	 */

	@Test(expected = BillingException.class)
	public void testUpdatelanAmountFailure() {
		dto.setPlanAmount(0.0);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Amount should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanDurationFailure() {
		dto.setPlanFrequency(0);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Duration should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanTypeFailure() {
		dto.setType(null);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Type should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanProductFailure() {
		dto.setProduct(null);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Product should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanProductAmountFailure() {
		dto.setProduct(null);
		dto.setPlanAmount(0.0);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Amount should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanAmountDurationFailure() {
		dto.setPlanFrequency(0);
		dto.setPlanAmount(0.0);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Amount should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanAmountTypeFailure() {
		dto.setType(null);
		dto.setPlanAmount(0.0);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Amount should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanProductTypeFailure() {
		dto.setType(null);
		dto.setProduct(null);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Type should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanProductDurationFailure() {
		dto.setPlanFrequency(0);
		dto.setProduct(null);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Duration should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanTypeDurationFailure() {
		dto.setPlanFrequency(0);
		dto.setType(null);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Duration should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanOnlyType() {
		PlanDto dto = new PlanDto();
		dto.setType("Data");
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan amount should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanOnlyAmount() {
		PlanDto dto = new PlanDto();
		dto.setPlanAmount(100);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan Duration should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanOnlyDuration() {
		PlanDto dto = new PlanDto();
		dto.setPlanFrequency(20);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan amount should not be null", service.updatePlan(dto));
	}

	@Test(expected = BillingException.class)
	public void testUpdatePlanOnlyProduct() {
		PlanDto dto = new PlanDto();
		dto.setProduct(pro);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan amount should not be null", service.updatePlan(dto));
	}

	@Test
	public void testUpdatePlanSuccess() {
		dto.setPlanId(1);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals(p, service.updatePlan(dto));
	}

	/**
	 * Test Cases : Remove Plan
	 */

	@Test(expected = BillingException.class)
	public void testRemovePlanFail() {
		when(dao.removePlan(1)).thenReturn(null);
		Assert.assertNull(service.removePlan(1));
	}

	@Test
	public void testRemovePlanSuccess() {
		when(dao.removePlan(1)).thenReturn(p);
		Assert.assertNotNull(service.removePlan(1));
	}

	/**
	 * Test Cases : Add Plan Detail
	 */
	@Test
	public void testAddPlanDetailSuccess() {
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals(res, service.addPlanDetail(det));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanDetailDetailFail() {
		det.setDetail(0);
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals("Failed to add plan detail with null detail value", service.addPlanDetail(det));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanDetailServiceFail() {
		det.setServiceType(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals("Failed to add plan detail with null Service Type", service.addPlanDetail(det));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanDetailPlanFail() {
		det.setPlan(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals("Failed to add plan detail without Plan", service.addPlanDetail(det));
	}

	@Test(expected = BillingException.class)
	public void testAddPlanDetailUnitFail() {
		det.setUnit(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals("Failed to add plan detail with null Unit", service.addPlanDetail(det));
	}
	
	@Test(expected = BillingException.class)
	public void testAddPlanDetailServicePlanFail() {
		det.setPlan(null);
		det.setServiceType(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals("Failed to add plan detail with null service type", service.addPlanDetail(det));
	}
	@Test(expected = BillingException.class)
	public void testAddPlanDetailServiceUnitFail() {
		det.setUnit(null);
		det.setServiceType(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals("Failed to add plan detail with null service type", service.addPlanDetail(det));
	}
	@Test(expected = BillingException.class)
	public void testAddPlanDetailServiceDetailFail() {
		det.setDetail(0);
		det.setServiceType(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals("Failed to add plan detail with null service type", service.addPlanDetail(det));
	}
	@Test(expected = BillingException.class)
	public void testAddPlanDetailPlanUnitFail() {
		det.setUnit(null);
		det.setPlan(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals("Failed to add plan detail with null Unit", service.addPlanDetail(det));
	}
	@Test(expected = BillingException.class)
	public void testAddPlanDetailPlanDetailFail() {
		det.setDetail(0);
		det.setPlan(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals("Failed to add plan detail with null Detail value", service.addPlanDetail(det));
	}
	@Test(expected = BillingException.class)
	public void testAddPlanDetailUnitDetailFail() {
		det.setDetail(0);
		det.setUnit(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals("Failed to add plan detail with null Detail value", service.addPlanDetail(det));
	}

}
