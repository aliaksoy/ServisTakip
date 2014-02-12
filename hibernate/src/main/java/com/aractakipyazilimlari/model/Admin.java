package com.aractakipyazilimlari.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String yetkiliKullaniciAdi;
	private String yetkiliKullaniciSifre;
	private String kullaniciYetkisi;

	// ********************************************************//

	public String getKullaniciYetkisi() {
		return kullaniciYetkisi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYetkiliKullaniciAdi() {
		return yetkiliKullaniciAdi;
	}

	public void setYetkiliKullaniciAdi(String yetkiliKullaniciAdi) {
		this.yetkiliKullaniciAdi = yetkiliKullaniciAdi;
	}

	public String getYetkiliKullaniciSifre() {
		return yetkiliKullaniciSifre;
	}

	public void setYetkiliKullaniciSifre(String yetkiliKullaniciSifre) {
		this.yetkiliKullaniciSifre = yetkiliKullaniciSifre;
	}

	public void setKullaniciYetkisi(String kullaniciYetkisi) {
		this.kullaniciYetkisi = kullaniciYetkisi;
	}


}
