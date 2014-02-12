public class ColorInfo {

	int colorCodeInt;
	String hexValue;
	int r, g, b;
	int numPixels;
	int alpha;

	public ColorInfo(int pcolorCodeInt, String phexValue, int pr, int pg, int pb, int palpha, int pnumPixels) {
		colorCodeInt = pcolorCodeInt;
		hexValue = phexValue;
		r = pr;
		g = pg;
		b = pb;
		numPixels = pnumPixels;
		alpha = palpha;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public int getColorCodeInt() {
		return colorCodeInt;
	}

	public void setColorCodeInt(int colorCodeInt) {
		this.colorCodeInt = colorCodeInt;
	}

	public String getHexValue() {
		return hexValue;
	}

	public void setHexValue(String hexValue) {
		this.hexValue = hexValue;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getNumPixels() {
		return numPixels;
	}

	public void setNumPixels(int numPixels) {
		this.numPixels = numPixels;
	}

}
