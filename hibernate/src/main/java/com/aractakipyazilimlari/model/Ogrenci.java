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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Ogrenci{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String ogrenciAdi; 
	private String ogrenciSoyAdi; 
	private String veliAdi;
	private String veliSoyAdi;
	private int veliOgrenciAlirkenSmsAtilacakTelefon;
	private int veliOgrenciBirakirkenSmsAtilacakTelefon;
	private int veliOgrenciOkulaVardigindaSmsAtilacakTelefon;

	private String veliAdres;
	private String veliAdresEnlem;
	private String veliAdresBoylam;
	private String veliKullaniciAdi;
	private String veliSifre;
	private String veliMailAdres;

	
	private int ogrenciOkulaGiderkenSmsGelmeZamani;
	private int ogrenciOkuldanGelirkenSmsGelmeZamani;
	
	private boolean okulaGitmeDurumu=true;
	private boolean lisansDurumu=false;
	private String oneriSikayet;
	
	@ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(name="ogrenciListesi")
	private List<Ogrenci> ogrenciListesi ;
	
	@OneToOne
	private Servis servis;
	
	@OneToOne
	private Okul okul;
	
	
	@OneToOne
	private Sirket sirket;
	
	@OneToOne
	private Lisans lisans;

	// ********************************************************//
	
	
	public Servis getServis() {
		return servis;
	}

	public Lisans getLisans() {
		return lisans;
	}

	public void setLisans(Lisans lisans) {
		this.lisans = lisans;
	}

	public int getOgrenciOkulaGiderkenSmsGelmeZamani() {
		return ogrenciOkulaGiderkenSmsGelmeZamani;
	}

	public void setOgrenciOkulaGiderkenSmsGelmeZamani(
			int ogrenciOkulaGiderkenSmsGelmeZamani) {
		this.ogrenciOkulaGiderkenSmsGelmeZamani = ogrenciOkulaGiderkenSmsGelmeZamani;
	}

	public int getOgrenciOkuldanGelirkenSmsGelmeZamani() {
		return ogrenciOkuldanGelirkenSmsGelmeZamani;
	}

	public void setOgrenciOkuldanGelirkenSmsGelmeZamani(
			int ogrenciOkuldanGelirkenSmsGelmeZamani) {
		this.ogrenciOkuldanGelirkenSmsGelmeZamani = ogrenciOkuldanGelirkenSmsGelmeZamani;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVeliOgrenciAlirkenSmsAtilacakTelefon() {
		return veliOgrenciAlirkenSmsAtilacakTelefon;
	}

	public void setVeliOgrenciAlirkenSmsAtilacakTelefon(
			int veliOgrenciAlirkenSmsAtilacakTelefon) {
		this.veliOgrenciAlirkenSmsAtilacakTelefon = veliOgrenciAlirkenSmsAtilacakTelefon;
	}

	public int getVeliOgrenciBirakirkenSmsAtilacakTelefon() {
		return veliOgrenciBirakirkenSmsAtilacakTelefon;
	}

	public void setVeliOgrenciBirakirkenSmsAtilacakTelefon(
			int veliOgrenciBirakirkenSmsAtilacakTelefon) {
		this.veliOgrenciBirakirkenSmsAtilacakTelefon = veliOgrenciBirakirkenSmsAtilacakTelefon;
	}

	public int getVeliOgrenciOkulaVardigindaSmsAtilacakTelefon() {
		return veliOgrenciOkulaVardigindaSmsAtilacakTelefon;
	}

	public void setVeliOgrenciOkulaVardigindaSmsAtilacakTelefon(
			int veliOgrenciOkulaVardigindaSmsAtilacakTelefon) {
		this.veliOgrenciOkulaVardigindaSmsAtilacakTelefon = veliOgrenciOkulaVardigindaSmsAtilacakTelefon;
	}

	public Okul getOkul() {
		return okul;
	}

	public void setOkul(Okul okul) {
		this.okul = okul;
	}

	public Sirket getSirket() {
		return sirket;
	}

	public void setSirket(Sirket sirket) {
		this.sirket = sirket;
	}

	public String getOneriSikayet() {
		return oneriSikayet;
	}

	public void setOneriSikayet(String oneriSikayet) {
		this.oneriSikayet = oneriSikayet;
	}

	public boolean isLisansDurumu() {
		return lisansDurumu;
	}

	public void setLisansDurumu(boolean lisansDurumu) {
		this.lisansDurumu = lisansDurumu;
	}

	public boolean isOkulaGitmeDurumu() {
		return okulaGitmeDurumu;
	}

	public void setOkulaGitmeDurumu(boolean okulaGitmeDurumu) {
		this.okulaGitmeDurumu = okulaGitmeDurumu;
	}






	public String getVeliSoyAdi() {
		return veliSoyAdi;
	}

	public void setVeliSoyAdi(String veliSoyAdi) {
		this.veliSoyAdi = veliSoyAdi;
	}

	public String getOgrenciSoyAdi() {
		return ogrenciSoyAdi;
	}

	public void setOgrenciSoyAdi(String ogrenciSoyAdi) {
		this.ogrenciSoyAdi = ogrenciSoyAdi;
	}

	public String getVeliMailAdres() {
		return veliMailAdres;
	}

	public void setVeliMailAdres(String veliMailAdres) {
		this.veliMailAdres = veliMailAdres;
	}

	public String getVeliAdresEnlem() {
		return veliAdresEnlem;
	}

	public void setVeliAdresEnlem(String veliAdresEnlem) {
		this.veliAdresEnlem = veliAdresEnlem;
	}

	public String getVeliAdresBoylam() {
		return veliAdresBoylam;
	}

	public void setVeliAdresBoylam(String veliAdresBoylam) {
		this.veliAdresBoylam = veliAdresBoylam;
	}

	public List<Ogrenci> getOgrenciListesi() {
		return ogrenciListesi;
	}

	public void setOgrenciListesi(List<Ogrenci> ogrenciListesi) {
		this.ogrenciListesi = ogrenciListesi;
	}

	public void setServis(Servis servis) {
		this.servis = servis;
	}

	public String getOgrenciAdi() {
		return ogrenciAdi;
	}

	public void setOgrenciAdi(String ogrenciAdi) {
		this.ogrenciAdi = ogrenciAdi;
	}

	public String getVeliAdi() {
		return veliAdi;
	}

	public void setVeliAdi(String veliAdi) {
		this.veliAdi = veliAdi;
	}


	public String getVeliAdres() {
		return veliAdres;
	}

	public void setVeliAdres(String veliAdres) {
		this.veliAdres = veliAdres;
	}

	public String getVeliKullaniciAdi() {
		return veliKullaniciAdi;
	}

	public void setVeliKullaniciAdi(String veliKullaniciAdi) {
		this.veliKullaniciAdi = veliKullaniciAdi;
	}

	public String getVeliSifre() {
		return veliSifre;
	}

	public void setVeliSifre(String veliSifre) {
		this.veliSifre = veliSifre;
	}


	// ********************************************************//

	public void veliTelefonGuncelle(Ogrenci ogreniKimlikNo) {};

	public void aranmaZamaniniDegistir(Ogrenci ogreniKimlikNo){};
	
	public void ogrencininOkulaGitmeyeceginiBildir(Ogrenci ogreniKimlikNo){};
	
	public void sikayetOneriBildir(){};
	
	// ********************************************************//

//	@Override
//	public void servisDetayGor(Servis plaka) {
//		System.out.println(getServis());
//		
//	};

}
