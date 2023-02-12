package com.infy.blog.exception;

public class ResourceNotFoundException extends RuntimeException {
	private String resourceName;
	private String field;
	private long value;
	
	public ResourceNotFoundException(String resourceName, String field, long value) {
		super(String.format("%s is not present with %s : %s",resourceName,field,value));
		this.resourceName = resourceName;
		this.field = field;
		this.value = value;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
	
	
}
