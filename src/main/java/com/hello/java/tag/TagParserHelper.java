package com.hello.java.tag;

import java.util.List;

import com.hello.java.tag.entity.TagGroup;
import com.hello.java.tag.service.ITagParserService;
import com.hello.java.tag.util.TagArgumentsDTO;

public class TagParserHelper {

	private List<ITagParserService> tagParserServices;
	
	public TagGroup parseTags(String templateId, TagArgumentsDTO tagArguments) {
		if(tagParserServices == null || tagParserServices.isEmpty()) {
			//ERROR cannot parse anything
		}
		TagGroup tagGroup = getTagGroup(templateId);
		if(!tagGroup.isValid()) {
			//ERROR no tags found
		}
		for(ITagParserService s:tagParserServices) {
			if(s.support(tagGroup)) { //could support multiple threads
				s.parse(tagGroup, tagArguments);
			}
		}
		return tagGroup;
	}
	
	private TagGroup getTagGroup(String templateId) {
		//list tags from remote template service with http call
		//assemble TagGroup
		TagGroup tg = new TagGroup();
		return tg;
	}
}
