package org.sahin.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessegesUtil {
	public static String MESSAGES_NAME="msg";//Proje startup da init edilebilir parametrik olabilir
	public static void addMessage(String str) {  
	  String message=getStringMessage(str);
    	/*
    	FacesContext context = FacesContext.getCurrentInstance();
  		ResourceBundle bundle = ResourceBundle.getBundle("msg", context.getViewRoot().getLocale());
  		return bundle.getString("goodbye");
    	 */
    	
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Sample warn message",message));
       
    }

	private static ResourceBundle getResourceBundle() {
		FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context,MESSAGES_NAME );
		return bundle;
	}
	private static String getStringMessage(ResourceBundle bundle,String str) {
		if(bundle==null){
			bundle=getResourceBundle();
		}
		bundle = getResourceBundle();
        String  message = bundle.getString(str);
		return message;
	}
	private static String getStringMessage(String str) {
		return getStringMessage(null, str);
	}
}