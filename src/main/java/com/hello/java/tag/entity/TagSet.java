package com.hello.java.tag.entity;

import java.util.List;

public class TagSet {

	private List<Tag> tags;

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public boolean isValid() {
		if(tags == null || tags.isEmpty()) {
			return false;
		}
		return true;
	}
}
