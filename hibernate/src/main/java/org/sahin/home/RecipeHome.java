package org.sahin.home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;
import org.sahin.model.Recipe;
import org.sahin.persistence.HomeBean;

@Named
@ConversationScoped
public class RecipeHome extends HomeBean<Recipe> {
	private static final long serialVersionUID = 1L;
	List<Recipe> recipeList = new ArrayList<Recipe>();
	Recipe testRecipe;
	private TagCloudModel recipeClouds;

	public void createRecipeClouds() {
		recipeClouds = new DefaultTagCloudModel();
		int i = 0;
		for (Recipe recipe : getRecipeList()) {
			recipeClouds.addTag(new DefaultTagCloudItem(recipe.getName(), getRandomCount(i)));
			i++;
			if (i == 10)
				break;
		}

	}

	private int getRandomCount(int num) {
		List numbers = null;
		if (numbers == null) {
			numbers = new ArrayList();
			numbers.add(1);
			numbers.add(3);
			numbers.add(4);
			numbers.add(2);
			numbers.add(1);
			numbers.add(5);
			numbers.add(4);
			numbers.add(2);
			numbers.add(1);
			numbers.add(3);
			numbers.add(4);
			numbers.add(2);
		}
		return (Integer) numbers.get(num);
	}

	public TagCloudModel getRecipeClouds() {
		if (recipeClouds == null)
			createRecipeClouds();
		return recipeClouds;
	}

	public void setRecipeClouds(TagCloudModel recipeClouds) {
		this.recipeClouds = recipeClouds;
	}

	public List<Recipe> getRecipeList() {
		if (recipeList.isEmpty())
			fillRecipes();
		return recipeList;
	}

	int count = 0;

	public Recipe getTestRecipe() {
		count++;
		if (count == 30)
			count = 0;
		try {

			testRecipe = getRecipeList().get(count);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return testRecipe;
	}

	public void setTestRecipe(Recipe testRecipe) {
		this.testRecipe = testRecipe;
	}

	private void fillRecipes() {

		//		int count = 0;
		//		for (int y = 1; y < 10; y++) {
		//			for (int i = 1; i < 4; i++) {
		//				count++;
		//				Recipe recipe = new Recipe();
		//				recipe.setName("tarif " + count);
		//				recipe.setDescription("tarif " + count);
		//				recipe.setIngredients("icindekiler" + i);
		//				recipe.setImageId("foodImg/recipe" + i + ".jpeg");
		//				recipeList.add(recipe);
		//			}
		//		}
		if (objectList.isEmpty())
			fillObjects();
		for (int i = 0; i < 30; i++) {
			recipeList.addAll(objectList);
		}

	}

	public void handleFileUpload(FileUploadEvent event) throws IOException {
		UploadedFile file = event.getFile();
		writeFile(file);
		if (file != null)
			logger.info(file.getFileName());
		//application code
	}

	public void setRecipeList(List<Recipe> recipeList) {
		this.recipeList = recipeList;
	}

	public void writeFile(UploadedFile file) throws IOException {
		//		InputStream is = file.getInputstream();
		//		try {
		//			String basePath = FMConstants.RECIPE_FILE_PATH;
		//			String fileName = "" + new Date().getTime() + file.getFileName();
		//			OutputStream os = new FileOutputStream(basePath + fileName);
		//
		//			try {
		//				byte[] buffer = new byte[4096];
		//				for (int n; (n = is.read(buffer)) != -1;)
		//					os.write(buffer, 0, n);
		//				FilePaths filePaths = new FilePaths();
		//				filePaths.setPath(fileName);
		//				save(filePaths);
		//				getInstance().getPhotos().add(filePaths);
		//			} catch (Exception e) {
		//				// TODO: handle exception
		//			} finally {
		//				os.close();
		//			}
		//
		//		} catch (Exception e) {
		//		} finally {
		//			is.close();
		//		}
	}
}
