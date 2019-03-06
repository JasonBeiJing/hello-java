package com.hello.java.tag.util;

public class TagArgumentsDTO {

	private String contractNumber;
	private String duId;
	
	public TagArgumentsDTO(String contractNumber, String duId) {
		super();
		this.contractNumber = contractNumber;
		this.duId = duId;
	}
	
	public String getContractNumber() {
		return contractNumber;
	}
	public String getDuId() {
		return duId;
	}
}
