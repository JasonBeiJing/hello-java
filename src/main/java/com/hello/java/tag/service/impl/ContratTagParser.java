package com.hello.java.tag.service.impl;

import com.hello.java.tag.entity.TagGroup;
import com.hello.java.tag.service.ITagParserService;
import com.hello.java.tag.util.TagArgumentsDTO;

public class ContratTagParser implements ITagParserService {

	private static final String[] supportedTags = {"A", "B"};
	
	@Override
	public boolean support(TagGroup tagGroup) {
		// TODO Auto-generated method stub
		for(String t:supportedTags) {
			if(t.equalsIgnoreCase("")) {				
				return true;
			}
		}
		return false;
	}

	@Override
	public void parse(TagGroup tagGroup, TagArgumentsDTO tagArguments) {
		// TODO Auto-generated method stub

	}

}
