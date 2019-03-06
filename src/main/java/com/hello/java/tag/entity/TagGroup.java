package com.hello.java.tag.entity;

import java.util.List;

public class TagGroup {
	private TagSet infoTags;
	private List<TagSet> tableTags;
	
	public TagSet getInfoTags() {
		return infoTags;
	}
	public void setInfoTags(TagSet infoTags) {
		this.infoTags = infoTags;
	}
	public List<TagSet> getTableTags() {
		return tableTags;
	}
	public void setTableTags(List<TagSet> tableTags) {
		this.tableTags = tableTags;
	}
	
	public boolean isValid() {
		if(infoTags!=null && infoTags.isValid()) {
			return true;
		}else if(tableTags!=null && tableTags!=null) {
			for(TagSet tableTag:tableTags) {
				if(tableTag.isValid()) {
					return true;
				}
			}
		}
		return false;
	}

	public Tag findTag(String key) {
		Tag target = findTarget(infoTags, key);;
		if(target == null && tableTags != null) {
			for(TagSet tableTag:tableTags) {
				target = findTarget(tableTag, key);
				if(tableTag != null) {
					return target;
				}
			}
		}
		return target;
	}
	
	private Tag findTarget(TagSet tags, String key) {
		if(key!=null && tags!=null && tags.isValid()) {			
			for(Tag t:tags.getTags()) {
				if(key.equalsIgnoreCase(t.getKey())) {
					return t;
				}
			}
		}
		return null;
	}
}
