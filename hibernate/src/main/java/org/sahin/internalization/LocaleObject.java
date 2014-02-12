package org.sahin.internalization;

import java.util.Locale;

public class LocaleObject {

	Locale locale;
	String localeKey;
	String langNameKeyInMessages;
	String countyrNameKeyInMessages;
	String imagePath;
	public LocaleObject(Locale locale,String localeKey, String langNameKeyInMessages,String countyrNameKeyInMessages, String imagePath) {
		super();
		this.locale = locale;
		this.localeKey = localeKey;
		this.langNameKeyInMessages = langNameKeyInMessages;
		this.countyrNameKeyInMessages = countyrNameKeyInMessages;
		this.imagePath = imagePath;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getLangNameKeyInMessages() {
		return langNameKeyInMessages;
	}
	public void setLangNameKeyInMessages(String langNameKeyInMessages) {
		this.langNameKeyInMessages = langNameKeyInMessages;
	}
	public String getCountyrNameKeyInMessages() {
		return countyrNameKeyInMessages;
	}
	public void setCountyrNameKeyInMessages(String countyrNameKeyInMessages) {
		this.countyrNameKeyInMessages = countyrNameKeyInMessages;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getLocaleKey() {
		return localeKey;
	}
	public void setLocaleKey(String localeKey) {
		this.localeKey = localeKey;
	}
	
	
}
