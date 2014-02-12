package sahibinden;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Araba {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String marka;
	private int model;
	private Date ilanTarihi = new Date();
	private String renk;
	private int motorGucu;
	private boolean takas;
	private boolean ikinciEl = true;
	private String resim1;
	private String detay;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public Date getIlanTarihi() {
		return ilanTarihi;
	}

	public void setIlanTarihi(Date ilanTarihi) {
		this.ilanTarihi = ilanTarihi;
	}

	public String getRenk() {
		return renk;
	}

	public void setRenk(String renk) {
		this.renk = renk;
	}

	public int getMotorGucu() {
		return motorGucu;
	}

	public void setMotorGucu(int motorGucu) {
		this.motorGucu = motorGucu;
	}

	public boolean isTakas() {
		return takas;
	}

	public void setTakas(boolean takas) {
		this.takas = takas;
	}

	public boolean isIkinciEl() {
		return ikinciEl;
	}

	public void setIkinciEl(boolean ikinciEl) {
		this.ikinciEl = ikinciEl;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public String getResim1() {
		return resim1;
	}

	public void setResim1(String resim1) {
		this.resim1 = resim1;
	}

	public String getDetay() {
		return detay;
	}

	public void setDetay(String detay) {
		this.detay = detay;
	}

}
