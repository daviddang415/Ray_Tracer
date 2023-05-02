import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.xml.namespace.QName;

public class Canvas {

	/* Make sure you understand all the methods you are given.
	 * 
	 * Then fill in the contents of toPPM.  The method should write the
	 * pixels array to a ppm file.  See the method for the details.
	 * 
	 */

	public Canvas() {
	}

	protected MyColor[][] pixels;

	public Canvas(int w, int h) {
		pixels = new MyColor[w][h];
		for (int i=0; i< w; i++)
			for (int j=0; j<h; j++)
				pixels[i][j] = new MyColor(0,0,0);
	}

	public void writeP(int i,int j,MyColor c) {
		pixels[i][j] = c;
	}


	public MyColor pixelAt(int i,int j) {
		return pixels[i][j];
	}

	public void toPPM(String name) {


		File f = new File(name);
		try {
			/* The first line of the file should be P3
			 * On the second line should the number of rows and the number of columns
			 * On the third line put the maximum integer for an RGB value 
			 * (which should be 255, at least for now)
			 * 
			 * After that the RGB values of each pixel.  
			 * 
			 * You need to convert the double values of the RGB to integer values
			 * The doubles should be between 0 and 1
			 */	

			int rows = pixels.length;
			int columns = pixels[0].length;

			PrintWriter p = new PrintWriter(f);
			// A printwriter has the print and println methods you usually use
			p.println("P3");
			p.println(columns + " " + rows);

			p.println("255");

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					double[] val = pixels[i][j].getT();
					//System.out.println(val[0] + " " + val[1] + " " + val[2]);

					int[] integer_val = {(int) (val[0]*255), (int) (val[1]*255), (int) (val[2]*255)};

					String str = integer_val[0] + " " + integer_val[1] + " " + integer_val[2];

					p.println(str);
				}
			}

			p.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
			System.exit(17);
		}




	}





	public static void main(String args[]) {

		int width = 50;
		int height = 100;

		Canvas c = new Canvas(width, height);
		for (int i=0; i<width; i++)
			for (int j=0;j<height/2; j++)
				c.writeP(i,j,new MyColor(0,1,0,1));

		for (int i=0; i<width; i++)
			for (int j=height/2;j<height; j++)
				c.writeP(i,j,new MyColor(1,1,0,1));

		c.toPPM("test.ppm");



	}

}
