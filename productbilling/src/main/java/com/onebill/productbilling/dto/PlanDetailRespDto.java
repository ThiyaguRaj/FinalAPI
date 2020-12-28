package com.onebill.productbilling.dto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PlanDetailRespDto implements Serializable{

	private String serviceType;

	private int detail;
	
	private String unit;
	
}
