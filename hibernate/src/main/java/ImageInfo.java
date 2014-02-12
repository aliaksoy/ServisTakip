import java.util.ArrayList;
import java.util.List;

public class ImageInfo {

	String imageName;
	String imagePath;
	Integer sensitiveness;

	public Integer getSensitiveness() {
		return sensitiveness;
	}

	public void setSensitiveness(Integer sensitiveness) {
		this.sensitiveness = sensitiveness;
	}

	List<ColorInfo> colorList = new ArrayList<ColorInfo>();

	public ImageInfo(String pimageName, String pimagePath) {
		imageName = pimageName;
		imagePath = pimagePath;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<ColorInfo> getColorList() {
		return colorList;
	}

	public void setColorList(List<ColorInfo> colorList) {
		this.colorList = colorList;
	}

}
