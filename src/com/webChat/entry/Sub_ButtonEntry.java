package com.webChat.entry;

import java.util.ArrayList;
import java.util.List;

public class Sub_ButtonEntry {
	private String name;
	private List<Object> sub_button =new ArrayList<Object>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Object> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<Object> sub_button) {
		this.sub_button = sub_button;
	}
}
