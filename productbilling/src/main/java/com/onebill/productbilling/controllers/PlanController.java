package com.onebill.productbilling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onebill.productbilling.dto.PlanChargeDto;
import com.onebill.productbilling.dto.PlanDetailDto;
import com.onebill.productbilling.dto.PlanDto;
import com.onebill.productbilling.dto.PlanOverdueDto;
import com.onebill.productbilling.dto.ResponseDto;
import com.onebill.productbilling.service.PlanService;

@RestController
@RequestMapping("/plans")
@CrossOrigin(origins = "*")
public class PlanController {

	@Autowired
	private PlanService service;

	@PostMapping
	public ResponseDto addPlan(@RequestBody PlanDto plan) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.addPlan(plan));
		return dto;
	}

	@GetMapping("/{productId}")
	public ResponseDto getPlan(@PathVariable int productId) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getPlan(productId));
		return dto;
	}

	@GetMapping
	public ResponseDto getAllPlans() {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getAllPlans());
		return dto;
	}

	@PutMapping
	public ResponseDto updatePlan(@RequestBody PlanDto plan) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.updatePlan(plan));
		return dto;
	}

	@DeleteMapping("/{planId}")
	public ResponseDto removePlan(@PathVariable int planId) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.removePlan(planId));
		return dto;
	}

	@PostMapping("/detail")
	public ResponseDto addPlanDetail(@RequestBody PlanDetailDto detail) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.addPlanDetail(detail));
		return dto;
	}

	@GetMapping("/detail/{planId}")
	public ResponseDto getPlanDetail(@PathVariable int planId) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getPlanDetail(planId));
		return dto;
	}

	@PutMapping("/detail")
	public ResponseDto updatePlanDetail(@RequestBody PlanDetailDto plan) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.updatePlanDetail(plan));
		return dto;
	}

	@DeleteMapping("/detail")
	public ResponseDto removePlanDetail(@RequestBody PlanDetailDto plan) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.removePlanDetail(plan));
		return dto;
	}

	@PostMapping("/charge")
	public ResponseDto addPlanCharge(@RequestBody PlanChargeDto plan) {
		System.out.println(plan);
		ResponseDto dto = new ResponseDto();
		dto.setData(service.addPlanCharge(plan));
		return dto;
	}

	@GetMapping("/charge/{planId}")
	public ResponseDto getPlanCharge(@PathVariable int planId) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getPlanCharge(planId));
		return dto;
	}

	@PutMapping("/charge")
	public ResponseDto updatePlanCharge(@RequestBody PlanChargeDto plan) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.updatePlanCharge(plan));
		return dto;
	}

	@DeleteMapping("/charge")
	public ResponseDto deletePlanCharge(@RequestBody PlanChargeDto plan) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.removePlanCharge(plan));
		return dto;
	}

	@PostMapping("/overdue")
	public ResponseDto addOverdueDetail(@RequestBody PlanOverdueDto plan) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.addOverdueDetails(plan));
		return dto;
	}

	@GetMapping("/overdue/{planId}")
	public ResponseDto getOverdueDetail(@PathVariable int planId) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.getOverdueDetails(planId));
		return dto;
	}

	@PutMapping("/overdue")
	public ResponseDto updateOverdueDetails(@RequestBody PlanOverdueDto plan) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.updateOverdueDetails(plan));
		return dto;
	}

	@DeleteMapping("/overdue")
	public ResponseDto removeOverdueDetails(@RequestBody PlanOverdueDto plan) {
		ResponseDto dto = new ResponseDto();
		dto.setData(service.removeOverdueDetails(plan));
		return dto;
	}

}
