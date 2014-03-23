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
public class Servis {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String plaka;
	private String soforAdi;
	private String soforSoyAdi;
	private int soforTelefon;
	private String soforKullaniciAdi;
	private String soforSifre;
	private String soforMailAdres;
	
	@ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(name="servisListesi")
	private List<Servis> servisListesi;
	
	
	@OneToMany
	private List<Ogrenci> anlasmaliOgrenciListesi ;
	

	
	@OneToMany
	private List<Sirket> servisinAnlasmaliSirketListesi;
	
	@OneToMany
	private List<Okul> servisinAnlasmaliOkulListesi;
	

	
	//********************************************************//	

		
	public String getPlaka() {
		return plaka;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<Sirket> getServisinAnlasmaliSirketListesi() {
		return servisinAnlasmaliSirketListesi;
	}


	public void setServisinAnlasmaliSirketListesi(
			List<Sirket> servisinAnlasmaliSirketListesi) {
		this.servisinAnlasmaliSirketListesi = servisinAnlasmaliSirketListesi;
	}


	public List<Okul> getServisinAnlasmaliOkulListesi() {
		return servisinAnlasmaliOkulListesi;
	}


	public void setServisinAnlasmaliOkulListesi(
			List<Okul> servisinAnlasmaliOkulListesi) {
		this.servisinAnlasmaliOkulListesi = servisinAnlasmaliOkulListesi;
	}


	public String getSoforMailAdres() {
		return soforMailAdres;
	}
	public void setSoforMailAdres(String soforMailAdres) {
		this.soforMailAdres = soforMailAdres;
	}
	public String getSoforSoyAdi() {
		return soforSoyAdi;
	}
	public void setSoforSoyAdi(String soforSoyAdi) {
		this.soforSoyAdi = soforSoyAdi;
	}

	public List<Servis> getServisListesi() {
		return servisListesi;
	}
	public void setServisListesi(List<Servis> servisListesi) {
		this.servisListesi = servisListesi;
	}
	public List<Ogrenci> getAnlasmaliOgrenciListesi() {
		return anlasmaliOgrenciListesi;
	}
	public void setAnlasmaliOgrenciListesi(List<Ogrenci> anlasmaliOgrenciListesi) {
		this.anlasmaliOgrenciListesi = anlasmaliOgrenciListesi;
	}
	public void setPlaka(String plaka) {
		this.plaka = plaka;
	}
	public String getSoforAdi() {
		return soforAdi;
	}
	public void setSoforAdi(String soforAdi) {
		this.soforAdi = soforAdi;
	}
	public int getSoforTelefon() {
		return soforTelefon;
	}
	public void setSoforTelefon(int soforTelefon) {
		this.soforTelefon = soforTelefon;
	}
	public String getSoforKullaniciAdi() {
		return soforKullaniciAdi;
	}
	public void setSoforKullaniciAdi(String soforKullaniciAdi) {
		this.soforKullaniciAdi = soforKullaniciAdi;
	}
	public String getSoforSifre() {
		return soforSifre;
	}
	public void setSoforSifre(String soforSifre) {
		this.soforSifre = soforSifre;
	}
	


	

	//********************************************************//
	
//	@Override
//	public void servisDetayGor(Servis plaka) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void ogrenciArama(Ogrenci ogreniID) {
//		// TODO Auto-generated method stub
//		
//	};
//	
	
	

}
