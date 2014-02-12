package com.aractakipyazilimlari.model;

import java.util.ArrayList;
import java.util.Date;
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
public class Okul  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String okulAdi;
	private String okulAdres;
	private String okulAdresEnlem;
	private String okulAdresBoylam;
	private int okulTelefon;
	private String okulKullaniciAdi;
	private String okulSifre;
	private String okulMailAdres;
	

//	private Date okulAnlasmaBaslangicTarihi;
//	private Date okulAnlasmaBitisTarihi;

	private String okulunKullanacagiGSMDatasi;
	
	
//	@ElementCollection(fetch=FetchType.LAZY)
//	@CollectionTable(name="okulListesi")
//	private List<Okul> okulListesi = new ArrayList<Okul>();
	

	
	@OneToMany
	private List<Ogrenci>okulunOgrenciListesi= new ArrayList<Ogrenci>();
	
	@OneToMany
	private List<Servis>okulunServisListesi= new ArrayList<Servis>();
	
	@OneToOne
	private Sirket okulunAnlasmaliSirketi;


		
	// ********************************************************//
	
	public List<Servis> getOkulunServisListesi() {
		return okulunServisListesi;
	}

	public void setOkulunServisListesi(List<Servis> okulunServisListesi) {
		this.okulunServisListesi = okulunServisListesi;
	}

	public Sirket getOkulunAnlasmaliSirketi() {
		return okulunAnlasmaliSirketi;
	}

	public void setOkulunAnlasmaliSirketi(Sirket okulunAnlasmaliSirketi) {
		this.okulunAnlasmaliSirketi = okulunAnlasmaliSirketi;
	}

	public List<Ogrenci> getOkulunOgrenciListesi() {
		return okulunOgrenciListesi;
	}

	public void setOkulunOgrenciListesi(List<Ogrenci> okulunOgrenciListesi) {
		this.okulunOgrenciListesi = okulunOgrenciListesi;
	}

	public String getOkulAdi() {
		return okulAdi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOkulunKullanacagiGSMDatasi() {
		return okulunKullanacagiGSMDatasi;
	}

	public void setOkulunKullanacagiGSMDatasi(String okulunKullanacagiGSMDatasi) {
		this.okulunKullanacagiGSMDatasi = okulunKullanacagiGSMDatasi;
	}

	public void setOkulAdi(String okulAdi) {
		this.okulAdi = okulAdi;
	}

	public String getOkulAdres() {
		return okulAdres;
	}

	public void setOkulAdres(String okulAdres) {
		this.okulAdres = okulAdres;
	}

	public String getOkulAdresEnlem() {
		return okulAdresEnlem;
	}

	public void setOkulAdresEnlem(String okulAdresEnlem) {
		this.okulAdresEnlem = okulAdresEnlem;
	}

	public String getOkulAdresBoylam() {
		return okulAdresBoylam;
	}

	public void setOkulAdresBoylam(String okulAdresBoylam) {
		this.okulAdresBoylam = okulAdresBoylam;
	}

	public int getOkulTelefon() {
		return okulTelefon;
	}

	public void setOkulTelefon(int okulTelefon) {
		this.okulTelefon = okulTelefon;
	}

	public String getOkulKullaniciAdi() {
		return okulKullaniciAdi;
	}

	public void setOkulKullaniciAdi(String okulKullaniciAdi) {
		this.okulKullaniciAdi = okulKullaniciAdi;
	}

	public String getOkulSifre() {
		return okulSifre;
	}

	public void setOkulSifre(String okulSifre) {
		this.okulSifre = okulSifre;
	}

	public String getOkulMailAdres() {
		return okulMailAdres;
	}

	public void setOkulMailAdres(String okulMailAdres) {
		this.okulMailAdres = okulMailAdres;
	}


//	public Date getOkulAnlasmaBaslangicTarihi() {
//		return okulAnlasmaBaslangicTarihi;
//	}
//
//	public void setOkulAnlasmaBaslangicTarihi(Date okulAnlasmaBaslangicTarihi) {
//		this.okulAnlasmaBaslangicTarihi = okulAnlasmaBaslangicTarihi;
//	}
//
//	public Date getOkulAnlasmaBitisTarihi() {
//		return okulAnlasmaBitisTarihi;
//	}
//
//	public void setOkulAnlasmaBitisTarihi(Date okulAnlasmaBitisTarihi) {
//		this.okulAnlasmaBitisTarihi = okulAnlasmaBitisTarihi;
//	}
	
	//*********************************************************

//	@Override
//	public void ogrenciArama(Ogrenci ogreniID) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void servisArama(Servis plaka) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	@Override
//	public void servisDetayGor(Servis plaka) {
//	//	String adi=getOkulAdi();
//		//getOkulunAnlasmaliSirketi().getAnlasmaliOkulListesi()
//		
//	}








}
