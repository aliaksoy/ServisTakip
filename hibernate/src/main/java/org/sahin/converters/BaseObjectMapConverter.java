package org.sahin.converters;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import org.apache.log4j.Logger;

@Named(value = "baseObjectMapConverter")
@SessionScoped
@ManagedBean(name = "baseObjectMapConverter")
@FacesConverter(value = "baseObjectMapConverter")
public class BaseObjectMapConverter<T> implements Converter, Serializable {

	private static final long serialVersionUID = -1549948404056541340L;

	Logger logger=Logger.getLogger(BaseObjectMapConverter.class);
	
	public BaseObjectMapConverter() {}

	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		Object obj=null;
		ConverterHolder converterHolder=	ConverterHolder.instance(context);
		logger.info(converterHolder);
		Map<String, Object> map=converterHolder.getMap(component.getId());
		
		logger.error("getAsObject");
		
		if(map!=null){
			obj=	map.get(value);
		}
		logger.info("map: "+map);
		logger.info("component: "+component.getId());
		logger.info("   value:"+value+" obj: "+obj);
		return obj;
	}

	public String getAsString(FacesContext context, UIComponent component,Object value) {
		ConverterHolder converterHolder=	ConverterHolder.instance(context);
		logger.info(converterHolder);
		Map<String, Object> map=converterHolder.getMap(component.getId());
		String str=""+value.hashCode();
		
		if(map==null)
			map=new HashMap<String, Object>();
		logger.info("map: "+map);
		logger.info("component: "+component.getId());
		map.put(str, value);
		logger.error("getAsString");
		logger.info("   obj:"+value+" str: "+str);
		converterHolder.addMap(component.getId(),map);
		return str;
	}

}
