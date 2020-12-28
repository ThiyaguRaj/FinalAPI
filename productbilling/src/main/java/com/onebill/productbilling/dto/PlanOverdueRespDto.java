package com.onebill.productbilling.dto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PlanOverdueRespDto implements Serializable {
	private String overageType;

	private int overageService;

	private double serviceCost;

	private String unit;
}
