import java.util.ArrayList;


public class Sphere extends Traceable {
	
	protected double radius = 1;
	
	public Sphere() {
		radius = 1;
	}
	
	public ArrayList<Intersection> local_intersect(Ray r1) {
		/* transform the ray by inverse of the Traceable transform before 
		 * intersecting See the beginning of the method in Cube for help
		 * */
		
		
		
		/* Calculate a, b and c as in class.  Be careful about using the ray origin, 
		 * which is a point - don't accidentally get an extra 1 from the w coordinate
		 * 
		 */
		
		Ray ray = r1.transform(transform.invert());

		double a = 0;
		double b = 0;
		double c = 0;

		Point p = ray.origin;
		Vector v = ray.direction;

		a = Tuple.dot(v, v);
		b = 2 * Tuple.dot(p, v);
		c = (Tuple.dot(p,p) - 1) - radius*radius;

		//System.out.println(a);
		//System.out.println(b);
		//System.out.println(c);
		
		double discriminant = b*b - 4*a*c;

		//System.out.println(discriminant);
		
		ArrayList<Intersection> ans = new ArrayList<Intersection>();

		/* use the discriminant to decide what to return in ans - 
		 * it should empty if there are no intersections (not null)
		 * 
		 * Fill in the intersections
		 */

		if (discriminant == 0) {
			double t = ((-1) * b)/(2 * a);
			ans.add(new Intersection(this, t));

		} else if (discriminant > 0) {
			discriminant = Math.sqrt(discriminant);
			double t_1 = (((-1) * b) + discriminant)/(2 * a);
			double t_2 = (((-1) * b) - discriminant)/(2 * a);

			ans.add(new Intersection(this, t_1));
			ans.add(new Intersection(this, t_2));
		}
		
		return ans;
		
	}
	
	@Override
	public Vector local_normal_at(Point p, Intersection dontUse) {
		return new Vector(new Tuple(world_to_object(p).getT()));
	}
	
	public static void main(String[] args) {
		
		Ray r = new Ray(new Point(0,0,-5),new Vector(0,0,1));
		Traceable s = new Sphere();
		
		show(r,s);
				
		s.transform = Transformations.getScale(2, 2, 2);

		show(r,s);
		
		s.transform = Transformations.getTranslate(5, 0, 0);

		show(r,s);
		
	}

	private static void show(Ray r, Traceable s) {
		// Assumes zero or two intersections - debugging code
		ArrayList<Intersection> ans = s.intersections(r);
		if (ans.size() == 0)
			System.out.println("No Intersections");
		else 
			System.out.println(ans.get(0)+"   "+ans.get(1));
	}

	@Override
	public boolean includes(Traceable object) {
		return false;
	}
	
	
	
	
	
	
	
}
