import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImageDownloader {

	/**
	 * @param args
	 */

	final static int size = 1024;

	public static void fileUrl(String fAddress, String localFileName, String destinationDir) {
		OutputStream outStream = null;
		URLConnection uCon = null;

		InputStream is = null;
		try {
			URL Url;
			byte[] buf;
			int ByteRead, ByteWritten = 0;
			Url = new URL(fAddress);
			outStream = new BufferedOutputStream(new FileOutputStream(destinationDir + "\\" + localFileName));

			uCon = Url.openConnection();
			is = uCon.getInputStream();
			buf = new byte[size];
			while ((ByteRead = is.read(buf)) != -1) {
				outStream.write(buf, 0, ByteRead);
				ByteWritten += ByteRead;
			}
			System.out.println("Downloaded Successfully.");
			System.out.println("File name:\"" + localFileName + "\"\nNo ofbytes :" + ByteWritten);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void fileDownload(String fAddress, String destinationDir) {
		int slashIndex = fAddress.lastIndexOf('/');
		int periodIndex = fAddress.lastIndexOf('.');

		String fileName = fAddress.substring(slashIndex + 1);

		if (periodIndex >= 1 && slashIndex >= 0 && slashIndex < fAddress.length() - 1) {
			fileUrl(fAddress, fileName, destinationDir);
		} else {
			System.err.println("path or file name.");
		}
	}

	public static void main(String[] args) {
		//		doSomething();
	}

	protected static void doSomething() {
		Date date = new Date();
		setDefault();
		List<String> list = new ArrayList<String>();
		list.add(".jpg");
		list.add("_1_zoom.jpg");
		list.add("_2_zoom.jpg");
		list.add("_3_zoom.jpg");
		list.add("_4_zoom.jpg");
		list.add("_5_zoom.jpg");

		for (int y = 01; y < 99; y++) {
			for (int i = 01; i < 99; i++) {
				String str = "http://www.37numara.com/images/products/00/" + sayacAyarla(y) + "/";

				str = str + sayacAyarla(i) + "/" + sayacAyarla(y) + sayacAyarla(i) + "_buyuk";
				System.out.println(str);

				for (String string : list) {
					try {
						fileDownload(str + string + "", "d://37/");
					} catch (Exception e) {
						break;
					}
				}

			}
		}
		Date date2 = new Date();
		System.out.println(date2.getTime() - date.getTime());
	}

	private static String sayacAyarla(int i) {
		if (i < 10)
			return "0" + i;
		else
			return "" + i;
	}

	private static void setDefault() {
		System.getProperties().put("http.proxyHost", "10.200.125.200");
		System.getProperties().put("http.proxyPort", "80");
		System.getProperties().put("http.proxyUser", "VBT00000");
		System.getProperties().put("http.proxyPassword", "Mersin37");

	}
}
