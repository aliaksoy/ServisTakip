import java.io.File;

public class FolderFileDisplay2 {

	public static boolean printFolder = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File folder = new File("D:/DEV/VBT00000/VBT0000_view/ps/platform2.0/cpcm-new/cpcm-new-ejb/src/main/java/com/turkcell/cpcm/model/entity/");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {

				if (listOfFiles[i].getName().contains(".kee") || !listOfFiles[i].getName().startsWith("Ref"))
					continue;

				String name = listOfFiles[i].getName().replaceAll(".java", "");

				//	System.out.println(name);
				printProducer(name);
				//	printList(name);

			} else if (printFolder && listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
	}

	private static void printList(String name) {
		System.out.println("List<RefTypeBase> " + changeName(name) + " = new ArrayList<RefTypeBase>();");

	}

	private static void printProducer(String name) {
		System.out.println("@Produces ");
		System.out.println("@SessionScoped ");
		System.out.println("@Named(\"" + changeName(name) + "\")");
		System.out.println("public List<" + "RefTypeBase" + "> fill" + name + "List(){");
		System.out.println("return fillRefTypeFromCache(" + changeName(name) + ", " + name + ".class);");
		System.out.println("}");

	}

	private static String changeName(String name) {
		String nameTemp = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toLowerCase());
		nameTemp = nameTemp + "List";
		return nameTemp;
	}
}
