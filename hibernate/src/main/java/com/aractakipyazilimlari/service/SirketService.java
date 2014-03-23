package com.aractakipyazilimlari.service;

import com.aractakipyazilimlari.model.Ogrenci;
import com.aractakipyazilimlari.model.Okul;
import com.aractakipyazilimlari.model.Servis;
import com.aractakipyazilimlari.model.Sirket;

public interface SirketService {
	public void servisDetayGor(Sirket sirketID);
	
	public void servisKaydet(Servis servis,Sirket hangiSirket,Okul hangiOkul);
	public void servisSil(Servis servis,Sirket hangiSirket,Okul hangiOkul);
	public void servisGuncelle(Servis servis,Sirket hangiSirket,Okul hangiOkul);	
	
	public void ogrenciKaydet(Ogrenci ogrenci,Servis hangiServis);
	public void ogrenciSil(Ogrenci ogrenci);
	public void ogrenciGuncelle(Ogrenci ogrenci);
		
	

}
