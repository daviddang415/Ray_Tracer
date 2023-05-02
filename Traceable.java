import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public abstract class Traceable {

	protected Matrices transform = Matrices.identity();
	protected Material material = new Material();

	public ArrayList<Intersection> intersections(Ray r) { 
		return this.local_intersect(r);
	};

	/* You need to write this - this should return 
	null if result is empty
	the Intersection with the smallest POSITIVE t value
	 */ 

	public static Intersection hit(ArrayList<Intersection> result) {
		if (result.size() == 0) {
			return null;
		}

		Intersection min = null;
		Intersection zero = new Intersection(null, 0);
		
		for (Intersection temp: result) {
			if (temp.compareTo(zero) == 1) {
				if (min == null) {
					min = temp;
				} else if (min.compareTo(temp) == 1) {
					min = temp;
				}
			}
		}

		return min;
	}

	/*
	public static Intersection otherHit(ArrayList<Intersection> result, Intersection lastObject) {
		if (result.size() == 0) {
			return null;
		}

		ArrayList<Intersection> ans = new ArrayList<Intersection>();

		for (Intersection temp: result) {
			if ((temp.compareTo(lastObject) == 1) && !temp.equals(lastObject)) {
					ans.add(temp);
			}
		}

		return hit(ans);
	}
	*/

	public static Intersection otherHit(ArrayList<Intersection> result, double t, Intersection last) {
		if (result.size() == 0) {
			return null;
		}

		ArrayList<Intersection> ans = new ArrayList<Intersection>();

		for (Intersection temp: result) {
			if ((temp.t > t) && !temp.object.includes(last.object)) {
				/*
				    System.out.println("///////////////////////////////////////////////////");
				    System.out.println(last.object.material.color);
				    System.out.println(temp.object.material.color);
					System.out.println(last.t);
					System.out.println(temp.t);
					System.out.println(!temp.object.includes(last.object));
				    System.out.println("///////////////////////////////////////////////////");
				*/
					ans.add(temp);
			}
		}

		//System.out.println(lastObject.object.material.color);
		//System.out.println(dude.object.material.color);
		return hit(ans);
	}


	//merges two Intersection Lists  
	public static ArrayList<Intersection> mergeInters(ArrayList<Intersection> rightxs,
			ArrayList<Intersection> leftxs) {
		ArrayList<Intersection> result;
		result = new ArrayList<Intersection>();
		result.addAll(rightxs);
		result.addAll(leftxs);

		return result;
	}

	public Vector normal_to_world(Vector normal) {
		Vector new_norm = Matrices.apply(transform.invert().transpose(), normal);
		return new_norm;
	}

	public Point world_to_object(Point p) {	
		Point point = new Point(Matrices.apply(transform.invert(), p));
		return point;
	}

	public abstract ArrayList<Intersection> local_intersect(Ray r);




	public static void main(String[] args) {

		// This is a little test program
		
		var test = new ArrayList<Intersection>();

		test.add(new Intersection(new Cube(),2));
		test.add(new Intersection(new Cube(),1));

		for (Intersection f:test)
			System.out.println(f);

		System.out.println(hit(test));




	}

	public final Vector normalAt(Point p, Intersection i) {

		return null;
	}


	public abstract Vector local_normal_at(Point p, Intersection i);

	public abstract boolean includes(Traceable object);


}
