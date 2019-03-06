package com.hello.java.tag.service;

import com.hello.java.tag.entity.TagGroup;
import com.hello.java.tag.util.TagArgumentsDTO;

public interface ITagParserService {

	boolean support(TagGroup tagGroup);
	void parse(TagGroup tagGroup, TagArgumentsDTO tagArguments);
}
