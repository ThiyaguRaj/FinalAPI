package com.onebill.productbilling.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "plan",uniqueConstraints = @UniqueConstraint(columnNames = {"plan_amount","type","product_id"}))
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "plan_id")
	private int planId;

	@NotNull
	@Column(name = "plan_amount")
	private double planAmount;

	@NotNull
	@Column(name = "plan_frequency")
	private int planFrequency;

	@NotNull
	@Column(name = "type",length = 30)
	private String type;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonIgnore
	private Product product;

	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PlanDetail> detail=new ArrayList<>();

	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PlanCharge> charge=new ArrayList<>();

	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PlanOverdue> due=new ArrayList<>();

}
