package org.sahin.home;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.sahin.model.User;
import org.sahin.persistence.HomeBean;

@Named
@ConversationScoped
public class UserHome extends HomeBean<User> {

	public void onPageLoad() {
		clearInstance();
		super.onPageLoad();
	}
}
