package org.sahin.home;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudItem;
import org.primefaces.model.tagcloud.TagCloudModel;

@Named
@SessionScoped
public class TagCloudBean implements Serializable {

	private TagCloudModel model;

	public TagCloudBean() {
		model = new DefaultTagCloudModel();
		model.addTag(new DefaultTagCloudItem("Transformers", 1));
		model.addTag(new DefaultTagCloudItem("RIA", "/ui/tagCloud.jsf", 3));
		model.addTag(new DefaultTagCloudItem("AJAX", 2));
		model.addTag(new DefaultTagCloudItem("jQuery", "/ui/tagCloud.jsf", 5));
		model.addTag(new DefaultTagCloudItem("NextGen", 4));
		model.addTag(new DefaultTagCloudItem("JSF 2.0", "/ui/tagCloud.jsf", 2));
		model.addTag(new DefaultTagCloudItem("FCB", 5));
		model.addTag(new DefaultTagCloudItem("Mobile", 3));
		model.addTag(new DefaultTagCloudItem("Themes", "/ui/tagCloud.jsf", 4));
		model.addTag(new DefaultTagCloudItem("Rocks", "/ui/tagCloud.jsf", 1));
	}

	public TagCloudModel getModel() {
		return model;
	}

	public void onSelect(SelectEvent event) {
		TagCloudItem item = (TagCloudItem) event.getObject();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", item.getLabel());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void setModel(TagCloudModel model) {
		this.model = model;
	}

}
