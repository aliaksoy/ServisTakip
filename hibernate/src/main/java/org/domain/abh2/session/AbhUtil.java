package org.domain.abh2.session;

import java.lang.reflect.Method;
import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@ApplicationScoped
public class AbhUtil {

	public static final Locale TR_LOCALE = new Locale("tr", "TR");
	public static final Locale RU_LOCALE = new Locale("ru", "RU");
	public static final boolean isUTF8 = true;
	private static HttpServletRequest httpServletRequest;

	private static String url;

	private static Date sonSistemTarih;

	// String yeniString=new String(gelenString.getBytes("ISO-8859-1"),"ISO-8859-9");

	public static String convertUTF8(String sonuc) {
		if (sonuc != null) {
			String str = "";// =str.replaceAll("s", "s");
			//sahin

			if (sonuc.indexOf('İ') >= 0) {// I için 1
				sonuc = replaceAll(sonuc, String.valueOf('İ'), "\u0130");
			}
			if (sonuc.indexOf('ı') >= 0) {// i için 2
				sonuc = replaceAll(sonuc, String.valueOf('ı'), "\u0131");
			}
			if (sonuc.indexOf('Ş') >= 0) {// i için 2
				sonuc = replaceAll(sonuc, String.valueOf('Ş'), "\u015E");
			}
			if (sonuc.indexOf('ş') >= 0) {// i için 2
				sonuc = replaceAll(sonuc, String.valueOf('ş'), "\u015F");
			}
			if (sonuc.indexOf('Ğ') >= 0) {// i için 2
				sonuc = replaceAll(sonuc, String.valueOf('Ğ'), "\u011E");
			}
			if (sonuc.indexOf('ğ') >= 0) {// i için 2
				sonuc = replaceAll(sonuc, String.valueOf('ğ'), "\u011F");
			}
		}
		return sonuc;
	}

	public static String encode(String sa) {

		if (sa != null) {
			String sa1 = "";
			int i = 0, j = 0;
			for (i = 0; i < sa.length(); i++) {
				j = (int) sa.charAt(i);
				if (j == 221)
					sa1 = sa1 + "İ";
				else if (j == 253)
					sa1 = sa1 + "ı";
				else if (j == 222)
					sa1 = sa1 + "Ş";
				else if (j == 254)
					sa1 = sa1 + "ş";
				else if (j == 208)
					sa1 = sa1 + "Ğ";
				else if (j == 240)
					sa1 = sa1 + "ğ";
				else
					sa1 = sa1 + sa.charAt(i);
			}
			System.out.println("Önceki hali " + sa + "  Sonraki hali: " + sa1);
			sa = sa1;
		}
		return sa;
	}

	public static List<String> getSayilar(int bas, int son, int artis) {

		List<String> saatListesi = new ArrayList<String>();
		for (int i = bas; i < son; i = i + artis) {
			String saatStr = "" + i;
			if (saatStr.length() == 1)
				saatStr = "0" + saatStr;
			saatListesi.add(saatStr);
		}

		return saatListesi;
	}

	/**
	 * Gelen text'in ba��na maximum text uzunlu�una ula��ncaya dek gelen karakteri ekler.
	 * 
	 * @param txt
	 * @param karakter
	 * @param maxTextLength
	 * @return
	 */
	public static String textBaslangicinaKarakterEkle(String txt, char karakter, int maxTextLength) {
		StringBuffer karakterStrBuf = new StringBuffer(maxTextLength);
		if (txt != null && txt.length() < maxTextLength) {
			for (int i = 0; i < maxTextLength - txt.length(); i++)
				karakterStrBuf.append(karakter);
		}
		return karakterStrBuf.append(txt).toString();
	}

	/**
	 * Gelen text'in sonuna maximum text uzunlu�una ula��ncaya dek gelen karakteri ekler.
	 * 
	 * @param txt
	 * @param karakter
	 * @param maxTextLength
	 * @return
	 */
	public static String textSonunaKarakterEkle(String txt, char karakter, int maxTextLength) {
		StringBuffer karakterStrBuf = new StringBuffer(maxTextLength).append(txt);
		if (txt != null && txt.length() < maxTextLength) {
			for (int i = 0; i < maxTextLength - txt.length(); i++)
				karakterStrBuf.append(karakter);
		}
		return karakterStrBuf.toString();
	}

	/**
	 * @param tarih1
	 * @param tarih2
	 * @return
	 */
	public static Long tarihFarki(Date tarih1, Date tarih2) {
		return tarih1 != null && tarih2 != null ? new Long((tarih2.getTime() - tarih1.getTime()) / (1000 * 60 * 60 * 24)) : null;
	}

	/**
	 * java.util.Date objesinden tarihi, SQL sorgusunda kullanilabilecek formata d�n�st�r�r.
	 * 
	 * @param date
	 * @return
	 */
	public static String convertToDateString(Date date, String pattern) {
		FacesContext fc = FacesContext.getCurrentInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, fc.getViewRoot().getLocale());
		return sdf.format(date);
	}

	/**
	 * Verilen tarihe, verilen g�n sayisi eklenir ve yeni olusan java.util.Date objesi d�nd�r�l�r.
	 * 
	 * @param date
	 * @param gunSayisi
	 * @return
	 */
	public static Date tariheGunEkleCikar(Date date, int gunSayisi) {
		return addTarih(date, Calendar.DATE, gunSayisi);

	}

	/**
	 * Verilen tarihe, verilen ay sayisi eklenir ve yeni olusan java.util.Date objesi d�nd�r�l�r.
	 * 
	 * @param date
	 * @param aySayisi
	 * @return
	 */
	public static Date tariheAyEkleCikar(Date date, int aySayisi) {
		return addTarih(date, Calendar.MONTH, aySayisi);
	}

	/**
	 * Verilen tarih stringini belirtilen paterne g�re java.util.Date objesine donusturur.
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static java.util.Date convertToJavaDate(String dateStr, String pattern) {
		FacesContext fc = FacesContext.getCurrentInstance();
		java.util.Date date = null;
		try {
			if (dateStr != null && pattern != null)
				date = new SimpleDateFormat(pattern, fc.getViewRoot().getLocale()).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Girilen float de�eri parse eder.
	 * 
	 * @return
	 */
	public static double parseDouble(String val) {
		double parsedValue = 0.0;
		try {
			if ((val != null) && (val.length() > 0)) {
				parsedValue = Double.parseDouble(val);

				if (parsedValue < 0) {
					parsedValue = 0;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return parsedValue;
	}

	/**
	 * Verilen String objesinin numerik de�erden olu�up olu�mad���n� kontrol eder. request parametresi olarak al�nan id de�erlerinin kontrol�nde kullan�l�r.
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNumeric(String val) {
		try {
			Integer.parseInt(val);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Verilen String objesinin decimal de�er olup olmad��� kontrol edilir.
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isDecimal(String val) {
		try {
			Double.parseDouble(val);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;

	}

	/**
	 * Date tipinde verilen iki tarih de�erini alarak y�l,ay ve g�n baz�nda e�it olup olmad�klar�n� kontrol eder.
	 * 
	 * @param tarih1
	 * @param tarih2
	 * @return iki de�eri yil/ay/g�n baz�nda e�itse true, de�ilse false d�ner.
	 */
	public static boolean tarihKarsilastir(Date tarih1, Date tarih2) {
		Calendar tarihCal1 = Calendar.getInstance();
		Calendar tarihCal2 = Calendar.getInstance();
		tarihCal1.setTime(tarih1);
		tarihCal2.setTime(tarih2);
		if (tarihCal1.get(Calendar.YEAR) == tarihCal2.get(Calendar.YEAR)) {
			if (tarihCal1.get(Calendar.MONTH) == tarihCal2.get(Calendar.MONTH)) {
				if (tarihCal1.get(Calendar.DATE) == tarihCal2.get(Calendar.DATE)) {
					return true;
				}
			}
		}
		return false;
	}

	public static int tarihKarsilastirNumeric(Date tarih1, Date tarih2) {
		long sayi1 = Long.parseLong(convertToDateString(tarih1, "yyyyMMdd"));
		long sayi2 = Long.parseLong(convertToDateString(tarih2, "yyyyMMdd"));
		int sonuc = 0;
		if (sayi1 > sayi2)
			sonuc = 1;
		else if (sayi1 < sayi2)
			sonuc = -1;
		return sonuc;
	}

	public static Date setTarih(Date tarih, int field, int value) {
		if (tarih != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(tarih);
			try {
				cal.set(field, value);
				tarih = cal.getTime();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return tarih;
	}

	public static Date addTarih(Date tarih, int field, int value) {
		if (tarih != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(tarih);
			try {
				cal.add(field, value);
				tarih = cal.getTime();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return tarih;
	}

	public static Date buGun() {

		Calendar date = Calendar.getInstance();
		date.clear(Calendar.MILLISECOND);
		date.clear(Calendar.SECOND);
		date.clear(Calendar.MINUTE);
		date.clear(Calendar.HOUR_OF_DAY);
		date.clear(Calendar.HOUR);
		return date.getTime();
	}

	public static String getStringKes(String str, int uzunluk) {
		if (str == null)
			str = "";
		return str != null && str.length() > uzunluk ? str.substring(0, uzunluk) : str;
	}

	/**
	 * Verilen para de�erini(string) double de�ere d�n��t�r�r.
	 * 
	 * @param value
	 * double'a d�n��t�r�lmesi istenen string de�eri
	 * @return
	 */
	public static double parseCurrency(String value) {
		String parsedValue = "";
		char comma = ',';

		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != comma)
				parsedValue += value.charAt(i);
		return parseDouble(parsedValue);
	}

	/**
	 * Text alan�n null veya empty string olup olmad���n� kontrol eder.
	 * 
	 * @param value
	 * @return null veya empty ise false, aksi halde true d�ner
	 */
	public static boolean textAlanGecerliMi(String value) {
		if (value == null || value.trim().length() <= 0)
			return false;
		return true;
	}

	/**
	 * T�rk�e s�ralama i�in karakter kural� tan�mlanmaktad�r.
	 * 
	 * @return
	 */
	public static RuleBasedCollator getTrRuleBasedCollator() {
		RuleBasedCollator tr_Collator = (RuleBasedCollator) Collator.getInstance(new Locale("tr", "TR"));
		try {
			tr_Collator = new RuleBasedCollator("<a,A<b,B<c,C<ç,Ç<d,D<e,E<f,F<g,G<\u011f,\u011e<ğ,Ğ<h,H<ı=\u0131;I<i,\u0130=İ<j,J"
					+ "<k,K<l,L<m,M<n,N<o,O<ö,Ö<p,P<q,Q<r,R<s,S<\u015f=ş;\u015e=Ş<t,T<u,U<ü,Ü<v,V<x,X<w,W<y,Y<z,Z<'-'<' '");

		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		tr_Collator.setStrength(Collator.SECONDARY | Collator.CANONICAL_DECOMPOSITION);//
		tr_Collator.setStrength(Collator.TERTIARY);
		return tr_Collator;

	}

	/**
	 * Ru��a s�ralama i�in karakter kural� tan�mlanmaktad�r.
	 * 
	 * @return
	 */
	public static RuleBasedCollator getRuRuleBasedCollator() {
		RuleBasedCollator collator = (RuleBasedCollator) Collator.getInstance(Constants.RU_LOCALE);
		try {
			String ruHarf = "< \u0430=?; \u0410=?" + "< \u0431=?; \u0411=?" + "< \u0432=?; \u0412=?" + "< \u0433=?; \u0413=?" + "< \u0434=?; \u0414=?" + "< \u0435=?; \u0415=?"
					+ "< \u0451=?; \u0401=?" + "< \u0436=?; \u0416=?" + "< \u0437=?; \u0417=?" + "< \u0438=?; \u0418=?" + "< \u0439=?; \u0419=?" + "< \u043A=?; \u041A=?" + "< \u043B=?; \u041B=?"
					+ "< \u043C=?; \u041C=?" + "< \u043D=?; \u041D=?" + "< \u043E=?; \u041E=?" + "< \u043F=?; \u041F=?" + "< \u0440=?; \u0420=?" + "< \u0441=?; \u0421=?" + "< \u0442=?; \u0422=?"
					+ "< \u0443=?; \u0423=?" + "< \u0444=?; \u0424=?" + "< \u0445=?; \u0425=?" + "< \u0446=?; \u0426=?" + "< \u0447=?; \u0427=?" + "< \u0448=?; \u0428=?" + "< \u0449=?; \u0429=?"
					+ "< \u044A=?; \u042A=?" + "< \u044B=?; \u042B=?" + "< \u044C=?; \u042C=?" + "< \u044D=?; \u042D=?" + "< \u044E=?; \u042E=?" + "< \u044F=?; \u042F=?" + "<'-'<' '";
			collator = new RuleBasedCollator(ruHarf);

		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		collator.setStrength(Collator.SECONDARY | Collator.CANONICAL_DECOMPOSITION);//
		collator.setStrength(Collator.TERTIARY);
		return collator;

	}

	/**
	 * @param deger
	 * @param ayrac
	 * @return
	 */
	public static ArrayList<String> parseCharToArrayList(String deger, char ayrac) {
		ArrayList<String> list = new ArrayList<String>();
		deger = deger.trim();
		char charArray[] = new char[deger.length()];
		deger.getChars(0, deger.length(), charArray, 0);
		for (int i = 0; i < charArray.length; i++) {
			if (ayrac != charArray[i]) {
				list.add(String.valueOf(charArray[i]));
			}
		}

		return list;
	}

	/**
	 * List objesini objenin istenilen alan�na g�re s�ralar
	 * 
	 * @param izinHakedisHakkiList
	 * @param alanAdi
	 * @param buyukdenKucuge
	 * @return
	 */
	public static List sortListByAlanAdi(List list, String alanAdi, boolean buyukdenKucuge) {
		ArrayList yeniList = new ArrayList();
		if (list != null && !list.isEmpty())
			for (Object object : list)
				if (object != null)
					yeniList.add(object);
		try {
			if (yeniList.size() > 1) {
				Comparator<Object> comparator = new BeanPropertyComparator(alanAdi);
				Collections.sort(yeniList, comparator);
				if (buyukdenKucuge)
					Collections.reverse(yeniList);
			}

		} catch (Exception e) {
			yeniList = (ArrayList) ((ArrayList) list).clone();
			e.printStackTrace();
		}

		return yeniList;
	}

	public static ArrayList getSayfalaList(String sonEk, ArrayList veriList, int satirSayisi, HttpServletRequest request) {
		if (sonEk == null)
			sonEk = "";
		int sayac = 0;
		List sayfaList = new ArrayList();
		while (veriList.size() > sayac) {
			sayac += satirSayisi;
			sayfaList.add(String.valueOf(sayac / satirSayisi));
		}
		request.setAttribute("sayfaList" + sonEk, sayfaList);
		ArrayList list = (ArrayList) veriList.clone();
		veriList.clear();
		int sayfaNo = 0;
		if (request.getParameter("sayfaNo" + sonEk) != null && !request.getParameter("sayfaNo" + sonEk).trim().equals(""))
			sayfaNo = Integer.parseInt(request.getParameter("sayfaNo" + sonEk)) - 1;

		request.setAttribute("sayfaNo" + sonEk, String.valueOf(sayfaNo + 1));
		if (sayfaList.size() == sayfaNo)
			--sayfaNo;
		int ilkIndis = ((satirSayisi * sayfaNo) + 1);
		int sonIndis = (satirSayisi * (sayfaNo + 1));
		if (sonIndis > list.size())
			sonIndis = list.size();
		for (int i = ilkIndis - 1; i < sonIndis; i++) {
			Object element = list.get(i);
			veriList.add(element);

		}
		request.setAttribute("sayfaStr" + sonEk, String.valueOf(sayfaNo + 1) + " ( " + ilkIndis + " - " + sonIndis + " )");
		return veriList;

	}

	public static boolean isValidEmail(String str) {
		int bolum1 = str.indexOf("@");
		boolean sonuc = bolum1 > 0 && str.lastIndexOf("@") == bolum1 && str.lastIndexOf(".") > bolum1 + 1 && str.charAt(bolum1 - 1) != '.' && str.indexOf(".") > 0
				&& str.lastIndexOf(".") < str.length() - 1;
		return sonuc;
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

	/**
	 * @param object
	 * @param method
	 * @param parametre
	 * @return
	 */
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

	/**
	 * @param object
	 * @param method
	 * @param parametre
	 */
	public static void runMethodObject(Object object, String method, Object[] parametre) {
		Class[] classes = null;
		if (parametre != null) {
			classes = new Class[parametre.length];
			for (int i = 0; i < classes.length; i++)
				classes[i] = parametre[i].getClass();
		}
		try {
			Method run = object.getClass().getMethod(method, classes);
			run.invoke(object, parametre);
		} catch (Exception e) {
			System.err.println(e.getMessage());

		}
	}

	public static List sortObjectStringAlanList(List list, String method, Object[] parametre) {
		List sortList = sortObjectStringAlanList(FacesContext.getCurrentInstance().getViewRoot().getLocale(), list, method, parametre);
		return sortList;
	}

	/**
	 * @param locale
	 * @param list
	 * @param method
	 * @param parametre
	 * @return
	 */
	public static List sortObjectStringAlanList(Locale locale, List list, String method, Object[] parametre) {
		if (list != null && list.size() > 1) {
			if (locale == null)
				locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			TreeMap sortMap = new TreeMap();
			String alan1 = "";
			String alan2 = "";
			HashMap degerMap = new HashMap();
			for (int k = 0; k < list.size(); k++) {
				sortMap.put(new Long(k), list.get(k));
				degerMap.put(new Long(k), getMethodObject(list.get(k), method, parametre));
			}
			list = new ArrayList(sortMap.values());
			RuleBasedCollator collator = null;
			if (locale.getLanguage().equals(Constants.TR_LOCALE.getLanguage()))
				collator = getTrRuleBasedCollator();
			else if (locale.getLanguage().equals(Constants.RU_LOCALE.getLanguage()))
				collator = getRuRuleBasedCollator();
			Object object1 = null;
			Object object2 = null;
			if (collator != null) {
				for (int j = 0; j < list.size() - 1; j++) {
					object1 = sortMap.get(new Long(j));
					alan1 = (String) degerMap.get(new Long(j));
					for (int k = j + 1; k < list.size(); k++) {
						object2 = sortMap.get(new Long(k));
						alan2 = (String) degerMap.get(new Long(k));
						if (alan1 != null && alan2 != null && collator.compare(alan1, alan2) == 1) {
							sortMap.put(new Long(j), object2);
							sortMap.put(new Long(k), object1);
							degerMap.put(new Long(j), alan2);
							degerMap.put(new Long(k), alan1);
							alan1 = alan2;
							object1 = object2;
						}
					}
				}
			} else {
				Collator coll = Collator.getInstance(locale);
				for (int j = 0; j < list.size() - 1; j++) {
					object1 = sortMap.get(new Long(j));
					alan1 = (String) degerMap.get(new Long(j));
					for (int k = j + 1; k < list.size(); k++) {
						object2 = sortMap.get(new Long(k));
						alan2 = (String) degerMap.get(new Long(k));
						if (alan1 != null && alan2 != null && coll.compare(alan1, alan2) == 1) {
							sortMap.put(new Long(j), object2);
							sortMap.put(new Long(k), object1);
							degerMap.put(new Long(j), alan2);
							degerMap.put(new Long(k), alan1);
							alan1 = alan2;
							object1 = object2;
						}
					}
				}
			}
			list = new ArrayList(sortMap.values());
		}
		return list;
	}

	/**
	 * Tanim obje listesi s�ralanmas� yap�lmaktad�r.
	 * 
	 * @param locale
	 * @param list
	 * @return
	 */
	public static List sortTanimList(Locale locale, List list) {
		if (locale == null)
			locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		if (list != null && list.size() > 0)
			list = sortObjectStringAlanList(locale, list, "getAciklama", null);
		return list;

	}

	public static ArrayList getArrayList(String[] dizi, String ayrac) {
		ArrayList arrayList = null;
		if (dizi != null && dizi.length > 0) {
			arrayList = new ArrayList();
			for (int i = 0; i < dizi.length; i++)
				arrayList.add(ayrac + dizi[i].trim() + ayrac);
		}
		return arrayList;
	}

	public static ArrayList getNumericArrayList(String[] dizi) {
		return getArrayList(dizi, "");
	}

	public static ArrayList getStringArrayList(String[] dizi) {
		return getArrayList(dizi, "'");
	}

	public static StringBuffer getObjectXmlString(Object object) {
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"ISO-8859-9\"?>");
		xml.append(getObjectXml(object));
		return xml;
	}

	public static StringBuffer getObjectXml(Object deger, String fieldAdi) {
		StringBuffer xml = new StringBuffer();
		if (deger instanceof String)
			xml.append("<" + fieldAdi + ">" + deger + "</" + fieldAdi + ">");
		else if (deger instanceof Integer)
			xml.append("<" + fieldAdi + ">" + ((Integer) deger).intValue() + "</" + fieldAdi + ">");
		else if (deger instanceof Double)
			xml.append("<" + fieldAdi + ">" + ((Double) deger).doubleValue() + "</" + fieldAdi + ">");
		else if (deger instanceof Long)
			xml.append("<" + fieldAdi + ">" + ((Long) deger).longValue() + "</" + fieldAdi + ">");
		else if (deger instanceof Float)
			xml.append("<" + fieldAdi + ">" + ((Float) deger).floatValue() + "</" + fieldAdi + ">");
		else if (deger instanceof Boolean)
			xml.append("<" + fieldAdi + ">" + ((Boolean) deger).booleanValue() + "</" + fieldAdi + ">");
		else {
			StringBuffer xml1 = getObjectXml(deger);
			if (xml1 != null && xml1.length() > 0)
				xml.append(xml1.toString());
		}
		return xml;
	}

	public static StringBuffer getObjectXml(Object object) {
		String objectAdi = object.getClass().getName();
		if (objectAdi.indexOf("java.lang.Class") == 0)
			return null;
		System.err.println(objectAdi);
		StringBuffer xml = new StringBuffer();
		objectAdi = objectAdi.substring(objectAdi.lastIndexOf(".") + 1);
		objectAdi = objectAdi.substring(0, 1).toLowerCase(Locale.ENGLISH) + objectAdi.substring(1);
		xml.append("<" + objectAdi + ">");
		Class[] class1 = null;
		if (object != null) {
			StringBuffer xml1 = null;
			Method[] methods = object.getClass().getMethods();
			if (methods != null && methods.length > 0) {
				for (int i = 0; i < methods.length; i++) {
					Method method = methods[i];
					String methodAdi = method.getName();
					class1 = method.getParameterTypes();
					if (methodAdi.indexOf("get") == 0 && (class1 == null || class1.length == 0)) {
						Object deger = getMethodObject(object, methodAdi, null);
						String fieldAdi = methodAdi.substring(3, 4).toLowerCase(Locale.ENGLISH) + methodAdi.substring(4);
						xml1 = null;
						if (deger == null) {
							xml.append("<" + fieldAdi + "></" + fieldAdi + ">");
							continue;
						}
						if (deger instanceof Object[]) {
							for (int j = 0; j < ((Object[]) deger).length; j++) {
								Object arrayItem = ((Object[]) deger)[j];
								fieldAdi = arrayItem.getClass().getName();
								fieldAdi = fieldAdi.substring(fieldAdi.lastIndexOf(".") + 1);
								fieldAdi = fieldAdi.substring(0, 1).toLowerCase(Locale.ENGLISH) + fieldAdi.substring(1);
								xml1 = getObjectXml(arrayItem, fieldAdi);
								if (xml1 != null && xml1.length() > 0)
									xml.append(xml1.toString());
							}
						} else {
							if (deger instanceof Map || deger instanceof HashMap || deger instanceof TreeMap)
								deger = new ArrayList(((Map) deger).values());
							if (deger instanceof List || deger instanceof ArrayList) {
								List list = (List) deger;
								for (Iterator iter = list.iterator(); iter.hasNext();) {
									Object arrayItem = iter.next();
									fieldAdi = arrayItem.getClass().getName();
									fieldAdi = fieldAdi.substring(fieldAdi.lastIndexOf(".") + 1);
									fieldAdi = fieldAdi.substring(0, 1).toLowerCase(Locale.ENGLISH) + fieldAdi.substring(1);
									xml1 = getObjectXml(arrayItem, fieldAdi);
									if (xml1 != null && xml1.length() > 0)
										xml.append(xml1.toString());
								}
							} else {
								xml1 = getObjectXml(deger, fieldAdi);
								if (xml1 != null && xml1.length() > 0)
									xml.append(xml1.toString());
							}

						}

					}
				}
			}

		}
		xml.append("</" + objectAdi + ">");
		return xml;
	}

	public static String getKullaniciCookie(HttpServletRequest request) {
		String kullaniciAdi = "";
		javax.servlet.http.Cookie cookies[] = request.getCookies();
		javax.servlet.http.Cookie username = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(Constants.COOKIE_KULLANICI_ADI)) {
					username = cookies[i];
					kullaniciAdi = username.getValue();
					break;
				}
			}
		}
		return kullaniciAdi;
	}

	public static void removeSession(HttpSession session, String name) {
		if (session.getAttribute(name) != null)
			session.removeAttribute(name);
	}

	public static void mesajYaz(String mesaj) {
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setDetail(mesaj);
		facesMessage.setSummary(mesaj);
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public static HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public static void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		AbhUtil.httpServletRequest = httpServletRequest;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		AbhUtil.url = url;
	}

	public static String encodePassword(String str) {
		//		String lockToken = "";
		//		try {
		//			MD5Encoder md5Encoder = new MD5Encoder();
		//			MessageDigest md5Helper = MessageDigest.getInstance("MD5");
		//			lockToken = md5Encoder.encode(md5Helper.digest(str.getBytes()));
		//		} catch (NoSuchAlgorithmException e) {
		//			// TODO Auto-generated catch block
		//			lockToken = str;
		//			System.err.println("encodePassword : " + e.getMessage());
		//		}
		//		return lockToken;
		return str;

	}

	public static Date getSonSistemTarih() {
		if (sonSistemTarih == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(2999, 11, 31);
			sonSistemTarih = calendar.getTime();
		}
		return sonSistemTarih;

	}

	public static void setSonSistemTarih(Date sonSistemTarih) {
		AbhUtil.sonSistemTarih = sonSistemTarih;
	}

	public static String getMessageBundleMessage(Locale locale, String key) {
		String message = null;
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			String bundleName = ctx.getApplication().getMessageBundle();
			if (locale == null)
				locale = ctx.getViewRoot().getLocale();
			ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
			message = bundle.getString(key);
		} catch (Exception e) {
			message = key + " ???";
		}
		return message;

	}

	public static String getMessageBundleMessage(String key) {
		return getMessageBundleMessage(null, key);
	}

}
