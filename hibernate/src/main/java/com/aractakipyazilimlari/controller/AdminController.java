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

import com.aractakipyazilimlari.model.*;
@Named
@SessionScoped
public class AdminController implements Serializable {

	private Okul aktifOkul =new Okul();

	
	
	@Inject
	protected EntityManagerDao entityManagerDao;
	@Inject
	@DataRepository
	protected EntityManager entityManager;
	
	private List<Admin> yetkiliListesi = new ArrayList<Admin>();
	// ********************************************************//

	public Okul getAktifOkul() {
		return aktifOkul;
	}

	public void setAktifOkul(Okul aktifOkul) {
		this.aktifOkul = aktifOkul;
	}



	public List<Admin> getYetkiliListesi() {
		return yetkiliListesi;
	}

	public void setYetkiliListesi(List<Admin> yetkiliListesi) {
		this.yetkiliListesi = yetkiliListesi;
	}
	// ********************************************************//


	public void okulKaydet() {
		entityManagerDao.createObject(getAktifOkul());
		aktifOkul=new Okul();
		System.out.println("Okul eklendii...");
	};



}
