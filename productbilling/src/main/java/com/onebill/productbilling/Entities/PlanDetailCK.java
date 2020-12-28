package com.onebill.productbilling.Entities;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PlanDetailCK implements Serializable {

	private int plan;
	private String serviceType;

}
