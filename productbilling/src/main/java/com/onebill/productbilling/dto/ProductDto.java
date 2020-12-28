package com.onebill.productbilling.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class ProductDto  implements Serializable{

	private int productId;

	private String productName;

	private String productType;
	
	private List<PlanRespDto> plan;
}
