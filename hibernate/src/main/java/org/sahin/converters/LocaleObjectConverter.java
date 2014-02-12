package org.sahin.converters;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.sahin.internalization.LocaleChanger;
import org.sahin.internalization.LocaleObject;

@Named(value = "localeObjectConverter")
@SessionScoped
@ManagedBean(name = "localeObjectConverter")
@FacesConverter(value = "localeObjectConverter")
public class LocaleObjectConverter implements Converter, Serializable {

	private static final long serialVersionUID = -1549948404056541340L;
	static Logger logger=Logger.getLogger(LocaleObjectConverter.class);
	public LocaleObjectConverter() {}
    public static HashMap<String,LocaleObject> localeMap=new HashMap<String,LocaleObject>();
    
    static{
    	logger.info("initilizing LocaleObjectConverter ");
    	localeMap.put(""+Locale.JAPAN, new LocaleObject(Locale.JAPAN,""+Locale.JAPAN,"prompt.lang.Japanase","prompt.lang.Japan",LocaleChanger.IMAGE_PATH+"Japan.png"));
    	localeMap.put(""+Locale.ENGLISH, new LocaleObject(Locale.ENGLISH,""+Locale.ENGLISH,"prompt.lang.English","prompt.lang.England",LocaleChanger.IMAGE_PATH+"England.png"));
    	localeMap.put(""+new Locale("tr", "TR"), new LocaleObject(new Locale("tr", "TR"),""+new Locale("tr", "TR"),"prompt.lang.Turkish","prompt.lang.Turkey",LocaleChanger.IMAGE_PATH+"Turkey.png"));
	
    }
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		logger.info("getAsObject: "+value);
		return localeMap.get(value);
	}

	public String getAsString(FacesContext context, UIComponent component,Object value) {
		logger.info("	getAsObject: "+value);
		LocaleObject obj= (LocaleObject) value;
		logger.info("getAsString: "+value+"   "+obj);
		return obj.getLocaleKey();
	}

}
