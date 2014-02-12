package org.sahin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Recipe extends BaseEntity {

	String name;
	@Column(length = 1000)
	String description;
	@Column(length = 500)
	String ingredients;
	String imageId;
	@ManyToOne
	User createdBy;

	//	@ManyToMany(cascade = CascadeType.ALL)
	//	@JoinTable(joinColumns = @JoinColumn(name = "RECIPE_ID"), inverseJoinColumns = @JoinColumn(name = "FILEPATH_ID"))
	//	List<FilePaths> photos = new ArrayList<FilePaths>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//	public List<FilePaths> getPhotos() {
	//		return photos;
	//	}
	//
	//	public void setPhotos(List<FilePaths> photos) {
	//		this.photos = photos;
	//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}
