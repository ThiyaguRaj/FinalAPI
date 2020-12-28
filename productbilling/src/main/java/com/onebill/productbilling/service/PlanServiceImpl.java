package com.onebill.productbilling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanDao dao;

	@Override
	public PlanRespDto addPlan(PlanDto plan) {
		PlanRespDto plan1 = null;
		int count = 0;
		try {
			if (plan.getPlanAmount() != 0.0 && plan.getPlanFrequency() != 0 && plan.getType() != null
					&& plan.getProduct() != null) {
				count++;
				plan1 = dao.addPlan(plan);
				if (plan1 != null) {
					return plan1;
				} else {
					throw new BillingException("Failed to add");
				}
			} else {
				throw new BillingException("Failed to add plan");
			}
		} catch (Exception e) {
			if (plan.getPlanAmount() == 0.0) {
				throw new BillingException("Task Failed. Plan Amount should not be null");
			} else if (plan.getPlanFrequency() == 0) {
				throw new BillingException("Task Failed. Plan Duration should not be null");
			} else if (plan.getType() == null) {
				throw new BillingException("Task Failed. Plan Type should not be null");
			} else {
				if (count == 0) {
					throw new BillingException("Task Failed to add plan without product");
				} else {
					throw new BillingException(
							"Plan type might be already present for this product with same amount and same duration. And also Please check with your Product is present");
				}
			}
		}
	}

	@Override
	public List<PlanRespDto> getPlan(int productId) {
		List<PlanRespDto> plan1 = null;
		try {
			plan1 = dao.getPlan(productId);
			if (!plan1.isEmpty()) {
				return plan1;
			} else {
				throw new BillingException("No plan available for this product");
			}
		} catch (Exception e) {
			throw new BillingException("No plan available for this product");
		}
	}

	@Override
	public List<PlanRespDto> getAllPlans() {
		List<PlanRespDto> plan = null;
		try {
			plan = dao.getAllPlans();
			if (!plan.isEmpty()) {
				return plan;
			} else {
				throw new BillingException("No plan Available");
			}
		} catch (Exception e) {
			throw new BillingException("No plan Available");
		}
	}

	@Override
	public PlanRespDto updatePlan(PlanDto plan) {
		PlanRespDto plan1 = null;
		int count = 0;
		try {
			if (plan.getPlanId() != 0 && plan.getPlanAmount() != 0.0 && plan.getPlanFrequency() != 0
					&& plan.getType() != null && plan.getProduct() != null) {
				count++;
				plan1 = dao.updatePlan(plan);
				if (plan1 != null) {
					return plan1;
				} else {
					throw new BillingException("Failed to update");
				}
			} else {
				throw new BillingException("Failed to update plan");
			}
		} catch (Exception e) {
			if (plan.getPlanId() == 0) {
				throw new BillingException("Task Failed. Plan ID should not be null");
			} else if (plan.getPlanAmount() == 0.0) {
				throw new BillingException("Task Failed. Plan Amount should not be null");
			} else if (plan.getPlanFrequency() == 0) {
				throw new BillingException("Task Failed. Plan Duration should not be null");
			} else if (plan.getType() == null) {
				throw new BillingException("Task Failed. Plan Type should not be null");
			} else {
				if (count == 0) {
					throw new BillingException("Task Failed to update plan without product");
				} else {
					throw new BillingException(
							"Plan type might be already present for this product with same amount and same duration. And also Please check with your Product is present");
				}
			}
		}
	}

	@Override
	public PlanRespDto removePlan(int planId) {
		PlanRespDto plan1 = null;
		try {
			plan1 = dao.removePlan(planId);
			if (plan1 != null) {
				return plan1;
			} else {
				throw new BillingException("Failed to remove plan of id " + planId);
			}
		} catch (Exception e) {
			throw new BillingException("Failed to remove plan of id " + planId);
		}
	}

	@Override
	public PlanDetailRespDto addPlanDetail(PlanDetailDto detail) {
		PlanDetailRespDto det = null;
		int count = 0;
		try {
			if (detail.getDetail() != 0 && detail.getServiceType() != null && detail.getUnit() != null
					&& detail.getPlan() != null) {
				count++;
				det = dao.addPlanDetail(detail);
				if (det != null) {
					return det;
				} else {
					throw new BillingException("Failed to add plan detail");
				}
			} else {
				throw new BillingException("Failed to add plan detail");
			}
		} catch (Exception e) {
			if (detail.getDetail() == 0) {
				throw new BillingException("Failed to add plan detail with null detail value");
			} else if (detail.getServiceType() == null) {
				throw new BillingException("Failed to add plan detail with null service type");
			} else if (detail.getUnit() == null) {
				throw new BillingException("Failed to add plan detail with null Unit");
			} else {
				if (count == 0) {
					throw new BillingException("Failed to add plan detail without Plan");
				} else {
					throw new BillingException("Failed to add plan detail. Check with service type and plan ID");
				}
			}
		}
	}

	@Override
	public List<PlanDetailRespDto> getPlanDetail(int planId) {
		List<PlanDetailRespDto> detail = null;
		try {
			detail = dao.getPlanDetail(planId);
			if (!detail.isEmpty()) {
				return detail;
			} else {
				throw new BillingException("No details available for this plan");
			}
		} catch (Exception e) {
			throw new BillingException("No details available for this plan");
		}
	}

	@Override
	public PlanDetailRespDto updatePlanDetail(PlanDetailDto detail) {
		PlanDetailRespDto det = null;
		int count = 0;
		try {
			if (detail.getDetail() != 0 && detail.getServiceType() != null && detail.getUnit() != null
					&& detail.getPlan() != null) {
				count++;
				det = dao.addPlanDetail(detail);
				if (det != null) {
					return det;
				} else {
					throw new BillingException("Failed to Update plan detail");
				}
			} else {
				throw new BillingException("Failed to Update plan detail");
			}
		} catch (Exception e) {
			if (detail.getDetail() == 0) {
				throw new BillingException("Failed to Update plan detail with null detail value");
			} else if (detail.getServiceType() == null) {
				throw new BillingException("Failed to Update plan detail with null service type");
			} else if (detail.getUnit() == null) {
				throw new BillingException("Failed to Update plan detail with null Unit");
			} else {
				if (count == 0) {
					throw new BillingException("Failed to Update plan detail without Plan");
				} else {
					throw new BillingException("Failed to Update plan detail. Check with service type and plan ID");
				}
			}
		}
	}

	@Override
	public PlanDetailRespDto removePlanDetail(PlanDetailDto plan) {
		PlanDetailRespDto detail = null;
		int count = 0;
		try {
			if (plan.getServiceType() != null && plan.getPlan() != null) {
				count++;
				detail = dao.removePlanDetail(plan);
				if (detail != null) {
					return detail;
				} else {
					throw new BillingException("Failed to remove plan detail");
				}
			} else {
				throw new BillingException("Failed to remove plan detail");
			}
		} catch (Exception e) {
			if (plan.getServiceType() == null) {
				throw new BillingException("Failed to remove plan detail for given null Type");
			} else {
				if (count == 0) {
					throw new BillingException("Failed to remove plan detail without plan id");
				} else {
					throw new BillingException("Task Failed. No Detail available for given Plan and Service type");
				}
			}
		}
	}

	@Override
	public PlanChargeRespDto addPlanCharge(PlanChargeDto plan) {
		PlanChargeRespDto charge = null;
		int count = 0;
		try {
			if (plan.getCharge() != 0.0 && plan.getChargeType() != null && plan.getPlan() != null) {
				count++;
				charge = dao.addPlanCharge(plan);
				if (charge != null) {
					return charge;
				} else {
					throw new BillingException("Failed to add plan charge details");
				}
			} else {
				throw new BillingException("Failed to add plan charge details");
			}
		} catch (Exception e) {
			if (plan.getChargeType() == null) {
				throw new BillingException("Failed to add plan charge without charge type");
			} else if (plan.getCharge() == 0.0) {
				throw new BillingException("Failed to add plan charge without charge");
			} else {
				if (count == 0) {
					throw new BillingException("Failed to add plan charge without plan id");
				} else {
					throw new BillingException("Failed to add plan charge. Check with charge type and plan ID");
				}
			}
		}
	}

	@Override
	public List<PlanChargeRespDto> getPlanCharge(int planId) {
		List<PlanChargeRespDto> detail = null;
		try {
			detail = dao.getPlanCharge(planId);
			if (!detail.isEmpty()) {
				return detail;
			} else {
				throw new BillingException("No charges available");
			}
		} catch (Exception e) {
			throw new BillingException("No Extra charges available for this plan");
		}
	}

	@Override
	public PlanChargeRespDto updatePlanCharge(PlanChargeDto plan) {
		PlanChargeRespDto charge = null;
		int count = 0;
		try {
			if (plan.getCharge() != 0.0 && plan.getChargeType() != null && plan.getPlan() != null) {
				count++;
				charge = dao.updatePlanCharge(plan);
				if (charge != null) {
					return charge;
				} else {
					throw new BillingException("Failed to Update plan charge details");
				}
			} else {
				throw new BillingException("Failed to Update plan charge details");
			}
		} catch (Exception e) {
			if (plan.getChargeType() == null) {
				throw new BillingException("Failed to Update plan charge without charge type");
			} else if (plan.getCharge() == 0.0) {
				throw new BillingException("Failed to Update plan charge without charge");
			} else {
				if (count == 0) {
					throw new BillingException("Failed to Update plan charge without plan id");
				} else {
					throw new BillingException("Failed to Update plan charge. Check with charge type and plan ID");
				}
			}
		}
	}

	@Override
	public PlanChargeRespDto removePlanCharge(PlanChargeDto plan) {
		PlanChargeRespDto detail = null;
		int count=0;
		try {
			if (plan.getChargeType() != null && plan.getPlan() != null) {
				count++;
				detail = dao.removePlanCharge(plan);
				if (detail != null) {
					return detail;
				} else {
					throw new BillingException("Failed to remove charge details");
				}
			}
			else {
				throw new BillingException("Failed to remove charge details");
			}
		} catch (Exception e) {
			if (plan.getChargeType() == null) {
				throw new BillingException("Failed to remove charge details without charge type");
			} else {
				if(count==0) {
					throw new BillingException("Failed to remove charge details without plan");
				}else {
					throw new BillingException("Task Failed. No Detail available for given Plan and Charge type");
				}
			}
		}
	}

	@Override
	public PlanOverdueRespDto addOverdueDetails(PlanOverdueDto plan) {
		PlanOverdueRespDto due = null;
		int count=0;
		try {
			if (plan.getOverageService() != 0 && plan.getServiceCost() != 0.0 && plan.getOverageType()!=null && plan.getUnit()!=null && plan.getPlan()!=null ) {
				count++;
				due = dao.addOverdueDetails(plan);
				if (due != null) {
					return due;
				} else {
					throw new BillingException("Failed to add overdue details");
				}
			} else {
				throw new BillingException("Failed to add overdue details");
			}
		} catch (Exception e) {
			if (plan.getOverageType() == null) {
				throw new BillingException("Failed to add overdue details without overage type");
			} else if(plan.getOverageService() == 0) {
				throw new BillingException("Failed to add overdue details without overage service");
			} else if(plan.getServiceCost() == 0.0) {
				throw new BillingException("Failed to add overdue details without service cost");
			} else if(plan.getUnit()==null) {
				throw new BillingException("Failed to add overdue details without unit");
			}else {
				if(count==0) {
					throw new BillingException("Failed to add overdue details without plan");
				}else {
					throw new BillingException("Failed to add plan overdue. Check with overage type and plan ID");
				}
			}
		}
	}

	@Override
	public List<PlanOverdueRespDto> getOverdueDetails(int planId) {
		List<PlanOverdueRespDto> due = null;
		try {
			due = dao.getOverdueDetails(planId);
			if (due.isEmpty()) {
				throw new BillingException("No overdue available for this plan");
			} else {
				return due;
			}
		} catch (Exception e) {
			throw new BillingException("No overdue details available for this plan " + planId);
		}
	}

	@Override
	public PlanOverdueRespDto updateOverdueDetails(PlanOverdueDto plan) {
		PlanOverdueRespDto due = null;
		int count=0;
		try {
			if (plan.getOverageService() != 0 && plan.getServiceCost() != 0.0 && plan.getOverageType()!=null && plan.getUnit()!=null && plan.getPlan()!=null ) {
				count++;
				due = dao.updateOverdueDetails(plan);
				if (due != null) {
					return due;
				} else {
					throw new BillingException("Failed to update overdue details");
				}
			} else {
				throw new BillingException("Failed to update overdue details");
			}
		} catch (Exception e) {
			if (plan.getOverageType() == null) {
				throw new BillingException("Failed to update overdue details without overage type");
			} else if(plan.getOverageService() == 0) {
				throw new BillingException("Failed to update overdue details without overage service");
			} else if(plan.getServiceCost() == 0.0) {
				throw new BillingException("Failed to update overdue details without service cost");
			} else if(plan.getUnit()==null) {
				throw new BillingException("Failed to update overdue details without unit");
			}else {
				if(count==0) {
					throw new BillingException("Failed to update overdue details without plan");
				}else {
					throw new BillingException("Failed to update plan overdue. Check with overage type and plan ID");
				}
			}
		}
	}

	@Override
	public PlanOverdueRespDto removeOverdueDetails(PlanOverdueDto plan) {
		PlanOverdueRespDto due = null;
		int count = 0;
		try {
			if(plan.getOverageType()!=null && plan.getPlan()!=null) {
				count++;
				due = dao.removeOverdueDetails(plan);
				if (due != null) {
					return due;
				} else {
					throw new BillingException("Failed to remove Details");
				}
			}else {
				throw new BillingException("Failed to remove Details");
			}
		} catch (Exception e) {
			if (plan.getOverageType() == null) {
				throw new BillingException("Failed to remove Details without overage type");
			} else{
				if(count==0) {
					throw new BillingException("Failed to remove Details without plan");
				}else {
					throw new BillingException("Task Failed. No Detail available for given Plan and Overdue type");
				}
			}
		}
	}

}
