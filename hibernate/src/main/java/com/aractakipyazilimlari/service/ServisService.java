package com.aractakipyazilimlari.service;

import com.aractakipyazilimlari.model.Ogrenci;
import com.aractakipyazilimlari.model.Servis;

public interface ServisService {
	public void serviseBasla(Servis plaka);
	public void hizBilgisiGonder(Servis plaka);
	public void konumBilgisiGonder(Servis plaka);
	public void smsAt(Ogrenci veliTelefonu);

}
