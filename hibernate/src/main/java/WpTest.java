import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.sahin.model.WPPost;
import org.sahin.persistence.WPHomeBean;

@Named
@ConversationScoped
public class WpTest extends WPHomeBean<WPPost> implements Serializable {

	String testStr="strTestt";

	public String getTestStr() {
		return testStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}

	public void ccddd() {
		System.out.println("setInstance: "+getInstance());
		
	}
	
	
}
