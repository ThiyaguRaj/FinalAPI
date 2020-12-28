package com.onebill.productbilling.dto;

import java.io.Serializable;

import com.onebill.productbilling.Entities.Plan;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PlanChargeDto implements Serializable{

	private String chargeType;

	private double charge;

	private String document;

	private Plan plan;
}
