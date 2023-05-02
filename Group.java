import java.util.ArrayList;


public class Group extends Traceable {
	
    protected ArrayList<Traceable> objects;
	
    //need a way to calculate the center and radius of the group
	public Group(ArrayList<Traceable> objects) {
        for (Traceable object: objects) {
            object.transform = Matrices.mult(this.transform, object.transform);
        }
        this.objects = objects;
	}

    public Group() {
        this.objects = new ArrayList<Traceable>();
	}

    public void addObject(Traceable object) {
        object.transform = Matrices.mult(this.transform, object.transform);
        this.objects.add(object);
    }

    public ArrayList<Intersection> local_intersect(Ray r1) {

         ArrayList<Intersection> ans = new ArrayList<Intersection>();
         
        for (Traceable curObject: this.objects) {
            ArrayList<Intersection> curObjectIntersections = curObject.local_intersect(r1);
            for (Intersection curObjectIntersection: curObjectIntersections) {
                ans.add(curObjectIntersection);
            }
        }

         return ans;
		
	}
	
	@Override
	public Vector local_normal_at(Point p, Intersection i) {
		return i.object.local_normal_at(p, i);
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
        return this.objects.contains(object);
	}
	
	
	
	
	
	
	
}
