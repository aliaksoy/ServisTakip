import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javax.imageio.ImageIO;

public class ImageLoader {

	static Integer sensitiveness = 10;

	static Integer numberOfImagesForProcessing = 1;
	static Integer colorNearless = 10;
	static Integer simillarityInPixel = 50;

	static boolean creatDivs = false;

	public static BufferedImage loadImageFrom(File f) throws IOException {
		return ImageIO.read(f);
	}

	public static int countUniqueColours(BufferedImage img, ImageInfo imageInfo) {
		BufferedImage buf = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

		int pixels[] = ((DataBufferInt) (buf).getRaster().getDataBuffer()).getData();
		TreeMap<String, Integer> coloursFound = new TreeMap<String, Integer>();
		int width = buf.getWidth();

		buf.getGraphics().drawImage(img, 0, 0, null);
		imageInfo.setSensitiveness(new Integer(sensitiveness));
		HashMap<String, HSLColor> colorMap = new HashMap<String, HSLColor>();
		for (int y = 0; y < buf.getHeight(); y++)
			for (int x = 0; x < width; x++) {
				int index = y * width + x;
				int c = pixels[index];
				if (c == -1)
					continue;
				int rgb = c;

				int alpha = (rgb >> 24) & 0xFF;
				int red = (rgb >> 16) & 0xFF;
				int green = (rgb >> 8) & 0xFF;
				int blue = (rgb) & 0xFF;

				Color color = new Color(red, green, blue, alpha);
				HSLColor base = new HSLColor(color);
				if (base.getLuminance() > 80)
					continue;
				c = (int) (base.getHue());
				if (c == 0)
					continue;
				System.err.println("** " + base.getLuminance());
				//				c = c / 10;
				c = c / 10;
				String key = c + "-" + base.getLuminance() / 50;
				colorMap.put("" + key, base);
				System.out.println(key);
				//				c = c / colorNearless;
				//				c = c * colorNearless;
				if (!coloursFound.containsKey(key)) {
					coloursFound.put(key, 0);
				}
				Integer count = coloursFound.get(key);
				coloursFound.put(key, count + 1);
			}

		for (String s : coloursFound.keySet()) {
			int i = Integer.parseInt(s.split("-")[0]);
			Integer count = coloursFound.get(s);
			if (count > sensitiveness) {

				//System.out.println("color : ....  " + i + " count :" + count);
				HSLColor base = colorMap.get("" + s);
				//				int rgb = base.getRGB().getAlpha();

				int alpha = base.getRGB().getAlpha();
				int red = base.getRGB().getRed();
				int green = base.getRGB().getGreen();
				int blue = base.getRGB().getBlue();
				String hex = String.format("#%02x%02x%02x", red, green, blue);
				String yeniSayi = convert(hex, 16, 10);
				if (yeniSayi == null || yeniSayi.equals(""))
					yeniSayi = "0";
				ColorInfo cInfo = new ColorInfo(Integer.parseInt(yeniSayi), hex, red, green, blue, alpha, count);
				cInfo.setNumPixels(count);
				imageInfo.getColorList().add(cInfo);
				//System.out.println("hex  ....  " + hex);
				if (creatDivs)
					createDiv(hex, yeniSayi, count);

			}
		}

		return coloursFound.size();
	}

	private static String createDiv(String colorCode, String yeniSayi, Integer count) {
		String returnStr = "<div style=\"min-width: 5px; min-height: 5px;background-color:" + "  " + yeniSayi + "\">" + colorCode + "  -  " + count
				+ "</div>";
		System.out.println(returnStr);
		return returnStr;

	}

	private static String convert(String oldNum, double base1, double base2) {
		oldNum = oldNum.replace("#", "");
		long temp = 0;
		int x = 0;
		String nextChar;
		String convertedNumber = "";
		oldNum = oldNum.toUpperCase();
		String chars = "0123456789ABCDEF";
		for (int i = 0; i < oldNum.length(); i++) {
			if (chars.indexOf(oldNum.substring(i, i + 1)) >= base1 || chars.indexOf(oldNum.substring(i, i + 1)) < 0)
				return "error - invalid data";
		}
		for (int i = oldNum.length(); i > 0; i--) {
			nextChar = oldNum.substring(x, x + 1);
			temp += chars.indexOf(nextChar) * Math.pow(base1, i - 1);
			x++;
		}
		x = 0;
		while (temp > Math.pow(base2, x))
			x++;
		int index = 0;
		for (int i = 1; i <= x; i++) {
			index = (int) (temp / Math.pow(base2, x - i));
			convertedNumber += chars.substring(index, index + 1);
			temp -= index * Math.pow(base2, x - i);
		}
		return convertedNumber;
	}

	public static void main(String a[]) {

		try {

			BufferedImage img = loadImageFrom(new File("d://37/test.jpg"));

			// do anything you want with the image.

			ImageInfo imageInfo = new ImageInfo("test.jpg", "d://37/test.jpg");
			countUniqueColours(img, imageInfo);

			// Directory path here
			String path = "listOfFiles.length";

			String files;
			File folder = new File("d://37");
			File[] listOfFiles = folder.listFiles();
			String htmlString = "";
			String divs = "<table>";
			for (int i = 0; i < listOfFiles.length; i++) {

				if (i == numberOfImagesForProcessing) {

					break;
				} else
					divs = divs + "<tr>";

				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();
					divs = divs + "<td >" + setBackGround(imageInfo.getImageName(), "") + "</td><td>" + setBackGround(files, "") + "</td><td>";
					//System.out.println(files);

					File tempFile = new File("d://37/" + files);
					double bytes = tempFile.length();
					if (bytes == 0 || tempFile.getName().contains("html")) {
						tempFile.delete();
						continue;
					}

					BufferedImage img2 = loadImageFrom(tempFile);

					// do anything you want with the image.

					ImageInfo imageInfo2 = new ImageInfo(files, "d://37/" + files);
					countUniqueColours(img2, imageInfo2);
					//divs = divs + "" + imageInfo2.getImageName() + "<br></br>";
					divs = divs + compareImages(imageInfo, imageInfo2);
					divs = divs + "</td>";
					divs = divs + "</tr>";

				}
			}
			divs = divs + "</tr></table>";
			htmlString = htmlHead() + divs + htmlEnd();
			System.out.println("\n\n\n" + htmlString);
			writeFile("d://37/test.html", htmlString);
			//System.out.println(listOfFiles.length);

		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Check that '" + a[0] + "' exists and is a valid image file.");
		}
	}

	private static String setBackGround(String imageName, String relativeFolder) {
		String returnStr = "<div style=\"min-width: 500px; min-height: 500px;background-image:" + "  " + "url(" + relativeFolder + imageName
				+ "); background-repeat:no-repeat !important;\">" + ".." + "</div>";
		System.out.println(returnStr);
		return returnStr;
	}

	public static void writeFile(String fileName, String content) {

		FileWriter fileWriter = null;
		try {

			File newTextFile = new File(fileName);
			fileWriter = new FileWriter(newTextFile);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {

		} finally {
			try {
				fileWriter.close();
			} catch (IOException ex) {

			}
		}
	}

	private static String compareImages(ImageInfo imageInfo, ImageInfo imageInfo2) {
		String htmlString = "";

		boolean show1 = true;
		boolean show2 = true;
		if (show1) {
			for (Iterator iterator = imageInfo.getColorList().iterator(); iterator.hasNext();) {
				ColorInfo colorInfo = (ColorInfo) iterator.next();
				if (!colorInfo.getHexValue().contains("#fff")) {
					//	htmlString = htmlString + createDiv("" + colorInfo.getColorCodeInt(), colorInfo.getHexValue(), colorInfo.getNumPixels());
					int sayac = 50;
					if (colorInfo.getNumPixels() > simillarityInPixel) {
						//	htmlString = htmlString + createDiv("" + colorInfo.getColorCodeInt(), colorInfo.getHexValue(), colorInfo.getNumPixels());

						if (show2) {
							for (ColorInfo colorInfo2 : imageInfo2.getColorList()) {

								//					if (!colorInfo2.getHexValue().equals("ffffff"))
								//
								//if (Math.abs(colorInfo2.getColorCodeInt() - colorInfo.getColorCodeInt()) < 100 && colorInfo2.getNumPixels() > 100)
								if (((colorInfo2.getNumPixels() > simillarityInPixel) && (colorInfo.getNumPixels() > simillarityInPixel)))
									htmlString = htmlString
											+ createDiv("" + colorInfo2.getColorCodeInt(), colorInfo2.getHexValue(), colorInfo2.getNumPixels());

							}
						}

					}

				}

			}
		}

		//		if (show2) {
		//			for (ColorInfo colorInfo2 : imageInfo2.getColorList()) {
		//
		//				//					if (!colorInfo2.getHexValue().equals("ffffff"))
		//				//
		//				//if (Math.abs(colorInfo2.getColorCodeInt() - colorInfo.getColorCodeInt()) < 100 && colorInfo2.getNumPixels() > 100)
		//				if (((colorInfo2.getNumPixels() > simillarityInPixel) && (colorInfo.getNumPixels() > simillarityInPixel))
		//						&& colorInfo2.getHexValue().substring(0, 4).equals(colorInfo.getHexValue().substring(0, 4)))
		//					htmlString = htmlString
		//							+ createDiv("" + colorInfo2.getColorCodeInt(), colorInfo2.getHexValue(), colorInfo2.getNumPixels());
		//
		//		}
		//		}

		//		if (show2) {
		//			for (ColorInfo colorInfo2 : imageInfo.getColorList()) {
		//
		//				//					if (!colorInfo2.getHexValue().equals("ffffff"))
		//				if (Math.abs(colorInfo2.getColorCodeInt() - 12661302) < 100 && colorInfo2.getNumPixels() > 100)
		//					htmlString = htmlString + createDiv("" + colorInfo2.getColorCodeInt(), colorInfo2.getHexValue(), colorInfo2.getNumPixels() * 100);
		//
		//			}
		//		}
		return htmlString;

	}

	public static String htmlHead() {

		return "<html><body >";
	}

	public static String htmlEnd() {

		return "</body ></html>";
	}

}