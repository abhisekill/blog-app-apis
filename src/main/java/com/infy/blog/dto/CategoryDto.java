package com.infy.blog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDto {

	private Integer id;
	@NotEmpty
	@Size(min=4,message = "title should be atleast 4 chars long")
	private String title;
	
	@NotEmpty
	@Size(min=10,message = "desc should be atleast 10 chars long")
	private String desc;

	public CategoryDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
