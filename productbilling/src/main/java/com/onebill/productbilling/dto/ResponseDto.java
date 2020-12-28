package com.onebill.productbilling.dto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class ResponseDto  implements Serializable{
	
	private boolean error;
	
	private Object data;
}
