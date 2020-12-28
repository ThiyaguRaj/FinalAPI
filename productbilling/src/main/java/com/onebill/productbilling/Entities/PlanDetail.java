package com.onebill.productbilling.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "plan_detail")
@IdClass(PlanDetailCK.class)
public class PlanDetail {

	@Id
	@Column(name = "service_type",length = 30)
	private String serviceType;

	@NotNull
	@Column(name = "detail")
	private int detail;

	@NotNull
	@Column(name = "unit",length = 10)
	private String unit;

	@Id
	@ManyToOne
	@JoinColumn(name = "plan_id")
	@JsonIgnore
	private Plan plan;
}
