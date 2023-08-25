package com.niveus.dto;

import lombok.Data;

@Data
public class StudentsDto {

	private String name;
	private Integer age;
	
	public StudentsDto(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	public StudentsDto() {
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
