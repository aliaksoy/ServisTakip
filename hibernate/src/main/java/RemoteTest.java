public class RemoteTest {
	public static void main(String[] args) throws Exception {
		Process p = Runtime.getRuntime().exec("C://Program Files (x86)/TeamViewer/Version8/TeamViewer.exe\"");
		p.waitFor();
	}
}
