package com.aractakipyazilimlari.service;

import com.aractakipyazilimlari.model.Okul;
import com.aractakipyazilimlari.model.Sirket;

public interface AdminService {
	
	public void okulEkle(Okul okul );
	public void okulSil(Okul okul);
	public void okulGuncelle(Okul okul);
	
	public void sirketEkle(Sirket sirket, Okul hangiOkul);
	public void sirketSil(Sirket sirket, Okul hangiOkul);
	public void sirketGuncelle(Sirket sirket, Okul hangiOkul);

	
}
