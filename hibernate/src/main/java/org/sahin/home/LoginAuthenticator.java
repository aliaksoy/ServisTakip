package org.sahin.home;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.sahin.model.User;
import org.sahin.persistence.EntityManagerDao;

@Named
@SessionScoped
public class LoginAuthenticator implements Serializable {

	private static final long serialVersionUID = 1L;

	private User authenticatedUser;
	private String username;
	private String password;
	private String password2;
	@Inject
	protected EntityManagerDao entityManagerDao;
	Logger logger = Logger.getLogger(LoginAuthenticator.class);
    String homePageFm="fmHome.xhtml?faces-redirect=true";
    String homePageRecipe="recipe.xhtml?faces-redirect=true";
	public String login() {
		checkPassword();
		if(authenticatedUser==null)
			return null;
		else
			return homePageRecipe;
			
	}
	public String loginFm() {
		checkPassword();
		if(authenticatedUser==null)
			return null;
		else
			return homePageFm;
			
	}
	public User checkPassword() {
		if (username != null && password != null) {

			HashMap fields = new HashMap();
			fields.put("username", username);
			fields.put("password", password);
			authenticatedUser = (User) entityManagerDao.getObjectByInnerObject(fields, User.class);
			logger.info("Signed as: " + authenticatedUser);
			return authenticatedUser;
		}

		return null;
	}

	public void logout() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		logger.debug("User " + username + " logged out succesfuly");
		authenticatedUser = null;
		clearForm();
		response.sendRedirect(request.getContextPath() + "/");
	}

	public void clearForm() {
		username = null;
		password = null;
		password2 = null;
	}

	public User getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static User getActiveUser() {
		Application application = FacesContext.getCurrentInstance().getApplication();
		LoginAuthenticator loginAuthenticator = application.evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{loginAuthenticator}", LoginAuthenticator.class);
		if (loginAuthenticator != null)
			return loginAuthenticator.getAuthenticatedUser();

		return null;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}
