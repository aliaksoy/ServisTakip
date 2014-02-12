package org.sahin.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FileOperationsUtil {

	public static StringBuffer createJsonString(List objectList){
	GsonBuilder gsonBuilder = new GsonBuilder();
	//gsonBuilder.registerTypeAdapter(Exception.class, new ExceptionSerializer());
	Gson gson = gsonBuilder.create();

	// convert java object to JSON format,
	// and returned as JSON formatted string
	String json = gson.toJson(objectList);

//	try {
//		// write converted json data to a file named "file.json"
//		FileWriter writer = new FileWriter("d:\\file.json");
//		writer.write(json);
//		writer.close();
//
//	} catch (IOException e) {
//		e.printStackTrace();
//	}

	StringBuffer bf=new StringBuffer();
	bf.append(json);
	return bf;
}
}