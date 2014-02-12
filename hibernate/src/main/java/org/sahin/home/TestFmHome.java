package org.sahin.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TestFmHome implements Serializable {

	int count1 = 23;
	int count2 = 44;

	List countList = new ArrayList();

	public List getCountList() {
		if (countList.isEmpty())
			fillCountList();
		return countList;
	}

	private void fillCountList() {

		for (int i = 0; i < 40; i++) {
			double count = (100 * Math.random());
			countList.add((int) count);
		}

	}

	public void setCountList(List countList) {
		this.countList = countList;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

}
