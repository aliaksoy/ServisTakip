package com.aractakipyazilimlari.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.sahin.persistence.DataRepository;
import org.sahin.persistence.EntityManagerDao;

import com.aractakipyazilimlari.model.Ogrenci;
import com.aractakipyazilimlari.model.Okul;
import com.aractakipyazilimlari.model.Servis;
import com.aractakipyazilimlari.model.Sirket;
@Named
@SessionScoped
public class OkulController implements Serializable {
	
	
	private Okul aktifOkul =new Okul();
	
	@Inject
	protected EntityManagerDao entityManagerDao;
	@Inject
	@DataRepository
	protected EntityManager entityManager;
	
	
	private List<Okul> okulListesi = new ArrayList<Okul>();
	private List<Servis>okulunServisListesi =new ArrayList<Servis>();
	private Sirket okulunAnlasmaliSirketi;
	private List<Ogrenci>okulunOgrenciListesi= new ArrayList<Ogrenci>();
	
	
	public Okul getAktifOkul() {
		return aktifOkul;
	}
	public void setAktifOkul(Okul aktifOkul) {
		this.aktifOkul = aktifOkul;
	}
	
	public List<Okul> getOkulListesi() {
		return okulListesi;
	}
	public void setOkulListesi(List<Okul> okulListesi) {
		this.okulListesi = okulListesi;
	}
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
	
	

	public void okulKaydet() {
		entityManagerDao.createObject(getAktifOkul());
		aktifOkul=new Okul();
		System.out.println("Okul eklendii...");
	};
	
	

}
