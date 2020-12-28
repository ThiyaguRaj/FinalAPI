package com.onebill.productbilling.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.onebill.productbilling.Entities.PlanCharge;
import com.onebill.productbilling.Entities.PlanDetail;
import com.onebill.productbilling.Entities.PlanOverdue;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PlanRespDto implements Serializable {

	private int planId;

	private double planAmount;

	private int planFrequency;

	private String type;

	private List<PlanDetail> detail = new ArrayList<PlanDetail>();

	private List<PlanCharge> charge = new ArrayList<PlanCharge>();

	private List<PlanOverdue> due = new ArrayList<PlanOverdue>();
}
