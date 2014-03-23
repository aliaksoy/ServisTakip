package com.aractakipyazilimlari.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Sirket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String sirketAdi;
	private int sirketTelefon;
	private String sirketAdres;
	private String sirketKullaniciAdi;
	private String sirketSifre;
	private String sirketMailAdres;
	
	private boolean aylikToplamOgrenciLisansUcretiOdendimi=false;
	
	@ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(name="sirketListesi")
	private List<Sirket> sirketListesi;
	
	
	@OneToMany
	private List<Ogrenci> anlasmaliOgrenciListesi ;
	
	

	@OneToMany
	private List<Okul> anlasmaliOkulListesi;
	
	@OneToMany
	private List<Servis> anlasmaliServisListesi;

	@OneToMany
	private List<Lisans> lisansListesi;

	
	// ********************************************************//

	
	public List<Okul> getAnlasmaliOkulListesi() {
		return anlasmaliOkulListesi;
	}

	public List<Lisans> getLisansListesi() {
		return lisansListesi;
	}

	public void setLisansListesi(List<Lisans> lisansListesi) {
		this.lisansListesi = lisansListesi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Ogrenci> getAnlasmaliOgrenciListesi() {
		return anlasmaliOgrenciListesi;
	}

	public void setAnlasmaliOgrenciListesi(List<Ogrenci> anlasmaliOgrenciListesi) {
		this.anlasmaliOgrenciListesi = anlasmaliOgrenciListesi;
	}

	public boolean isAylikToplamOgrenciLisansUcretiOdendimi() {
		return aylikToplamOgrenciLisansUcretiOdendimi;
	}

	public void setAylikToplamOgrenciLisansUcretiOdendimi(
			boolean aylikToplamOgrenciLisansUcretiOdendimi) {
		this.aylikToplamOgrenciLisansUcretiOdendimi = aylikToplamOgrenciLisansUcretiOdendimi;
	}

	public String getSirketMailAdres() {
		return sirketMailAdres;
	}

	public void setSirketMailAdres(String sirketMailAdres) {
		this.sirketMailAdres = sirketMailAdres;
	}

	public List<Servis> getAnlasmaliServisListesi() {
		return anlasmaliServisListesi;
	}

	public void setAnlasmaliServisListesi(List<Servis> anlasmaliServisListesi) {
		this.anlasmaliServisListesi = anlasmaliServisListesi;
	}

	public void setAnlasmaliOkulListesi(List<Okul> anlasmaliOkulListesi) {
		this.anlasmaliOkulListesi = anlasmaliOkulListesi;
	}
	
	
	public String getSirketAdi() {
		return sirketAdi;
	}

	public void setSirketAdi(String sirketAdi) {
		this.sirketAdi = sirketAdi;
	}

	public int getSirketTelefon() {
		return sirketTelefon;
	}

	public void setSirketTelefon(int sirketTelefon) {
		this.sirketTelefon = sirketTelefon;
	}

	public String getSirketAdres() {
		return sirketAdres;
	}

	public void setSirketAdres(String sirketAdres) {
		this.sirketAdres = sirketAdres;
	}

	public String getSirketKullaniciAdi() {
		return sirketKullaniciAdi;
	}

	public void setSirketKullaniciAdi(String sirketKullaniciAdi) {
		this.sirketKullaniciAdi = sirketKullaniciAdi;
	}

	public String getSirketSifre() {
		return sirketSifre;
	}

	public void setSirketSifre(String sirketSifre) {
		this.sirketSifre = sirketSifre;
	}

	public List<Sirket> getSirketListesi() {
		return sirketListesi;
	}

	public void setSirketListesi(List<Sirket> sirketListesi) {
		this.sirketListesi = sirketListesi;
	}


	// ********************************************************//
	
//	@Override
//	public void servisDetayGor(Servis plaka) {
//		System.out.println(getAnlasmaliServisListesi());
//		
//	}
//
//	@Override
//	public void ogrenciArama(Ogrenci ogreniID) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void servisArama(Servis plaka) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void okulArama(Okul okulAdi) {
//		// TODO Auto-generated method stub
//		
//	};
//	
//	@Override
//	public void kampanyaHesapla(Okul okulAdi,Kampanya hangiKampanya) {
//		// TODO Auto-generated method stub
//		//Gelen okulAdi parametresinden lisanl� ��renci say�s� bulunacak.
//		//On an i�in admin taraf�ndan tan�ml� olan hangi kampanya varsa o kampanyaya g�re
//		//kampanya fiyat� belirlenecek.
//		//okulAdi.getOkulunServisListesi().getAnlasmaliOgrenciListesi();
//	}

	
	
}
