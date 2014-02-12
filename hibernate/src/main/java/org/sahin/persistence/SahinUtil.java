package org.sahin.persistence;

import java.lang.reflect.Method;

public class SahinUtil {

	public static Object getMethodObject(Object object, String method, Object[] parametre) {
		Object str = null;
		Class[] classes = null;
		if (parametre != null) {
			classes = new Class[parametre.length];
			for (int i = 0; i < classes.length; i++)
				classes[i] = parametre[i].getClass();
		}
		try {
			Method run = object.getClass().getMethod(method, classes);
			str = run.invoke(object, parametre);
		} catch (Exception e) {
			str = null;
		}
		return str;
	}

}
