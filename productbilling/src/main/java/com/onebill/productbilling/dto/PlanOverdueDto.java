package com.onebill.productbilling.dto;

import java.io.Serializable;

import com.onebill.productbilling.Entities.Plan;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PlanOverdueDto implements Serializable{

	private String overageType;

	private int overageService;

	private double serviceCost;
	
	private String unit;

	private Plan plan;
}
