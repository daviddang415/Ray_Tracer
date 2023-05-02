

public class Transformations {

	/* In order to cement your understanding of Transformations, 
	 * fill in the methods with missing insides - the ones that currently
	 * return null
	 */
	
	public static Matrices getTranslate(double x, double y, double z) {
		return new Matrices(
				new double[][]{
					
					{1,0,0,x},
					{0,1,0,y},
					{0,0,1,z},
					{0,0,0,1}});
	}
	
	public static Matrices getScale(double sx, double sy, double sz) {
		return new Matrices(
				new double[][]{
					
					{sx,0,0,0},
					{0,sy,0,0},
					{0,0,sz,0},
					{0,0,0,1}});
	}
	
	public static Matrices getRotZ(double x) {
		return new Matrices(
				new double[][]{
					
					{Math.cos(x),-Math.sin(x),0,0},
					{Math.sin(x), Math.cos(x),0,0},
					{0,0,1,0},
					{0,0,0,1}});
	}
	
	public static Matrices getRotY(double x) {
		return new Matrices(
				new double[][]{
					
					{Math.cos(x),0,Math.sin(x),0},
					{0,1,0,0},
					{-Math.sin(x),0,Math.cos(x),0},
					{0,0,0,1}});
	}
	
	public static Matrices getRotX(double x) {
		return new Matrices(
				new double[][]{
					
					{1,0,0,0},
					{0,Math.cos(x),-Math.sin(x),0},
					{0,Math.sin(x),Math.cos(x),0},
					{0,0,0,1}});
	}
	
	public static void main(String[] args) {
	
		Matrices m = Matrices.identity();
		Matrices n = new Matrices(new double [][] 
				{{2,3,4,5},{1,2,3,4},{2,3,4,7},{0,0,0,1}});
		
		Matrices p1 = Matrices.mult(m,n);
		Matrices p2 = Matrices.mult(n,m);

		System.out.println(p1);
		System.out.println(p2);

		Point v1 = new Point(Matrices.apply(n, new Point(1,1,1)));
		System.out.println(v1);
		System.out.println("");


		/*
		 * a. generate two scaling matrices and see what happens when you multiply them in both possible orders 
		 */

		 System.out.println("Part (a)");
		 System.out.println("");

		 Matrices scalarMatrixOne = getScale(1, 2, 3);
		 Matrices scalarMatrixTwo = getScale(-4, 2, 6);

		 System.out.println("scalarMatrixOne:");
		 System.out.println(scalarMatrixOne);

		 System.out.println("scalarMatrixTwo:");
		 System.out.println(scalarMatrixTwo);

		 Matrices transformationOne = Matrices.mult(scalarMatrixOne,scalarMatrixTwo);
		 Matrices transformationTwo = Matrices.mult(scalarMatrixTwo,scalarMatrixOne);
 
		 System.out.println("Transformation one:");
		 System.out.println(transformationOne);

		 System.out.println("Transformation two:");
		 System.out.println(transformationTwo);

		 System.out.println("Multiplying the two matrices results in a scalar matrix where each scalar for x, y, and z is a product of the scalars of the two matrices");

		 System.out.println("");

		/*
		b. give an example of two transformations that do not commute. Print both products to
		show they do not. In addition, apply the two (different) products to at least one Point to show
		the results are different.		 
		*/

		System.out.println("Part (b)");
		System.out.println("");

		Matrices rotXMatrix = getRotX(Math.toRadians(45));
		Matrices translateMatrix = getTranslate(-4, 2, 6);

		System.out.println("rotXMatrix:");
		System.out.println(rotXMatrix);

		System.out.println("translateMatrix:");
		System.out.println(translateMatrix);

		Matrices transformationThree = Matrices.mult(rotXMatrix,translateMatrix);
		Matrices transformationFour = Matrices.mult(translateMatrix,rotXMatrix);

		System.out.println("Transformation one:");
		System.out.println(transformationThree);

		System.out.println("Transformation two:");
		System.out.println(transformationFour);

		System.out.println("Point after using first transformation:");
		Point v2 = new Point(Matrices.apply(transformationThree, new Point(1,0,0)));
		System.out.println(v2);

		System.out.println("");

		System.out.println("Point after using second transformation:");
		Point v3 = new Point(Matrices.apply(transformationFour, new Point(1,0,0)));
		System.out.println(v3);

		System.out.println("");

		System.out.println("Translating and rotating 45 degrees around the x-axis are not commutative, which results in different transformations and different points after using each possible transformation");

		System.out.println("");

		/*
		 * c. add something else that you think will help you understand Transformations. Your
		 * program should, when run, System.out.println an explanation of what you are exploring and
		 * then print whatever you are doing to do the exploration. 
		 */

		 System.out.println("Part (c)");

		 System.out.println("");
		 System.out.println("Explanation:");
		 System.out.println("I am exploring how to rotate a point about the three axes. I will aim to move the point (1,1,1) to (0, 0, sqrt(3)) by rotating the point 45 degrees around the y-axis and then rotate the new point 45 degrees around the z-axis so that the new point is located at (0, sqrt(3), 0) and on the positive side of the y-axis. After that, I will rotate 90 degrees about the x-axis so that the point is now at (0, 0, sqrt(3)) and on the z-axis. I create three rotation matrices and multiply them together to obtain the desired transformation.");

		 System.out.println("");

		 Matrices rotOne = getRotY(Math.toRadians(45)); 
		 Matrices rotTwo = getRotZ(Math.toRadians(45));
		 Matrices rotThree = getRotX(Math.toRadians(90));

		 Matrices transformationFive = Matrices.mult(rotThree, rotTwo, rotOne);

		 System.out.println("Transformation:");
		 System.out.println(transformationFive);
 
		 System.out.println("Old point:");
		 System.out.println(new Point(1, 1, 1));
		 System.out.println("");

		 Point v4 = new Point(Matrices.apply(transformationFive, new Point(1, 1, 1)));

		 System.out.println("Transformed point:");
		 System.out.println(v4);
		 System.out.println("");
	}

}
