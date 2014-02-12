package jsf;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
@Named
@SessionScoped
public class GenericJSFErrorUIBean implements Serializable{
	String summary; // the name of the exception class, usually self-descriptive
String details; // a little more information about the named exception


public GenericJSFErrorUIBean() {}

private static String getFirstStackTrace(Throwable t) {
    if (t == null) {
        return null;
    }
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    t.printStackTrace(pw);
    return sw.toString();
}

public String showErrorString(){

	 FacesContext facesContext = FacesContext.getCurrentInstance();
	 HttpServletRequest request=(HttpServletRequest) facesContext.getExternalContext().getRequest();
   Throwable ex = (Exception) request.getSession().getAttribute("JSF_ERROR");
  if(ex==null)
	  return null;
   String message =ex.getClass().getSimpleName()+"\n";
   message=message+  ex.getLocalizedMessage()+"\n";
   String stack = ex.getStackTrace().toString();
   
   while (ex.getCause() != null) {
       ex = ex.getCause();
       message = ex.getLocalizedMessage();
      
   }
   message=message+"\n"+ stack+"\n";
   details = ex.getMessage();
   message=message+"\n"+ details+"\n";
	return message;
}

}