package org.sahin.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.sahin.model.BaseEntity;
import org.sahin.repository.StaticRepo;

public class HomeBean<T extends BaseEntity> implements Serializable {

	public static final long serialVersionUID = 1L;
	boolean conversational = false;
	@Inject
	protected EntityManagerDao entityManagerDao;
	@Inject
	@DataRepository
	protected EntityManager entityManager;
	private Long id;
	private T instance;
	boolean fillObjectsAutomatically = true;
	protected List<T> objectList = new ArrayList<T>();
	@Inject
	private Conversation conversation = null;

	protected Logger logger = Logger.getLogger(getClass());

	public boolean isConversational() {
		return conversational;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public void setConversational(boolean conversational) {
		this.conversational = conversational;
	}

	public List<T> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<T> objectList) {
		this.objectList = objectList;
	}

	public T getInstance() {
		if (instance == null) {
			if (id != null) {
				instance = loadInstance();
			} else {
				instance = createInstance();
			}
		}
		return instance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public T loadInstance() {
		return entityManagerDao.find(getClassType(), getId());
	}

	public T createInstance() {
		try {
			return getClassType().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void clearInstance() {
		setInstance(createInstance());
	}

	@SuppressWarnings("unchecked")
	private Class<T> getClassType() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	public boolean isManaged() {
		return getInstance().getId() != null;
	}

	public String save() {
		if (isManaged()) {
			entityManagerDao.updateObject(getInstance());
		} else {
			entityManagerDao.createObject(getInstance());
		}
		addMessage("action.save.succesfull", FacesMessage.SEVERITY_INFO);
		//clearInstance();

		return "saved";
	}

	public String save(Object obj) {
		if (isManaged()) {
			entityManagerDao.updateObject(obj);
		} else {
			entityManagerDao.createObject(obj);
		}
		addMessage("action.save.succesfull", FacesMessage.SEVERITY_INFO);
		return "saved";
	}

	public String saveAll(List<Object> saveList) {
		entityManagerDao.saveAll(saveList);
		addMessage("action.save.succesfull", FacesMessage.SEVERITY_INFO);
		return "saved";
	}

	public String cancel() {
		try {
			if (conversational)
				conversation.end();
		} catch (Exception e) {

		}
		return "cancelled";
	}

	public void initConversation() {

		StaticRepo.entityManager = entityManager;
		StaticRepo.entityManagerDao = entityManagerDao;
		try {
			conversational = true;

			if (conversational) {
				if (conversation.isTransient()) {
					conversation.begin();

					System.out.println("conversation: ID  " + conversation.getId());
				}
			}
		} catch (Exception e) {

		}
	}

	public String delete() {
		System.out.println("Deleting " + getInstance());
		entityManagerDao.deleteObject(getInstance());
		try {

			if (conversational)
				conversation.end();
		} catch (Exception e) {

		}
		return "deleted";
	}

	public void setInstance(T instance) {
		this.instance = instance;
	}

	public void onPageLoad() {

		if (!FacesContext.getCurrentInstance().isPostback()) {
			clearInstance();
			initConversation();
			if (fillObjectsAutomatically)
				fillObjects();
		}
	}

	@SuppressWarnings("unchecked")
	public void fillObjects() {
		objectList = entityManagerDao.findAll(getInstance().getClass());
	}

	public void addMessage(String str) {
		addMessage(str, null);

	}
	public void addMessageStr(String str, FacesMessage.Severity fmType) {
		
		String message = str;
		/*
		 * FacesContext context = FacesContext.getCurrentInstance(); ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale()); return bundle.getString("goodbye");
		 */

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fmType, message, message));

	}
	public void addMessage(String str, FacesMessage.Severity fmType) {
		FacesContext context = FacesContext.getCurrentInstance();
		fmType = FacesMessage.SEVERITY_WARN;
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		String message = bundle.getString(str);
		/*
		 * FacesContext context = FacesContext.getCurrentInstance(); ResourceBundle bundle = ResourceBundle.getBundle("messages", context.getViewRoot().getLocale()); return bundle.getString("goodbye");
		 */

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fmType, message, message));

	}

}
