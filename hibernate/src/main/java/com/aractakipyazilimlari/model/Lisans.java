package com.aractakipyazilimlari.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Lisans {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int lisansUcreti;
	
	
	@OneToOne
	private Ogrenci ogrenci;
	

	public Ogrenci getOgrenci() {
		return ogrenci;
	}

	public void setOgrenci(Ogrenci ogrenci) {
		this.ogrenci = ogrenci;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLisansUcreti() {
		return lisansUcreti;
	}

	public void setLisansUcreti(int lisansUcreti) {
		this.lisansUcreti = lisansUcreti;
	}

}
