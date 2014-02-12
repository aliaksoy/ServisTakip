package org.sahin.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.sahin.model.BaseEntity;

/**
 * Entity Manager Dao that
 * 
 * @author Sahin AYDIN
 * 
 */
public class EntityManagerDao implements Serializable {
	public static final int LIST_MAX_SIZE = 300;
	@Inject
	@DataRepository
	private EntityManager entityManager;

	public Object updateObject(Object object) {
		entityManager.getTransaction().begin();
		object = entityManager.merge(object);
		entityManager.getTransaction().commit();
		return object;
	}

	public void createObject(Object object) {
		entityManager.getTransaction().begin();
		entityManager.persist(object);
		entityManager.getTransaction().commit();
	}

	public List<Object> saveAll(List<Object> objectList) {
		entityManager.getTransaction().begin();
		for (Object object : objectList) {
			try {
				entityManager.persist(object);
			} catch (Exception e) {
				object = entityManager.merge(object);

			}
		}
		entityManager.getTransaction().commit();
		return objectList;
	}

	public void refresh(Object object) {
		entityManager.refresh(object);
	}

	public <T> T find(Class<T> clazz, Long id) {
		return entityManager.find(clazz, id);
	}

	public <T> List findAll(Class<T> clazz) {
		return getObjectByInnerObjectList(new HashMap<String, Object>(), clazz);
	}

	public void deleteObject(Object object) {
		entityManager.getTransaction().begin();
		entityManager.remove(object);
		entityManager.getTransaction().commit();

	}

	private String getStringParse(Object fieldValue) {
		String str = "";
		if (fieldValue instanceof List || fieldValue instanceof ArrayList) {
			for (Iterator iter = ((List) fieldValue).iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				str += " c." + element + (iter.hasNext() ? "," : "");
			}
		} else if (fieldValue instanceof String) {
			StringTokenizer st = new StringTokenizer((String) fieldValue, ",");
			while (st.hasMoreTokens())
				str += " c." + (String) st.nextToken() + (st.hasMoreTokens() ? "," : "");
		}
		return str;
	}

	private List getObjectByInnerObjectList(HashMap fields, Class class1, String esit) {
		return getObjectByInnerObjectList(fields, class1, esit, null);

	}

	public List getObjectByInnerObjectList(HashMap fields, Class class1, String esit, EntityManager pEntityManager) {
		if (pEntityManager == null)
			pEntityManager = entityManager;
		if (esit == null)
			esit = "";
		else if (esit.equals(""))
			esit = "=";
		int parametreSayac = 1;
		Object fieldValue;
		List list = null;
		if (class1 != null) {
			ArrayList parametreList = new ArrayList();
			String query = "";
			String order = "";
			int maxLimit = 0;
			String select = "";
			String or = null;
			if (!fields.isEmpty()) {
				for (Iterator iter = fields.keySet().iterator(); iter.hasNext();) {
					String fieldName = (String) iter.next();
					fieldValue = fields.get(fieldName);
					if (fieldValue == null) {
						query += (query.trim().length() > 0 ? " and" : " where") + " c." + fieldName + " " + esit + " null";
						continue;
					}
					String str = esit + " null";
					if (fieldName.toLowerCase().trim().equals("or"))
						or = " or ";
					else if (fieldName.toLowerCase().trim().equals("select")) {
						fieldValue = fields.get(fieldName);
						select = getStringParse(fieldValue);
					} else if (fieldName.toLowerCase().trim().equals("order") || fieldName.toLowerCase().trim().equals("limit")) {

						if (fieldName.toLowerCase().trim().equals("order")) {
							fieldValue = fields.get(fieldName);
							order = " order by " + getStringParse(fieldValue);
						} else {

							fieldValue = fields.get(fieldName);
							maxLimit = Integer.parseInt("" + fieldValue);
						}
					} else {
						if (fieldValue != null) {
							if (fieldValue instanceof List) {
								if (((List) fieldValue).isEmpty())
									continue;
								str = "";
								for (Iterator iter1 = ((List) fieldValue).iterator(); iter1.hasNext();) {
									Object fieldListValue = iter1.next();
									str += "?" + (parametreSayac++) + (iter1.hasNext() ? "," : "");
									parametreList.add(fieldListValue);
								}
								str = str.indexOf(",") > 0 ? "in (" + str + ")" : "= " + str;

							} else {
								str = esit + "?" + (parametreSayac++);
								parametreList.add(fieldValue);
							}
						}
						query += (query.trim().length() > 0 ? " and" : " where") + " c." + fieldName + " " + str;
					}
				}
			}
			query = "select " + (select.trim().length() == 0 ? " c " : select.trim()) + " from " + class1.getName() + " c " + query.trim() + order;
			if (query.indexOf(" and ") > 0 && or != null)
				query = replaceAll(query, " and ", or);
			Query qry = null;

			if (pEntityManager == null)
				qry = entityManager.createQuery(query);
			else
				qry = pEntityManager.createQuery(query);
			if (maxLimit != 0)
				qry.setMaxResults(maxLimit);
			parametreSayac = 1;
			for (Object parametre : parametreList)
				qry.setParameter(parametreSayac++, parametre);
			list = qry.getResultList();

			/*
			 * try { list = qry.getResultList();
			 * 
			 * } catch (Exception e) { AbhUtil.mesajYaz(query + "\n" +
			 * e.getMessage());
			 * 
			 * }
			 */

		}
		return list;

	}

	public static String replaceAll(String str, String pattern, String replace) {
		StringBuffer lSb = new StringBuffer();
		if ((str != null) && (pattern != null) && (pattern.length() > 0) && (replace != null)) {
			int i = 0;
			int j = str.indexOf(pattern, i);
			int l = pattern.length();
			int m = str.length();
			if (j > -1) {
				while (j > -1) {
					if (i != j) {
						lSb.append(str.substring(i, j));
					}
					lSb.append(replace);
					i = j + l;
					j = (i > m) ? -1 : str.indexOf(pattern, i);
				}
				if (i < m) {
					lSb.append(str.substring(i));
				}
			} else {
				lSb.append(str);
			}
		}
		return lSb.toString();
	}

	public List getObjectInnerNameList(String fieldName, List list, Class class1) {
		List liste = new ArrayList();
		String str = "";
		int i = 0;
		if (!list.isEmpty()) {

			int uz = list.size() > LIST_MAX_SIZE ? LIST_MAX_SIZE : list.size();

			for (int j = 0; j < uz; j++) {
				str += (!str.equals("") ? "," + "?" + (j + 1) : "?1");
			}

			String query = "from " + class1.getName() + " t where t." + fieldName + " " + (str.indexOf(",") > 0 ? "in (" + str + ")" : "=" + str);
			Query createdQuery = entityManager.createQuery(query);

			for (int j = 0; j < uz; j++) {
				String id = "" + list.get(j);
				createdQuery.setParameter(j + 1, id);
			}

			i = 0;
			str = "";
			List list1 = createdQuery.getResultList();
			if (list1 != null && !list1.isEmpty())
				liste.addAll(list1);

		}
		return liste;
	}

	/**
	 * @param fields
	 * @param class1
	 * @param esit
	 * @return
	 */
	public String update(ArrayList saveList) {
		for (Iterator iterator = saveList.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			updateEntity(object);
		}
		return "ok";
	}

	public String saveList(List<Object> saveList) {
		for (Iterator iterator = saveList.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			try {
				saveEntity(object);
			} catch (Exception e) {
				updateEntity(object);
			}

		}
		return "ok";
	}

	public BaseEntity save(BaseEntity baseEntity) {

		if (baseEntity.getId() == null)
			saveEntity(baseEntity);
		else
			baseEntity = (BaseEntity) updateEntity(baseEntity);

		return baseEntity;
	}

	public Object saveEntity(Object object) {
		entityManager.persist(object);

		return object;
	}

	public Object updateEntity(Object object) {
		Object object2 = entityManager.merge(object);

		return object2;
	}

	public String save(ArrayList saveList) {
		for (Iterator iterator = saveList.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			try {
				saveEntity(object);
			} catch (Exception e) {
				updateEntity(object);
			}

		}
		return "ok";
	}

	public String saveObject(Object object) {

		try {

			entityManager.persist(object);
			entityManager.flush();
			System.out.println("object" + "flushed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entityManager.merge(object);
			entityManager.flush();
		}

		return "ok";
	}

	public String remove(ArrayList saveList) {
		for (Iterator iterator = saveList.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			entityManager.remove(object);
			entityManager.flush();
		}
		return "ok";
	}

	public Object getObjectByInnerObject(HashMap fields, Class class1, String esit) {
		ArrayList<Object> tempObjectList = (ArrayList<Object>) getObjectByInnerObjectList(fields, class1, esit);
		return (tempObjectList != null && !tempObjectList.isEmpty() ? tempObjectList.get(0) : null);
	}

	public List getObjectByInnerObjectListInLogic(HashMap fields, Class class1) {
		return getObjectByInnerObjectList(fields, class1, null);
	}

	public List getObjectByInnerObjectList(HashMap fields, Class class1) {
		return getObjectByInnerObjectList(fields, class1, "");

	}

	public Object getObjectByInnerObject(String fieldName, Object fieldValue, Class class1) {
		List list = getObjectByInnerObjectList(fieldName, fieldValue, class1);
		return (list != null && !list.isEmpty() ? list.get(0) : null);
	}

	public Object getObjectByInnerObject(HashMap fields, Class class1) {
		List list = getObjectByInnerObjectList(fields, class1);
		return (list != null && !list.isEmpty() ? list.get(0) : null);

	}

	public List getObjectByInnerObjectList(String fieldName, Object fieldValue, Class class1) {
		List list = null;
		if (class1 != null) {
			if (!(fieldValue instanceof List) && !(fieldValue instanceof ArrayList)) {
				HashMap map = new HashMap();
				map.put(fieldName, fieldValue);
				list = getObjectByInnerObjectList(map, class1);
			} else
				list = getObjectInnerNameList(fieldName, (List) fieldValue, class1);

		}
		return list;
	}

	public TreeMap getObjectByInnerObjectMap(String method, String fieldName, Object fieldValue, Class class1, boolean uzerineYaz) {
		TreeMap treeMap = new TreeMap();
		List list = getObjectByInnerObjectList(fieldName, fieldValue, class1);
		treeMap = getTreeMapByList(list, method, uzerineYaz);
		return treeMap;
	}

	public TreeMap getObjectByInnerObjectMap(HashMap map, Class class1, boolean uzerineYaz) {
		TreeMap treeMap = new TreeMap();
		if (map.containsKey("Map")) {
			String method = (String) map.get("Map");
			if (method != null && method.trim().length() > 0) {
				map.remove("Map");
				List list = getObjectByInnerObjectList(map, class1);
				if (!list.isEmpty())
					treeMap = getTreeMapByList(list, method, uzerineYaz);
			}

		}
		return treeMap;
	}

	public TreeMap getObjectByInnerObjectMapInLogic(HashMap map, Class class1, boolean uzerineYaz) {
		TreeMap treeMap = new TreeMap();
		if (map.containsKey("Map")) {
			String method = (String) map.get("Map");
			if (method != null && method.trim().length() > 0) {
				map.remove("Map");
				List list = getObjectByInnerObjectListInLogic(map, class1);
				if (!list.isEmpty())
					treeMap = getTreeMapByList(list, method, uzerineYaz);
			}

		}

		return treeMap;
	}

	private TreeMap getTreeMapByList(List list, String method, boolean uzerineYaz) {
		TreeMap treeMap = new TreeMap();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object object = (Object) iter.next();
			try {
				Object key = SahinUtil.getMethodObject(object, method, null);
				if (key != null)
					if (uzerineYaz || !treeMap.containsKey(key))
						treeMap.put(key, object);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return treeMap;
	}

}
