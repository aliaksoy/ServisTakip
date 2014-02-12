package sahibinden;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.sahin.persistence.DataRepository;
import org.sahin.persistence.EntityManagerDao;

@Named
@SessionScoped
public class SahibindenArabaController implements Serializable {
	private Araba instance = new Araba();
	private List<Araba> objectList = new ArrayList<Araba>();
	private String arabaId;
	private Date simdi = new Date();
	private int minModelYil = 1920;
	@Inject
	protected EntityManagerDao entityManagerDao;
	@Inject
	@DataRepository
	protected EntityManager entityManager;

	public void kaydet() {
		getInstance().setMarka("asdsds");
		entityManagerDao.createObject(getInstance());
		instance = new Araba();
		System.out.println("eklendi");

	}

	@PostConstruct
	public void init() {

		System.out.println(arabaId);
		Araba araba = new Araba();
		araba.setId(20);
		araba.setIkinciEl(true);
		araba.setIlanTarihi(new Date());
		araba.setMarka("Opel");
		araba.setModel(1999);
		araba.setMotorGucu(145);
		araba.setTakas(false);
		araba.setResim1("http://image5.sahibinden.com/photos/97/81/56/147978156odk.jpg");
		araba.setRenk("KIRMIZI");

		Araba araba2 = new Araba();
		araba2.setResim1("http://image5.sahibinden.com/photos/15/31/08/130153108vnu.jpg");
		araba2.setId(21);
		araba2.setIkinciEl(false);
		araba2.setIlanTarihi(new Date());
		araba2.setMarka("Reno");
		araba2.setModel(2013);
		araba2.setMotorGucu(110);
		araba2.setTakas(false);
		araba2.setRenk("YESIL");
		objectList.add(araba);

		objectList.add(araba2);

	}

	public void ikinciElKontrol() {
		System.out.println("ikinciElKontrol");
		if (!instance.isIkinciEl()) {
			minModelYil = 1900 + simdi.getYear();
			getInstance().setModel(minModelYil);
		} else {
			minModelYil = 1920;
		}
	}

	public void ekle() {

		String donusSayfasi = null;

		System.out.println("eklendi");
		if (!objectList.contains(instance)) {
			objectList.add(instance);
			System.out.println("entityManager" + entityManager);
			getInstance().setMarka("lplp");
			entityManagerDao.createObject(getInstance());
		}
		instance = new Araba();

	}

	public String ekleYadaKal() {

		String donusSayfasi = null;

		System.out.println("eklendi");
		if (!objectList.contains(instance)) {
			objectList.add(instance);
			instance = new Araba();
			donusSayfasi = "arabaList?faces-redirect=true";
		}

		return donusSayfasi;
	}

	public void onPageLoad() {
		if (arabaId != null) {
			for (Araba araba : objectList) {
				if (araba.getId() == Integer.parseInt(arabaId)) {
					instance = araba;
					break;

				}

			}
		}

	}

	public Araba getInstance() {
		return instance;
	}

	public void setInstance(Araba instance) {
		this.instance = instance;
	}

	public List<Araba> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<Araba> objectList) {
		this.objectList = objectList;
	}

	public String getArabaId() {
		return arabaId;
	}

	public void setArabaId(String arabaId) {
		this.arabaId = arabaId;
	}

	public Date getSimdi() {
		return simdi;
	}

	public void setSimdi(Date simdi) {
		this.simdi = simdi;
	}

	public int getMinModelYil() {
		return minModelYil;
	}

	public void setMinModelYil(int minModelYil) {
		this.minModelYil = minModelYil;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManagerDao getEntityManagerDao() {
		return entityManagerDao;
	}

	public void setEntityManagerDao(EntityManagerDao entityManagerDao) {
		this.entityManagerDao = entityManagerDao;
	}

}
