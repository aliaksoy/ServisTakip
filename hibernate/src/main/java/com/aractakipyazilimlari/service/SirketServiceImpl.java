package com.aractakipyazilimlari.service;

import javax.inject.Inject;

import com.aractakipyazilimlari.dao.ObjectDao;
import com.aractakipyazilimlari.model.Ogrenci;
import com.aractakipyazilimlari.model.Okul;
import com.aractakipyazilimlari.model.Servis;
import com.aractakipyazilimlari.model.Sirket;

public class SirketServiceImpl implements SirketService{
	
	@Inject 
	public ObjectDao objectDao;
	

	@Override
	public void servisDetayGor(Sirket sirketID) {
	}

	//****************************************************************//

	@Override
	public void servisKaydet(Servis servis, Sirket hangiSirket, Okul hangiOkul) {
		
		servis.getServisinAnlasmaliSirketListesi().add(hangiSirket);
		servis.getServisinAnlasmaliOkulListesi().add(hangiOkul);
		objectDao.objeKaydet(servis);	
	}


	@Override
	public void servisSil(Servis servis, Sirket hangiSirket, Okul hangiOkul) {
		servis.getServisinAnlasmaliSirketListesi().remove(hangiSirket);
		servis.getServisinAnlasmaliOkulListesi().remove(hangiOkul);
		objectDao.objeSil(servis);
	}


	@Override
	public void servisGuncelle(Servis servis, Sirket hangiSirket, Okul hangiOkul) {
		// TODO Auto-generated method stub guncellemeye bak
		objectDao.objeGuncelle(servis);
	}

	//****************************************************************//

	@Override
	public void ogrenciKaydet(Ogrenci ogrenci, Servis hangiServis) {
		objectDao.objeKaydet(ogrenci);		
		ogrenci.setServis(hangiServis);
	}


	@Override
	public void ogrenciSil(Ogrenci ogrenci) {
		objectDao.objeSil(ogrenci);
	}


	@Override
	public void ogrenciGuncelle(Ogrenci ogrenci) {
		objectDao.objeGuncelle(ogrenci);
	}


}


	