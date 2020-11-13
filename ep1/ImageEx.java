// Esqueleto da classe na qual devem ser implementadas as novas funcionalidades de desenho

public class ImageEx extends Image {

	static ImageEx img = null;

	public ImageEx(int w, int h, int r, int g, int b){

		super(w, h, r, g, b);
	}

	public ImageEx(int w, int h){

		super(w, h);
	}

		/* 
			P: 0, (int) (h/2)
			A: (int) (w/3), (int) (h/2)
			B: (int) (w/2), (int) ((h/2) - (w/3 * Math.sqrt(3) / 2))
			C: (int) (2*w/3), (int) (h/2)
			Q: w, (int) (h/2)
		*/	

	public int distanceBetween(int ax, int ay, int bx, int by) {
		return (int) ( Math.sqrt( Math.pow(ax - bx, 2) + Math.pow(ay - by, 2) ) );
	}

	public void kochCurve(int px, int py, int qx, int qy, int l){

		int fullDistance = distanceBetween(px, py, qx, qy);

		// The distance between PA, AB, AC and CQ
		int lineLength = (int) (fullDistance / 3);

		// Escaping condition. Only here the lines will be drawn
		if (lineLength < l) {
			img.drawLine(px, py, qx, qy);
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

		double angle = (Math.PI/2) - Math.atan((double) (qy-py)/(qx-px));

		double xSkew = lineLength * Math.cos(angle);
		double ySkew = lineLength * Math.sin(angle);

		int bx;
		int by;

		if (px < qx) {
			bx = (int) (xMiddle + xSkew);
			by = (int) (yMiddle - ySkew);
		} else {
			bx = (int) (xMiddle - xSkew);
			by = (int) (yMiddle + ySkew);
		}

		//// Calculating A coordenates
		int cx = (int) (px + ((qx - px) * 2.0/3.0));
		int cy = (int) (py + ((qy - py) * 2.0/3.0));

		img.kochCurve(px, py, ax, ay, l);
		img.kochCurve(ax, ay, bx, by, l);
		img.kochCurve(bx, by, cx, cy, l);
		img.kochCurve(cx, cy, qx, qy, l);
	}

	public void regionFill(int x, int y, int reference_rgb){

	}

	public static void drawCurve() {

		int w = 1000;
		int h = 1000;

		img = new ImageEx(w, h, 0, 0, 0);

		img.clear();		

		img.setColor(0, 255, 0);

		/* img.kochCurve(500, 250, 500, 750, 10); */
		img.kochCurve(500, 750, 500, 250, 10);


		img.save("out.png");
	}

	public static void main(String[] args) {
		drawCurve();
	}
}
