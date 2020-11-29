// João Eduardo da Paz Silva - 11845514

public class ImageEx extends Image {

	public ImageEx(int w, int h, int r, int g, int b){

		super(w, h, r, g, b);
	}

	public ImageEx(int w, int h){

		super(w, h);
	}

	public int distanceBetween(int ax, int ay, int bx, int by) {
		return (int) ( Math.sqrt( Math.pow(ax - bx, 2) + Math.pow(ay - by, 2) ) );
	}

	public void kochCurve(int px, int py, int qx, int qy, int l){

		int fullDistance = distanceBetween(px, py, qx, qy);

		// The distance of the segments PA, AB, AC and CQ too
		int lineLength = (int) (fullDistance / 3);

		// Escaping condition. Only here the lines will be drawn
		if (lineLength < l) {
			this.drawLine(px, py, qx, qy);
			return;
		}

		// The next step is to calculate the coordenates of A, B and C recursively, and also P and Q, consequently
		
		//// Calculating A coordenates
		int ax = (int) (px + ( (double) (qx - px) / 3));
		int ay = (int) (py + ( (double) (qy - py) / 3));

		//// Calculating B coordenates. These coordenates are more complicated
		////// First thing first we need to calculate the PQ segment's midpoint
		int xMiddle = (int) (px + ( (double) (qx - px) / 2));
		int yMiddle = (int) (py + ( (double) (qy - py) / 2));

		////// Then the angle and the skews
		double angle = (Math.PI/2) - Math.atan((double) (qy-py)/(qx-px));
		double triangleHeight = lineLength * Math.sqrt(3) / 2;
		double xSkew =triangleHeight * Math.cos(angle);
		double ySkew =triangleHeight * Math.sin(angle);

		int bx = 0;
		int by = 0;

		if (px <= qx) {
			bx = (int) (xMiddle + xSkew);
			by = (int) (yMiddle - ySkew);
		} else {
			bx = (int) (xMiddle - xSkew);
			by = (int) (yMiddle + ySkew);
		}

		//// Calculating C coordenates
		int cx = (int) (px + ((qx - px) * 2.0/3.0));
		int cy = (int) (py + ((qy - py) * 2.0/3.0));

		/* System.out.println(img); */

		this.kochCurve(px, py, ax, ay, l);
		this.kochCurve(ax, ay, bx, by, l);
		this.kochCurve(bx, by, cx, cy, l);
		this.kochCurve(cx, cy, qx, qy, l);
	}

	public boolean isValidCoordenate(int x, int y) {
		boolean notValid = (
			x < 0 ||
			x >= this.getWidth() ||
			y < 0 ||
			y >= this.getHeight()
		);

		return !notValid;
	}

	private void regionFill(int x, int y, int referenceRGB) {
		if (this.isValidCoordenate(x, y)) {
			if (this.getPixel(x, y) == referenceRGB) {

				this.setPixel(x, y);

				// Recursive calls
				this.regionFill(x+1, y, referenceRGB);
				this.regionFill(x, y+1, referenceRGB);
				this.regionFill(x-1, y, referenceRGB);
				this.regionFill(x, y-1, referenceRGB);
			}
		}
	}

	public void regionFill(int x, int y) {
		if (this.isValidCoordenate(x, y)) {
			this.regionFill(x, y, this.getPixel(x, y));				
		}
	}
}
