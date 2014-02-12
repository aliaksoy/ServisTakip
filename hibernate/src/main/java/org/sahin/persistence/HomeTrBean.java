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

import org.apache.log4j.Logger;
import org.sahin.model.BaseEntity;
import org.sahin.repository.StaticRepo;


public class HomeTrBean<T extends BaseEntity> implements Serializable {

	public static final long serialVersionUID = 1L;
	private Long id;
	@Inject
	protected EntityManagerDao entityManagerDao;
	private T instance;
	boolean fillObjectsAutomatically=true;
	protected List<T> objectList=new ArrayList<T>();
	protected Logger logger=Logger.getLogger(getClass());
	@Inject
	private Conversation conversation=null;
	boolean conversational=false;
    
	public boolean isConversational() {
		return conversational;
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
		//logger.error("getInstance: "+instance+ "  id"+id);
		if (instance == null) {
			if (id != null) {
				instance = loadInstance();
			} else {
				instance = createInstance();
			}
		}
		//logger.error("    endgetInstance: "+instance+ "  id"+id);
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
		logger.info("Ca not create instance");
		return null;
	}

	public void clearInstance(){
		setInstance(createInstance());
		logger.info("instance:"+getInstance());
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getClassType() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();
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
		logger.error("instance"+instance);
		clearInstance();
		return "saved";
	}
	public String save(Object obj) {
		if (isManaged()) {
			entityManagerDao.updateObject(obj);
		} else {
			entityManagerDao.createObject(obj);
		}
		addMessage("kayit.basarili");
		return "saved";
	}
	public String cancel() {

		try {
			if(conversational)
				conversation.end();
		} catch (Exception e) {
			
		}
		return "cancelled";
	}

	public void initConversation() {
		
		StaticRepo.entityManagerDao=entityManagerDao;
		try {
			if(conversational){
				if (conversation.isTransient()) {
					conversation.begin();
				}
			}
		} catch (Exception e) {
			
		}
	}

	public String delete() {		
		System.out.println("Deleting "+getInstance());
		entityManagerDao.deleteObject(getInstance());
		try {
			if(conversational)
				conversation.end();
		} catch (Exception e) {
			
		}
		return "deleted";		
	}
	
	public void setInstance(T instance) {
		this.instance = instance;
	}

	public void onPageLoad(){
		
		logger.error("onPageLoad: "+this);
		if(!FacesContext.getCurrentInstance().isPostback()){
			clearInstance();
			initConversation();
			if(fillObjectsAutomatically)
				fillObjects();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void fillObjects(){
		objectList=entityManagerDao.findAll(getInstance().getClass());
	}
	
    public void addMessage(String str) {  
    	FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        String  message = bundle.getString(str);
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"INFO",message));
       
    }
	
}
