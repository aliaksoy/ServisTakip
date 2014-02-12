package org.sahin.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Futbolcu extends BaseEntity {
    
	private static final long serialVersionUID = 1L;

	String adi,soyadi;
	
	String guvenlikSorusu;
	@OneToOne
	Futbolcu arkadas;
	public Futbolcu(String ad, String soyad) {
		adi=ad;
		soyadi=soyad;
	}

	public Futbolcu() {
		
	}

	public String getAdi() {
		return adi;
	}


	public void setAdi(String adi) {
		this.adi = adi;
	}


	public String getSoyadi() {
		return soyadi;
	}


	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}
	
	


	  @Override
	  public boolean equals(Object obj) {
	   if (obj == null)
	    return false;
	   if (obj instanceof Futbolcu) {
		   Futbolcu other = (Futbolcu) obj;
	    if (other.getId() == this.getId() )
	     return true;
	   }
	   return false;
	  }

	 
	public Futbolcu getArkadas() {
		return arkadas;
	}

	public void setArkadas(Futbolcu arkadas) {
		this.arkadas = arkadas;
	}

		public int hashCode() {
	         return getId().intValue();
	     }



}
