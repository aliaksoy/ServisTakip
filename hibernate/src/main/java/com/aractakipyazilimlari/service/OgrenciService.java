package com.aractakipyazilimlari.service;

import com.aractakipyazilimlari.model.Ogrenci;

public interface OgrenciService {

	public void smsAtilmaZamaniBelirle(Ogrenci ogrenciID, String sabahMiAksammi);
	public void telefonGuncelle(Ogrenci ogrenciID,String sabahMiAksammi);
	public void okulaGitmemeDurumunuBildir(Ogrenci ogrenciID,String neGun);
	public void sikayetOneriBildir(Ogrenci ogrenciID, String mesaj);
	public void servisDetayGor(Ogrenci ogrenciID);
	
}
