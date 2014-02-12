package org.sahin.composite;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TestBean implements Serializable{

	private static final long serialVersionUID = 1L;
	String ad="AHMET";
	Date date1;
	int age=33;
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDate1() {
		return new Date();
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	
}
