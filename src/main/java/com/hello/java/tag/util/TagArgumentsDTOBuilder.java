package com.hello.java.tag.util;

public class TagArgumentsDTOBuilder {
	private String contractNumber;
	private String duId;

	private TagArgumentsDTOBuilder() {
		super();
	}

	public static TagArgumentsDTOBuilder create() {
		return new TagArgumentsDTOBuilder();
	}

	public TagArgumentsDTOBuilder addContractNumber(final String contractNumber) {
		this.contractNumber = contractNumber;
		return this;
	}
	
	public TagArgumentsDTOBuilder addDuId(final String duId) {
		this.duId = duId;
		return this;
	}

	public TagArgumentsDTO build() {
		return new TagArgumentsDTO(
				contractNumber, 
				duId
				);
	}
}
