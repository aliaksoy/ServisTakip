package org.sahin.home;

import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.sahin.model.Futbolcu;
import org.sahin.persistence.HomeBean;

@Named
@ConversationScoped
public class FutbolcuHome extends HomeBean<Futbolcu> {
	private static final long serialVersionUID = 1L;

	String date;

	Futbolcu secilenFutbolcu;

	List<Futbolcu> futbolcuListesi;

	public List<Futbolcu> getFutbolcuListesi() {
		return futbolcuListesi;
	}

	public void setFutbolcuListesi(List<Futbolcu> futbolcuListesi) {
		this.futbolcuListesi = futbolcuListesi;
	}

	public Futbolcu getSecilenFutbolcu() {
		return secilenFutbolcu;
	}

	public void setSecilenFutbolcu(Futbolcu secilenFutbolcu) {
		this.secilenFutbolcu = secilenFutbolcu;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void getDoLongWork() {
		System.out.println("Long work");

		for (int i = 0; i < 100; i++) {

		}
		System.out.println("Long work end");
	}

	@Produces
	@Named("futbolcular")
	public List<Futbolcu> getObjectList() {
		return objectList;
	}

	public void search() {
		HashMap<String, String> map = new HashMap<String, String>();

	}

}
