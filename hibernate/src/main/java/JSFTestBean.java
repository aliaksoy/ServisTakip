import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class JSFTestBean implements Serializable {
String testStr="ko";

public String getTestStr() {
	return testStr;
}

public void setTestStr(String testStr) {
	this.testStr = testStr;
}


}
