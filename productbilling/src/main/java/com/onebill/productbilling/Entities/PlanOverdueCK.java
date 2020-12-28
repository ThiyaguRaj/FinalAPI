package com.onebill.productbilling.Entities;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PlanOverdueCK implements Serializable {

	private int plan;
	private String overageType;
}
