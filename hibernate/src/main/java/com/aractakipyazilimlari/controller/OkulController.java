package com.aractakipyazilimlari.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import com.aractakipyazilimlari.model.Ogrenci;
import com.aractakipyazilimlari.model.Okul;
import com.aractakipyazilimlari.model.Servis;
import com.aractakipyazilimlari.model.Sirket;
@Named
@SessionScoped
public class OkulController implements Serializable {
	
	private List<Okul> okulListesi = new ArrayList<Okul>();
	private List<Servis>okulunServisListesi =new ArrayList<Servis>();
	private Sirket okulunAnlasmaliSirketi;
	private List<Ogrenci>okulunOgrenciListesi= new ArrayList<Ogrenci>();
	
	
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
	
	
	

}
