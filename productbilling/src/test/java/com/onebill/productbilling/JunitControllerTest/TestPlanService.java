package com.onebill.productbilling.JunitControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import com.onebill.productbilling.dto.PlanChargeDto;
import com.onebill.productbilling.dto.PlanChargeRespDto;
import com.onebill.productbilling.dto.PlanDetailDto;
import com.onebill.productbilling.dto.PlanDetailRespDto;
import com.onebill.productbilling.dto.PlanDto;
import com.onebill.productbilling.dto.PlanOverdueDto;
import com.onebill.productbilling.dto.PlanOverdueRespDto;
import com.onebill.productbilling.dto.PlanRespDto;
import com.onebill.productbilling.exception.BillingException;
import com.onebill.productbilling.service.PlanService;
import com.onebill.productbilling.service.PlanServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestPlanService {

	@Rule
	public ExpectedException exp = ExpectedException.none();

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

	private PlanChargeDto charge;

	private PlanChargeRespDto cresp;

	private PlanOverdueDto due;

	private PlanOverdueRespDto rdue;

	@Before
	public void set() {
		pro = new Product();
		pro.setProductId(1);
		dto = new PlanDto();
		dto.setPlanId(1);
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
		charge = new PlanChargeDto();
		cresp = new PlanChargeRespDto();
		charge.setCharge(200);
		charge.setChargeType("Rent");
		charge.setPlan(plan);
		BeanUtils.copyProperties(charge, cresp);
		due = new PlanOverdueDto();
		rdue = new PlanOverdueRespDto();
		due.setOverageService(1);
		due.setOverageType("Data");
		due.setPlan(plan);
		due.setServiceCost(40);
		due.setUnit("GB");
		BeanUtils.copyProperties(due, rdue);
	}

	/**
	 * Test Cases : Add Plan
	 */

	@Test
	public void testAddPlanAmountFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		dto.setPlanAmount(0.0);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Input should not be null");
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(null);
	}

	@Test
	public void testAddPlanDurationFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Duration should not be null");
		dto.setPlanFrequency(0);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Type should not be null");
		dto.setType(null);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanProductFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed to add plan without product");
		dto.setProduct(null);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanProductAmountFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		dto.setProduct(null);
		dto.setPlanAmount(0.0);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanAmountDurationFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		dto.setPlanFrequency(0);
		dto.setPlanAmount(0.0);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanAmountTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		dto.setType(null);
		dto.setPlanAmount(0.0);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanProductTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Type should not be null");
		dto.setType(null);
		dto.setProduct(null);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanProductDurationFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Duration should not be null");
		dto.setPlanFrequency(0);
		dto.setProduct(null);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanTypeDurationFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Duration should not be null");
		dto.setPlanFrequency(0);
		dto.setType(null);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanOnlyType() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		PlanDto dto = new PlanDto();
		dto.setType("Data");
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanOnlyAmount() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Duration should not be null");
		PlanDto dto = new PlanDto();
		dto.setPlanAmount(100);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanOnlyDuration() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		PlanDto dto = new PlanDto();
		dto.setPlanFrequency(20);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanOnlyProduct() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		PlanDto dto = new PlanDto();
		dto.setProduct(pro);
		when(dao.addPlan(dto)).thenReturn(p);
		service.addPlan(dto);
	}

	@Test
	public void testAddPlanSuccess() {
		when(dao.addPlan(dto)).thenReturn(p);
		Assert.assertEquals(p, service.addPlan(dto));
	}

	/**
	 * Test Cases : Get Plan
	 */

	@Test
	public void testGetPlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage(
				"Plan type might be already present for this product with same amount and same duration. And also Please check with your Product is present");
		when(dao.getPlan(1)).thenReturn(null);
		service.addPlan(dto);
	}

	@Test
	public void testGetPlanSuccess() {
		when(dao.getPlan(1)).thenReturn(Arrays.asList(p));
		Assert.assertEquals(1, service.getPlan(1).size());
	}

	@Test
	public void testGetAllPlansFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No plan Available");
		when(dao.getAllPlans()).thenReturn(null);
		service.getAllPlans();
	}

	@Test
	public void testGetAllPlansSuccess() {
		when(dao.getAllPlans()).thenReturn(Arrays.asList(p));
		Assert.assertEquals(1, service.getAllPlans().size());
	}

	/**
	 * Test Cases : Update Plan
	 */

	@Test
	public void testUpdatePlanAmountFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		dto.setPlanAmount(0.0);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}
	@Test
	public void testUpdatePlanNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Inputs should not be null");
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(null);
	}

	@Test
	public void testUpdatePlanDurationFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Duration should not be null");
		dto.setPlanFrequency(0);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Type should not be null");
		dto.setType(null);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanProductFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed to update plan without product");
		dto.setProduct(null);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanProductAmountFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		dto.setProduct(null);
		dto.setPlanAmount(0.0);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanAmountDurationFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		dto.setPlanFrequency(0);
		dto.setPlanAmount(0.0);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanAmountTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		dto.setType(null);
		dto.setPlanAmount(0.0);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanProductTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Type should not be null");
		dto.setType(null);
		dto.setProduct(null);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanProductDurationFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Duration should not be null");
		dto.setPlanFrequency(0);
		dto.setProduct(null);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanTypeDurationFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Duration should not be null");
		dto.setPlanFrequency(0);
		dto.setType(null);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanOnlyType() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan ID should not be null");
		PlanDto dto = new PlanDto();
		dto.setType("Data");
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanOnlyAmount() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan ID should not be null");
		PlanDto dto = new PlanDto();
		dto.setPlanAmount(100);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanOnlyDuration() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan ID should not be null");
		PlanDto dto = new PlanDto();
		dto.setPlanFrequency(20);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
	}

	@Test
	public void testUpdatePlanOnlyProduct() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan ID should not be null");
		PlanDto dto = new PlanDto();
		dto.setProduct(pro);
		when(dao.updatePlan(dto)).thenReturn(p);
		Assert.assertEquals("Task Failed. Plan amount should not be null", service.updatePlan(dto));
	}

	@Test
	public void testUpdatePlanOnlyId() {
		exp.expect(BillingException.class);
		exp.expectMessage("Task Failed. Plan Amount should not be null");
		PlanDto dto = new PlanDto();
		dto.setPlanId(1);
		when(dao.updatePlan(dto)).thenReturn(p);
		service.updatePlan(dto);
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

	@Test
	public void testRemovePlanFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to remove plan of id 1");
		when(dao.removePlan(1)).thenReturn(null);
		Assert.assertNull(service.removePlan(1));
	}

	@Test
	public void testRemovePlanSuccess() {
		when(dao.removePlan(1)).thenReturn(p);
		Assert.assertNotNull(service.removePlan(1));
	}

	/**
	 * Test Cases : Get Plan
	 */

	@Test
	public void getPlanSuccess() {
		when(dao.getPlan(1)).thenReturn(Arrays.asList(p));
		assertEquals(1, service.getPlan(1).size());
	}

	@Test
	public void getPlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No plan available for this product");
		when(dao.getPlan(1)).thenReturn(new ArrayList<>());
		assertEquals("No plan available for this product", service.getPlan(1));
	}

	@Test
	public void getAllPlanSuccess() {
		when(dao.getAllPlans()).thenReturn(Arrays.asList(p));
		assertEquals(1, service.getAllPlans().size());
	}

	@Test
	public void getAllPlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No plan Available");
		when(dao.getAllPlans()).thenReturn(new ArrayList<>());
		service.getAllPlans();
	}

	/**
	 * Test Cases : Add Plan Detail
	 */
	@Test
	public void testAddPlanDetailSuccess() {
		when(dao.addPlanDetail(det)).thenReturn(res);
		Assert.assertEquals(res, service.addPlanDetail(det));
	}
	
	@Test
	public void testAddPlanDetailNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Inputs should not be null");
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(null);
	}

	@Test
	public void testAddPlanDetailDetailFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null detail value");
		det.setDetail(0);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailServiceFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null service type");
		det.setServiceType(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailPlanFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail without Plan");
		det.setPlan(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailUnitFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null Unit");
		det.setUnit(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailServicePlanFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null service type");
		det.setPlan(null);
		det.setServiceType(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailServiceUnitFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null service type");
		det.setUnit(null);
		det.setServiceType(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailServiceDetailFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null service type");
		det.setDetail(10);
		det.setServiceType(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailPlanUnitFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null Unit");
		det.setUnit(null);
		det.setPlan(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailPlanDetailFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null detail value");
		det.setDetail(0);
		det.setPlan(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailUnitDetailFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null detail value");
		det.setDetail(0);
		det.setUnit(null);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailOnlyType() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null detail value");
		PlanDetailDto det = new PlanDetailDto();
		det.setServiceType("Data");
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailOnlyDetail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null service type");
		PlanDetailDto det = new PlanDetailDto();
		det.setDetail(30);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailOnlyUnit() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null detail value");
		PlanDetailDto det = new PlanDetailDto();
		det.setUnit("GB");
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	@Test
	public void testAddPlanDetailOnlyPlan() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan detail with null detail value");
		PlanDetailDto det = new PlanDetailDto();
		det.setPlan(plan);
		when(dao.addPlanDetail(det)).thenReturn(res);
		service.addPlanDetail(det);
	}

	/**
	 * Test Cases : Get Plan Detail
	 */

	@Test
	public void getPlanDetailSuccess() {
		when(dao.getPlanDetail(1)).thenReturn(Arrays.asList(res));
		Assert.assertEquals(1, service.getPlanDetail(1).size());
	}

	@Test
	public void getPlanDetailFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No details available for this plan");
		when(dao.getPlanDetail(1)).thenReturn(Arrays.asList(res));
		service.getPlanDetail(2);
	}

	/**
	 * Test Cases : Update Plan Detail
	 */

	@Test
	public void testUpdatePlanDetailDetailFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null detail value");
		det.setDetail(0);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailServiceFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null service type");
		det.setServiceType(null);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailPlanFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail without Plan");
		det.setPlan(null);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailUnitFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null Unit");
		det.setUnit(null);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailServicePlanFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null service type");
		det.setPlan(null);
		det.setServiceType(null);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailServiceUnitFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null service type");
		det.setUnit(null);
		det.setServiceType(null);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailServiceDetailFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null service type");
		det.setDetail(10);
		det.setServiceType(null);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailPlanUnitFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null Unit");
		det.setUnit(null);
		det.setPlan(null);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailPlanDetailFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null detail value");
		det.setDetail(0);
		det.setPlan(null);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailUnitDetailFail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null detail value");
		det.setDetail(0);
		det.setUnit(null);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailOnlyType() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null detail value");
		PlanDetailDto det = new PlanDetailDto();
		det.setServiceType("Data");
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailOnlyDetail() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null service type");
		PlanDetailDto det = new PlanDetailDto();
		det.setDetail(30);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailOnlyUnit() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null detail value");
		PlanDetailDto det = new PlanDetailDto();
		det.setUnit("GB");
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	@Test
	public void testUpdatePlanDetailOnlyPlan() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan detail with null detail value");
		PlanDetailDto det = new PlanDetailDto();
		det.setPlan(plan);
		when(dao.updatePlanDetail(det)).thenReturn(res);
		service.updatePlanDetail(det);
	}

	/**
	 * Test Cases : Remove Plan Detail
	 */

	@Test
	public void testRemovePlanDetailSuccess() {
		when(dao.removePlanDetail(det)).thenReturn(res);
		Assert.assertEquals(res, service.removePlanDetail(det));
	}
	@Test
	public void testRemovePlanDetailNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Inputs should not be null");
		when(dao.removePlanDetail(det)).thenReturn(res);
		service.removePlanDetail(null);
	}

	@Test
	public void testRemovePlanDetailPlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to remove plan detail without plan id");
		det.setPlan(null);
		when(dao.removePlanDetail(det)).thenReturn(res);
		service.removePlanDetail(det);
	}

	@Test
	public void testRemovePlanDetailDetailFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to remove plan detail for given null Type");
		det.setServiceType(null);
		when(dao.removePlanDetail(det)).thenReturn(res);
		service.removePlanDetail(det);
	}

	@Test
	public void testRemovePlanDetailBothFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to remove plan detail for given null Type");
		det.setServiceType(null);
		det.setPlan(null);
		when(dao.removePlanDetail(det)).thenReturn(res);
		service.removePlanDetail(det);
	}

	/**
	 * Test Cases : Add Plan Charge
	 */

	@Test
	public void addPlanChargeSuccess() {
		when(dao.addPlanCharge(charge)).thenReturn(cresp);
		Assert.assertEquals(cresp, service.addPlanCharge(charge));
	}
	
	@Test
	public void testAddPlanChargeNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Inputs should not be null");
		when(dao.addPlanCharge(charge)).thenReturn(cresp);
		service.addPlanCharge(null);
	}

	@Test
	public void addPlanChargeTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan charge without charge type");
		charge.setChargeType(null);
		when(dao.addPlanCharge(charge)).thenReturn(cresp);
		service.addPlanCharge(charge);
	}

	@Test
	public void addPlanChargeChargeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan charge without charge");
		charge.setCharge(0.0);
		when(dao.addPlanCharge(charge)).thenReturn(cresp);
		service.addPlanCharge(charge);
	}

	@Test
	public void addPlanChargePlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan charge without plan id");
		charge.setPlan(null);
		when(dao.addPlanCharge(charge)).thenReturn(cresp);
		service.addPlanCharge(charge);
	}

	@Test
	public void addPlanChargeChargePlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan charge without charge");
		charge.setPlan(null);
		charge.setCharge(0.0);
		when(dao.addPlanCharge(charge)).thenReturn(cresp);
		service.addPlanCharge(charge);
	}

	@Test
	public void addPlanChargeChargeTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan charge without charge type");
		charge.setChargeType(null);
		charge.setCharge(0.0);
		when(dao.addPlanCharge(charge)).thenReturn(cresp);
		service.addPlanCharge(charge);
	}

	@Test
	public void addPlanChargePlanTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add plan charge without charge type");
		charge.setChargeType(null);
		charge.setPlan(null);
		when(dao.addPlanCharge(charge)).thenReturn(cresp);
		service.addPlanCharge(charge);
	}

	/**
	 * Test Cases : Get Plan Charge
	 */

	@Test
	public void getPlanChargeSuccess() {
		when(dao.getPlanCharge(1)).thenReturn(Arrays.asList(cresp));
		Assert.assertEquals(1, service.getPlanCharge(1).size());
	}

	@Test
	public void getPlanChargeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No Extra charges available for this plan");
		when(dao.getPlanCharge(1)).thenReturn(Arrays.asList(cresp));
		service.getPlanCharge(2);
	}

	/**
	 * Test Cases : Update Plan Charge
	 */

	@Test
	public void updatePlanChargeSuccess() {
		when(dao.updatePlanCharge(charge)).thenReturn(cresp);
		Assert.assertEquals(cresp, service.updatePlanCharge(charge));
	}
	
	@Test
	public void testUpdatePlanChargeNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Inputs should not be null");
		when(dao.updatePlanCharge(charge)).thenReturn(cresp);
		service.updatePlanCharge(null);
	}

	@Test
	public void updatePlanChargeTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan charge without charge type");
		charge.setChargeType(null);
		when(dao.updatePlanCharge(charge)).thenReturn(cresp);
		service.updatePlanCharge(charge);
	}

	@Test
	public void updatePlanChargeChargeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan charge without charge");
		charge.setCharge(0.0);
		when(dao.updatePlanCharge(charge)).thenReturn(cresp);
		service.updatePlanCharge(charge);
	}

	@Test
	public void updatePlanChargePlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan charge without plan id");
		charge.setPlan(null);
		when(dao.updatePlanCharge(charge)).thenReturn(cresp);
		service.updatePlanCharge(charge);
	}

	@Test
	public void updatePlanChargeChargePlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan charge without charge");
		charge.setPlan(null);
		charge.setCharge(0.0);
		when(dao.updatePlanCharge(charge)).thenReturn(cresp);
		service.updatePlanCharge(charge);
	}

	@Test
	public void updatePlanChargeChargeTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan charge without charge type");
		charge.setChargeType(null);
		charge.setCharge(0.0);
		when(dao.updatePlanCharge(charge)).thenReturn(cresp);
		service.updatePlanCharge(charge);
	}

	@Test
	public void updatePlanChargePlanTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to Update plan charge without charge type");
		charge.setChargeType(null);
		charge.setPlan(null);
		when(dao.updatePlanCharge(charge)).thenReturn(cresp);
		service.updatePlanCharge(charge);
	}

	/**
	 * Test Cases : Remove Plan Charge
	 */

	@Test
	public void removePlanChargeSuccess() {
		when(dao.removePlanCharge(charge)).thenReturn(cresp);
		Assert.assertEquals(cresp, service.removePlanCharge(charge));
	}
	
	@Test
	public void testRemovePlanChargeNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Inputs should not be null");
		when(dao.removePlanCharge(charge)).thenReturn(cresp);
		service.removePlanCharge(null);
	}

	@Test
	public void removePlanChargeTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to remove charge details without charge type");
		charge.setChargeType(null);
		when(dao.removePlanCharge(charge)).thenReturn(cresp);
		service.removePlanCharge(charge);
	}

	@Test
	public void removePlanChargePlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to remove charge details without plan");
		charge.setPlan(null);
		when(dao.removePlanCharge(charge)).thenReturn(cresp);
		service.removePlanCharge(charge);
	}

	@Test
	public void removePlanChargePlanAndTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to remove charge details without charge type");
		charge.setPlan(null);
		charge.setChargeType(null);
		when(dao.removePlanCharge(charge)).thenReturn(cresp);
		service.removePlanCharge(charge);
	}

	/**
	 * Test Cases : Add Plan Overdue
	 */

	@Test
	public void addPlanOverdueSuccess() {
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		Assert.assertEquals(rdue, service.addOverdueDetails(due));
	}
	
	@Test
	public void addPlanOverdueNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Inputs should not be null");
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(null);
	}

	@Test
	public void addPlanOverdueTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage type");
		due.setOverageType(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueServiceFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage service");
		due.setOverageService(0);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueCostFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without service cost");
		due.setServiceCost(0.0);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueUnitFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without unit");
		due.setUnit(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverduePlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without plan");
		due.setPlan(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueTypeServiceFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage type");
		due.setOverageType(null);
		due.setOverageService(0);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueTypeCostFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage type");
		due.setOverageType(null);
		due.setServiceCost(0.0);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueTypeUnitFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage type");
		due.setOverageType(null);
		due.setUnit(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueTypePlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage type");
		due.setOverageType(null);
		due.setPlan(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueServiceCostFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage service");
		due.setOverageService(0);
		due.setServiceCost(0.0);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueServiceUnitFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage service");
		due.setOverageService(0);
		due.setUnit(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueServicePlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage service");
		due.setOverageService(0);
		due.setPlan(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueUnitPlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without unit");
		due.setUnit(null);
		due.setPlan(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueCostPlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without service cost");
		due.setServiceCost(0.0);
		due.setPlan(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addPlanOverdueUnitCostFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without service cost");
		due.setUnit(null);
		due.setServiceCost(0.0);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addOverdueTypeServiceCostFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage type");
		due.setOverageType(null);
		due.setOverageService(0);
		due.setServiceCost(0.0);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addOverdueTypeServiceUnitFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage type");
		due.setOverageType(null);
		due.setOverageService(0);
		due.setUnit(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addOverdueTypeServicePlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage type");
		due.setOverageType(null);
		due.setOverageService(0);
		due.setPlan(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addOverdueTypeUnitCostFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage type");
		due.setOverageType(null);
		due.setUnit(null);
		due.setServiceCost(0.0);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addOverdueTypePlanCostFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage type");
		due.setOverageType(null);
		due.setPlan(null);
		due.setServiceCost(0.0);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addOverdueTypeUnitPlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage type");
		due.setOverageType(null);
		due.setUnit(null);
		due.setPlan(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addOverdueServiceUnitPlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage service");
		due.setOverageService(0);
		due.setUnit(null);
		due.setPlan(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addOverdueServiceUnitCostFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage service");
		due.setOverageService(0);
		due.setUnit(null);
		due.setServiceCost(0.0);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addOverdueServiceCostPlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without overage service");
		due.setOverageService(0);
		due.setServiceCost(0.0);
		due.setPlan(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	@Test
	public void addOverdueUnitCostPlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to add overdue details without service cost");
		due.setUnit(null);
		due.setServiceCost(0.0);
		due.setPlan(null);
		when(dao.addOverdueDetails(due)).thenReturn(rdue);
		service.addOverdueDetails(due);
	}

	/**
	 * Test Cases : Get Plan Overdue
	 */

	@Test
	public void getOverdueSuccess() {
		when(dao.getOverdueDetails(1)).thenReturn(Arrays.asList(rdue));
		Assert.assertEquals(1, service.getOverdueDetails(1).size());
	}

	@Test
	public void getOverdueFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("No overdue details available for this plan 2");
		when(dao.getOverdueDetails(1)).thenReturn(Arrays.asList(rdue));
		service.getOverdueDetails(2);
	}

	/**
	 * Test Cases : Update Plan Overdue
	 */

	@Test
	public void updatePlanOverdueSuccess() {
		when(dao.updateOverdueDetails(due)).thenReturn(rdue);
		Assert.assertEquals(rdue, service.updateOverdueDetails(due));
	}
	
	@Test
	public void updatePlanOverdueNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Inputs should not be null");
		when(dao.updateOverdueDetails(due)).thenReturn(rdue);
		service.updateOverdueDetails(null);
	}

	@Test
	public void updatePlanOverdueTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to update overdue details without overage type");
		due.setOverageType(null);
		when(dao.updateOverdueDetails(due)).thenReturn(rdue);
		service.updateOverdueDetails(due);
	}

	@Test
	public void updatePlanOverdueServiceFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to update overdue details without overage service");
		due.setOverageService(0);
		when(dao.updateOverdueDetails(due)).thenReturn(rdue);
		service.updateOverdueDetails(due);
	}

	@Test
	public void updatePlanOverdueCostFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to update overdue details without service cost");
		due.setServiceCost(0.0);
		when(dao.updateOverdueDetails(due)).thenReturn(rdue);
		service.updateOverdueDetails(due);
	}

	@Test
	public void updatePlanOverdueUnitFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to update overdue details without unit");
		due.setUnit(null);
		when(dao.updateOverdueDetails(due)).thenReturn(rdue);
		service.updateOverdueDetails(due);
	}

	@Test
	public void updatePlanOverduePlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to update overdue details without plan");
		due.setPlan(null);
		when(dao.updateOverdueDetails(due)).thenReturn(rdue);
		service.updateOverdueDetails(due);
	}

	/**
	 * Test Cases : Remove Plan Overdue
	 */

	@Test
	public void removePlanOverdueSuccess() {
		when(dao.removeOverdueDetails(due)).thenReturn(rdue);
		Assert.assertEquals(rdue, service.removeOverdueDetails(due));
	}
	
	@Test
	public void removePlanOverdueNullFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Inputs should not be null");
		when(dao.removeOverdueDetails(due)).thenReturn(rdue);
		service.removeOverdueDetails(null);
	}

	@Test
	public void removePlanOverdueTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to remove Details without overage type");
		due.setOverageType(null);
		when(dao.removeOverdueDetails(due)).thenReturn(rdue);
		service.removeOverdueDetails(due);
	}

	@Test
	public void removePlanOverduePlanFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to remove Details without plan");
		due.setPlan(null);
		when(dao.removeOverdueDetails(due)).thenReturn(rdue);
		service.removeOverdueDetails(due);
	}

	@Test
	public void removePlanOverduePlanTypeFailure() {
		exp.expect(BillingException.class);
		exp.expectMessage("Failed to remove Details without overage type");
		due.setPlan(null);
		due.setOverageType(null);
		when(dao.removeOverdueDetails(due)).thenReturn(rdue);
		service.removeOverdueDetails(due);
	}
}
