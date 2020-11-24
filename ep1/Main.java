import java.util.*;
import java.io.*;

// Esqueleto do programa principal

public class Main {

	public static void generateImage(String inputFileName, String outputFileName) throws IOException {

		Scanner scanner = new Scanner(new File(inputFileName));

		ImageEx image = new ImageEx(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
	
		while(scanner.hasNext()){

			String command = scanner.next();

			if (command.equals("SET_COLOR")){

				image.setColor(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			}

			if (command.equals("SET_PIXEL")){

				image.setPixel(scanner.nextInt(), scanner.nextInt());
			}

			if (command.equals("DRAW_LINE")){

				image.drawLine(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			}

			if (command.equals("KOCH_CURVE")){

				image.kochCurve(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			}

			if (command.equals("REGION_FILL")){

				image.regionFill(scanner.nextInt(), scanner.nextInt());
			}
		}

		image.save(outputFileName);
	}

	public static void main(String [] args){


		if(args.length != 2){
	
			System.out.println("Uso: java " + Main.class.getName() + " entrada.txt saida.png");
			System.exit(1);
		}

		try{
			generateImage(args[0], args[1]);
		}
		catch(IOException e){
			
			System.out.println("Problem generating image... :(");
			e.printStackTrace();
		}
	}
}
