package com.onebill.productbilling.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.onebill.productbilling.Entities.Plan;
import com.onebill.productbilling.Entities.PlanCharge;
import com.onebill.productbilling.Entities.PlanDetail;
import com.onebill.productbilling.Entities.PlanOverdue;
import com.onebill.productbilling.Entities.Product;
import com.onebill.productbilling.dto.PlanChargeDto;
import com.onebill.productbilling.dto.PlanChargeRespDto;
import com.onebill.productbilling.dto.PlanDetailDto;
import com.onebill.productbilling.dto.PlanDetailRespDto;
import com.onebill.productbilling.dto.PlanDto;
import com.onebill.productbilling.dto.PlanOverdueDto;
import com.onebill.productbilling.dto.PlanOverdueRespDto;
import com.onebill.productbilling.dto.PlanRespDto;

@Repository
public class PlanDaoImpl implements PlanDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	@Transactional
	public PlanRespDto addPlan(PlanDto plan) {
		Plan p = new Plan();
		BeanUtils.copyProperties(plan, p);
		if(p.getPlanAmount()==0.0 || p.getPlanFrequency()==0) {
			return null;
		}else {
			manager.persist(p);
			PlanRespDto dto = new PlanRespDto();
			BeanUtils.copyProperties(p, dto);
			return dto;
		}
	}

	@Override
	public List<PlanRespDto> getPlan(int productId) {
		Product pro = new Product();
		pro.setProductId(productId);
		TypedQuery<Plan> query = manager.createQuery("from Plan P where P.product=:value", Plan.class);
		query.setParameter("value", pro);
		List<Plan> list = query.getResultList();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<PlanRespDto>>() {
		}.getType();
		List<PlanRespDto> dto = mapper.map(list, listType);
		return dto;
	}

	@Override
	public List<PlanRespDto> getAllPlans() {
		List<Plan> list = manager.createQuery("FROM Plan", Plan.class).getResultList();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<PlanRespDto>>() {
		}.getType();
		return mapper.map(list, listType);
	}

	@Override
	@Transactional
	public PlanRespDto updatePlan(PlanDto plan) {
		plan.setCharge(new ArrayList<PlanCharge>());
		plan.setDetail(new ArrayList<PlanDetail>());
		plan.setDue(new ArrayList<PlanOverdue>());
		Plan plan1 = manager.find(Plan.class, plan.getPlanId());
		plan.getCharge().clear();
		plan.getCharge().addAll(plan1.getCharge());
		plan.getDetail().clear();
		plan.getDetail().addAll(plan1.getDetail());
		plan.getDue().clear();
		plan.getDue().addAll(plan1.getDue());
		Plan p = new Plan();
		BeanUtils.copyProperties(plan, p);
		manager.merge(p);
		PlanRespDto dto = new PlanRespDto();
		BeanUtils.copyProperties(p, dto);
		return dto;
	}

	@Override
	@Transactional
	public PlanRespDto removePlan(int planId) {
		Plan plan1 = manager.find(Plan.class, planId);
		if (plan1 != null) {
			Product pro = manager.find(Product.class, plan1.getProduct().getProductId());
			pro.getPlan().remove(plan1);
			PlanRespDto plan = new PlanRespDto();
			BeanUtils.copyProperties(plan1, plan);
			return plan;
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public PlanDetailRespDto addPlanDetail(PlanDetailDto detail) {
			PlanDetail det = new PlanDetail();
			BeanUtils.copyProperties(detail, det);
			manager.merge(det);
			PlanDetailRespDto dto = new PlanDetailRespDto();
			BeanUtils.copyProperties(detail, dto);
			return dto;		
	}

	@Override
	public List<PlanDetailRespDto> getPlanDetail(int planId) {
		Plan p = new Plan();
		p.setPlanId(planId);
		TypedQuery<PlanDetail> query = manager.createQuery("from PlanDetail P where P.plan=:value", PlanDetail.class);
		query.setParameter("value", p);
		List<PlanDetail> list = query.getResultList();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<PlanDetailRespDto>>() {
		}.getType();
		return mapper.map(list, listType);
	}

	@Override
	@Transactional
	public PlanDetailRespDto updatePlanDetail(PlanDetailDto plan) {
		PlanDetail detail = new PlanDetail();
		BeanUtils.copyProperties(plan, detail);
		manager.merge(detail);
		PlanDetailRespDto dto = new PlanDetailRespDto();
		BeanUtils.copyProperties(detail, dto);
		return dto;
	}

	@Override
	@Transactional
	public PlanDetailRespDto removePlanDetail(PlanDetailDto plan) {
		TypedQuery<PlanDetail> query = manager
				.createQuery("from PlanDetail P where P.plan=:value and P.serviceType=:type", PlanDetail.class);
		query.setParameter("value", plan.getPlan());
		query.setParameter("type", plan.getServiceType());
		PlanDetail detail = query.getSingleResult();
		manager.remove(detail);
		Plan pl = manager.find(Plan.class, plan.getPlan().getPlanId());
		pl.getDetail().remove(detail);
		PlanDetailRespDto dto = new PlanDetailRespDto();
		BeanUtils.copyProperties(detail, dto);
		return dto;
	}

	@Override
	@Transactional
	public PlanChargeRespDto addPlanCharge(PlanChargeDto plan) {
		PlanCharge charge = new PlanCharge();
		BeanUtils.copyProperties(plan, charge);
		manager.merge(charge);
		PlanChargeRespDto dto = new PlanChargeRespDto();
		BeanUtils.copyProperties(plan, dto);
		return dto;
	}

	@Override
	public List<PlanChargeRespDto> getPlanCharge(int planId) {
		Plan p = new Plan();
		p.setPlanId(planId);
		TypedQuery<PlanCharge> query = manager.createQuery("from PlanCharge P where P.plan=:value", PlanCharge.class);
		query.setParameter("value", p);
		List<PlanCharge> list = query.getResultList();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<PlanChargeRespDto>>() {
		}.getType();
		List<PlanChargeRespDto> dto = mapper.map(list, listType);
		return dto;
	}

	@Override
	@Transactional
	public PlanChargeRespDto updatePlanCharge(PlanChargeDto plan) {

		PlanCharge charge = new PlanCharge();
		BeanUtils.copyProperties(plan, charge);
		manager.merge(charge);
		PlanChargeRespDto dto = new PlanChargeRespDto();
		BeanUtils.copyProperties(charge, dto);
		return dto;
	}

	@Override
	@Transactional
	public PlanChargeRespDto removePlanCharge(PlanChargeDto plan) {
		TypedQuery<PlanCharge> query = manager
				.createQuery("from PlanCharge P where P.plan=:value and P.chargeType=:type", PlanCharge.class);
		query.setParameter("value", plan.getPlan());
		query.setParameter("type", plan.getChargeType());
		PlanCharge charge = query.getSingleResult();
		manager.remove(charge);
		Plan pl = manager.find(Plan.class, plan.getPlan().getPlanId());
		pl.getCharge().remove(charge);
		PlanChargeRespDto dto = new PlanChargeRespDto();
		BeanUtils.copyProperties(charge, dto);
		return dto;
	}

	@Override
	@Transactional
	public PlanOverdueRespDto addOverdueDetails(PlanOverdueDto plan) {
		PlanOverdue charge = new PlanOverdue();
		BeanUtils.copyProperties(plan, charge);
		manager.merge(charge);
		PlanOverdueRespDto dto = new PlanOverdueRespDto();
		BeanUtils.copyProperties(plan, dto);
		return dto;
	}

	@Override
	public List<PlanOverdueRespDto> getOverdueDetails(int planId) {
		Plan p = new Plan();
		p.setPlanId(planId);
		TypedQuery<PlanOverdue> query = manager.createQuery("from PlanOverdue P where P.plan=:value",
				PlanOverdue.class);
		query.setParameter("value", p);
		List<PlanOverdue> list = query.getResultList();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<PlanOverdueRespDto>>() {
		}.getType();
		List<PlanOverdueRespDto> dto = mapper.map(list, listType);
		return dto;

	}

	@Override
	@Transactional
	public PlanOverdueRespDto updateOverdueDetails(PlanOverdueDto plan) {
		PlanOverdue due = new PlanOverdue();
		BeanUtils.copyProperties(plan, due);
		manager.merge(due);
		PlanOverdueRespDto dto = new PlanOverdueRespDto();
		BeanUtils.copyProperties(plan, dto);
		return dto;
	}

	@Override
	@Transactional
	public PlanOverdueRespDto removeOverdueDetails(PlanOverdueDto plan) {
		TypedQuery<PlanOverdue> query = manager
				.createQuery("from PlanOverdue P where P.plan=:value and P.overageType=:type", PlanOverdue.class);
		query.setParameter("value", plan.getPlan());
		query.setParameter("type", plan.getOverageType());
		PlanOverdue due = query.getSingleResult();
		manager.remove(due);
		Plan pl = manager.find(Plan.class, plan.getPlan().getPlanId());
		pl.getDue().remove(due);
		PlanOverdueRespDto dto = new PlanOverdueRespDto();
		BeanUtils.copyProperties(due, dto);
		return dto;
	}
}