package org.sahin.converters;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import org.sahin.model.BaseEntity;
import org.sahin.repository.StaticRepo;

@Named(value = "baseObjectConverter")
@SessionScoped
@ManagedBean(name = "baseObjectConverter")
@FacesConverter(value = "baseObjectConverter")
public class BaseObjectConverter<T> implements Converter, Serializable {

	private static final long serialVersionUID = -1549948404056541340L;

	public BaseObjectConverter() {}

	public Object getAsObject(FacesContext context, UIComponent component,String value) {

		Object obj = null;
		Class<?> className = null;
		Long id = null;
		String values[] = value.split(":");
		try {
			className = Class.forName(values[0]);
			id = Long.valueOf(values[1]);
			obj = StaticRepo.entityManager.find(className, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public String getAsString(FacesContext context, UIComponent component,Object value) {
		String str = null;
		if (value != null) {
			str = String.valueOf("" + value.getClass().getCanonicalName() + ":"
					+ ((BaseEntity) (value)).getId());
		}
		return str;
	}

}
