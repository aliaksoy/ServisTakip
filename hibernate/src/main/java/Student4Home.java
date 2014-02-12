import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.sahin.persistence.HomeBean;

@Named
@ConversationScoped
public class Student4Home extends HomeBean<Student4> {

	public void saveMe() {
		logger.info("***** starting save");
		logger.info(getInstance());
		save();
		logger.info("***** save succesfull");

	}
}
