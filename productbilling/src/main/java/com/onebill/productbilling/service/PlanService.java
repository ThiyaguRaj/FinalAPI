package com.onebill.productbilling.service;

import java.util.List;

import com.onebill.productbilling.dto.PlanChargeDto;
import com.onebill.productbilling.dto.PlanChargeRespDto;
import com.onebill.productbilling.dto.PlanDetailDto;
import com.onebill.productbilling.dto.PlanDetailRespDto;
import com.onebill.productbilling.dto.PlanDto;
import com.onebill.productbilling.dto.PlanOverdueDto;
import com.onebill.productbilling.dto.PlanOverdueRespDto;
import com.onebill.productbilling.dto.PlanRespDto;

public interface PlanService {

	public PlanRespDto addPlan(PlanDto plan);
	
	public List<PlanRespDto> getPlan(int productId);

	public List<PlanRespDto> getAllPlans();

	public PlanRespDto updatePlan(PlanDto plan);

	public PlanRespDto removePlan(int planId);

	public PlanDetailRespDto addPlanDetail(PlanDetailDto detail);

	public List<PlanDetailRespDto> getPlanDetail(int planId);

	public PlanDetailRespDto updatePlanDetail(PlanDetailDto plan);

	public PlanDetailRespDto removePlanDetail(PlanDetailDto plan);

	public PlanChargeRespDto addPlanCharge(PlanChargeDto plan);

	public List<PlanChargeRespDto> getPlanCharge(int planId);

	public PlanChargeRespDto updatePlanCharge(PlanChargeDto plan);

	public PlanChargeRespDto removePlanCharge(PlanChargeDto plan);

	public PlanOverdueRespDto addOverdueDetails(PlanOverdueDto plan);

	public List<PlanOverdueRespDto> getOverdueDetails(int planId);

	public PlanOverdueRespDto updateOverdueDetails(PlanOverdueDto plan);

	public PlanOverdueRespDto removeOverdueDetails(PlanOverdueDto plan);
}
