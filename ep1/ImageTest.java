// Exemplo de uso dos recursos da classe Image

public class ImageTest {

	public static void drawScene(String fileName){

		int w = 512;
		int h = 512;
		Image img = new Image(w, h);
		img.setBgColor(0, 0, 0);
		img.clear();

		int numberOfPixels = 15000;

		for(int i = 0; i < numberOfPixels; i++){

			int x = (int) ((i * w) / numberOfPixels);
			int y = (int) h/4;

			/* int r = 255; */
			/* int g = (int)(64 + y/2 + Math.random() * 64); */
			/* int g = (int)(64 + x/5 + Math.random() * 64); */
			/* int b = 255 - g; */

			int r = (int) (Math.random() * 256);
			int g = (int) (Math.random() * 256);
			int b = (int) (Math.random() * 256);
			
			img.setColor(r, g, b);
			img.setPixel(x, y);
		}

		for(double d = 1; d <= 20; d++){

			int l = 1000;
			int f = 1;

			int x1 = (int)(w/2 - l/(f + d) * 5);
			int x2 = (int)(w/2 + l/(f + d) * 5);
			int y = (int)(h/2.5 + l/(f + d));

			img.setColor(0, 255, 128);
			img.drawLine(x1, y, x2, y);
		}

		for(double d = -1; d <= 1; d += 0.05){

			int l = 1000;
			int f = 1;

			int y1 = (int)(h/2.5 + l/(f + 1));
			int y2 = (int)(h/2.5 + l/(f + 20));

			int x1 = (int) (w/2 + l/(f + 1) * d * 5);
			int x2 = (int) (w/2 + l/(f + 20) * d * 5);

			img.setColor(0, 128, 255);
			img.drawLine(x1, y1, x2, y2);
			
		}

		img.save(fileName);
	}

	public static void main(String [] args){

		drawScene(args.length > 0 ? args[0] : "out.png");
	}
}

