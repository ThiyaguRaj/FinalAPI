package com.onebill.productbilling.dto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PlanChargeRespDto implements Serializable{

	private String chargeType;

	private double charge;

	private String document;
}
