package org.sahin.home;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.sahin.model.Recipe;
import org.sahin.persistence.HomeBean;

public class RecipeHomePage extends HomeBean<Recipe> {

	List<Recipe> recipeListA = new ArrayList<Recipe>();
	List<Recipe> recipeListB = new ArrayList<Recipe>();
	List<Recipe> recipeListC = new ArrayList<Recipe>();

	boolean fillObjectsCompleted = false;

	public void onPageLoad() {

		if (!FacesContext.getCurrentInstance().isPostback()) {
			clearInstance();

			if (!fillObjectsCompleted)
				fillAllRecipes();
		}
	}

	private void fillAllRecipes() {
		try {

			fillObjectsCompleted = true;

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public List<Recipe> getRecipeListA() {
		return recipeListA;
	}

	public void setRecipeListA(List<Recipe> recipeListA) {
		this.recipeListA = recipeListA;
	}

	public List<Recipe> getRecipeListB() {
		return recipeListB;
	}

	public void setRecipeListB(List<Recipe> recipeListB) {
		this.recipeListB = recipeListB;
	}

	public List<Recipe> getRecipeListC() {
		return recipeListC;
	}

	public void setRecipeListC(List<Recipe> recipeListC) {
		this.recipeListC = recipeListC;
	}

}
