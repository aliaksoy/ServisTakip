package com.aractakipyazilimlari.service;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.aractakipyazilimlari.dao.ObjectDao;
import com.aractakipyazilimlari.model.Okul;
import com.aractakipyazilimlari.model.Sirket;

@Named
@SessionScoped
public class AdminServiceImpl implements AdminService {
	
	
	
	@Inject 
	public ObjectDao objectDao;

	//****************************************************************//
	
	public ObjectDao getObjectDao() {
		return objectDao;
	}


	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}


	@Override
	public void okulEkle(Okul okul) {
		objectDao.objeKaydet(okul);
	}


	@Override
	public void okulSil(Okul okul) {
		objectDao.objeSil(okul);
	}

	@Override
	public void okulGuncelle(Okul okul) {
		objectDao.objeGuncelle(okul);
	}

	//****************************************************************//

	@Override
	public void sirketEkle(Sirket sirket, Okul hangiOkul) {		
		sirket.getAnlasmaliOkulListesi().add(hangiOkul);
		objectDao.objeKaydet(sirket);
	}

	@Override
	public void sirketSil(Sirket sirket, Okul hangiOkul) {
		sirket.getAnlasmaliOkulListesi().remove(hangiOkul);
		objectDao.objeSil(sirket);
		
	}

	@Override
	public void sirketGuncelle(Sirket sirket, Okul hangiOkul) {
		objectDao.objeGuncelle(sirket);
		
		// TODO Guncellemeya bak.
		//sirket.getAnlasmaliOgrenciListesi().;
	}



}
