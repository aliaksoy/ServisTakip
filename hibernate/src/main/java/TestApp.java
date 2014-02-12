public class TestApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyChild<MyTestType> test = new MyChild<MyTestType>();
		System.out.println(test.createInstance());

	}
}
