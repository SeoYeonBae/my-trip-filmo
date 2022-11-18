package com.ssafy.vue.do17.model;

public class DoDto {
	int areaCode;
	String name;

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DoDto [areaCode=" + areaCode + ", name=" + name + "]";
	}

	
}
