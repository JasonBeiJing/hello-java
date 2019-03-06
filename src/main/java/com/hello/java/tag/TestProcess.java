package com.hello.java.tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.hello.java.tag.entity.Tag;
import com.hello.java.tag.entity.TagGroup;
import com.hello.java.tag.util.TagArgumentsDTO;
import com.hello.java.tag.util.TagArgumentsDTOBuilder;

public class TestProcess {

	public static void main(String[] args) {
		//根据国家分流，先试运行新解决方案
		
		//构建请求参数
		TagArgumentsDTO tagArguments = TagArgumentsDTOBuilder.create().
			addContractNumber("contractNumber").
			addDuId("duId").build();
		String templateId = "templateId";
		
		//解析标签
		TagParserHelper helper = new TagParserHelper();
		TagGroup tagGroup = helper.parseTags(templateId, tagArguments);
		
		//替换自定义标签值
		Map<String, String> customizedTags = new HashMap<>();
		for(Entry<String, String> customizedTag:customizedTags.entrySet()) {
			Tag tag = tagGroup.findTag(customizedTag.getKey());
			tag.setValue(customizedTag.getValue());
		}
		
		//组装工具云数据
		
		//发送请求，处理结果
	}
}
