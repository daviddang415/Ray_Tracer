import java.util.ArrayList;

public abstract class LightSource {
	
	protected MyColor intensity;
	protected Point position;
		
	public abstract MyColor intensityAt(
			Point point, 
			World w);

	public boolean isShadowed(Point p,  World w) {
		//fill out
		double[] position_vals = position.getT();
		double[] p_vals = p.getT();		

		double[] v_vals = new double[position_vals.length];

		for (int i = 0; i < v_vals.length; i++) {
			v_vals[i] = position_vals[i] - p_vals[i];
		}

		Vector v = new Vector(new Tuple(v_vals));

		Ray r = new Ray(p, v);

		ArrayList<Intersection> results = w.intersectWorld(r);
				
		Intersection temp = Traceable.hit(results);

		if (temp == null || temp.t > 1) {
			return false;
		} else {
			return true;
		}
	}

	public Vector getDirection(Point p, World w) {
		double[] position_vals = position.getT();
		double[] p_vals = p.getT();		

		double[] v_vals = new double[position_vals.length];

		for (int i = 0; i < v_vals.length; i++) {
			v_vals[i] = position_vals[i] - p_vals[i];
		}

		Vector v = new Vector(new Tuple(v_vals));
		return v;	
	}


	
}