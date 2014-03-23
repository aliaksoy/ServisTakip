package com.aractakipyazilimlari.dao;

import javax.inject.Inject;

import org.sahin.persistence.EntityManagerDao;

public class ObjectDaoImpl implements ObjectDao {

	@Inject
	EntityManagerDao em;

	@Override
	public void objeKaydet(Object obje) {
		em.createObject(obje);
	}

	@Override
	public void objeSil(Object obje) {
		em.deleteObject(obje);
	}

	@Override
	public void objeGuncelle(Object obje) {
		em.updateObject(obje);

	}

}
