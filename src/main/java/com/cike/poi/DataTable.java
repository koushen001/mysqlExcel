package com.cike.poi;

public class DataTable {
	private String field;
	private String type;
	private String key;
	private String comment;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "DataTable [field=" + field + ", type=" + type + ", key=" + key
				+ ", comment=" + comment + "]";
	}

}
