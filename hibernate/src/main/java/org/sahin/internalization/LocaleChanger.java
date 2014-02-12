package org.sahin.internalization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.apache.log4j.Logger;

@Named
@SessionScoped

public class LocaleChanger implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String IMAGE_PATH="/resources/images/flags/";
	private Logger logger=Logger.getLogger(LocaleChanger.class);
    private String localeCode;
    private Locale selectedLocale=new Locale("en");//new Locale("tr", "TR");
	
    private	LocaleObject localeJapanase=new LocaleObject(Locale.JAPAN,""+Locale.JAPAN,"prompt.lang.Japanase","prompt.lang.Japan",IMAGE_PATH+"Japan.png");
    private LocaleObject localeEnglish=new LocaleObject(Locale.ENGLISH,""+Locale.ENGLISH,"prompt.lang.English","prompt.lang.England",IMAGE_PATH+"England.png");
    private LocaleObject localeTurkish=new LocaleObject(new Locale("tr", "TR"),""+new Locale("tr", "TR"),"prompt.lang.Turkish","prompt.lang.Turkey",IMAGE_PATH+"Turkey.png");
	
    private List<LocaleObject> activelocales=new ArrayList<LocaleObject>(); 
    
	
	@PostConstruct
	public void initLocales() {
		
		//logger.info("initilizing Locales "+this);
		if(activelocales.isEmpty()){
			activelocales.add(localeJapanase);
			activelocales.add(localeEnglish);
			activelocales.add(localeTurkish);
			
		}else{
			logger.info("already inited ");
		}
	}
	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}


	public List<LocaleObject> getActivelocales() {
		return activelocales;
	}

	public void setActivelocales(List<LocaleObject> activelocales) {
		this.activelocales = activelocales;
	}

	public LocaleObject getLocaleJapanase() {
		return localeJapanase;
	}
	public void setLocaleJapanase(LocaleObject localeJapanase) {
		this.localeJapanase = localeJapanase;
	}
	public LocaleObject getLocaleEnglish() {
		return localeEnglish;
	}
	public void setLocaleEnglish(LocaleObject localeEnglish) {
		this.localeEnglish = localeEnglish;
	}
	public LocaleObject getLocaleTurkish() {
		return localeTurkish;
	}
	public void setLocaleTurkish(LocaleObject localeTurkish) {
		this.localeTurkish = localeTurkish;
	}

	public Locale getSelectedLocale() {
		return selectedLocale;
	}
	public void setSelectedLocale(Locale selectedLocale) {
		this.selectedLocale = selectedLocale;
	}
	
	public void changeLocale2(String localeKey){
		if(localeKey!=null){
			for (LocaleObject tempLocaleObject : activelocales) {
				if(tempLocaleObject.getLocaleKey().equalsIgnoreCase(localeKey)){
					logger.info("Old Language :"+FacesContext.getCurrentInstance().getViewRoot().getLocale());
					FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.JAPAN);
					logger.info("language changed to :"+tempLocaleObject.getLocale());
				}
			}
			
			
		}	
	}
	
	// value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {

		String newLocaleValue = e.getNewValue().toString();

		// loop country map to compare the locale code
		for (Iterator<LocaleObject> iterator = activelocales.iterator(); iterator.hasNext();) {
			LocaleObject tempLocaleObject = (LocaleObject) iterator.next();
			if(tempLocaleObject.getLocaleKey().equals(newLocaleValue)){
				FacesContext.getCurrentInstance().getViewRoot().setLocale(tempLocaleObject.getLocale());
				return;
			}
		}
		
	}
	public void changeLocale(String localeKey){

		String newLocaleValue = localeKey;
//		FacesContext context = FacesContext.getCurrentInstance(); 
//		context.getApplication().getDefaultLocale();
//		context.getViewRoot().setLocale(Locale.JAPAN);
		
		// loop country map to compare the locale code
		for (Iterator<LocaleObject> iterator = activelocales.iterator(); iterator.hasNext();) {
			LocaleObject tempLocaleObject = (LocaleObject) iterator.next();
			if(tempLocaleObject.getLocaleKey().equals(newLocaleValue)){
				FacesContext.getCurrentInstance().getViewRoot().setLocale(tempLocaleObject.getLocale());
				setSelectedLocale(tempLocaleObject.getLocale());
				logger.info("Locale changed to "+tempLocaleObject.getLocale());
				return;
			}
		}
		
	}
}