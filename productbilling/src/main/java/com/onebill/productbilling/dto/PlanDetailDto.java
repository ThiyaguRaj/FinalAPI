package com.onebill.productbilling.dto;

import java.io.Serializable;

import com.onebill.productbilling.Entities.Plan;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PlanDetailDto  implements Serializable{

	private String serviceType;

	private int detail;
	
	private String unit;

	private Plan plan;
}
