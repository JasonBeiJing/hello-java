package com.hello.java.tag.service.impl;

import com.hello.java.tag.entity.TagGroup;
import com.hello.java.tag.service.ITagParserService;
import com.hello.java.tag.util.TagArgumentsDTO;

public class DUTagParser implements ITagParserService {
	
	private static final String[] supportedTags = {"C", "D"};

	@Override
	public boolean support(TagGroup tagGroup) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void parse(TagGroup tagGroup, TagArgumentsDTO tagArguments) {
		// TODO Auto-generated method stub

	}

}
