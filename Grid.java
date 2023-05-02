import java.util.ArrayList;

public class Grid extends LightSource {

    protected Point[][][] pointGrid;

    public Grid(int dimensions, double width, MyColor intensity, Point point) {
        this.position = point;
        this.intensity = intensity;

        pointGrid = new Point[dimensions][dimensions][dimensions];
        double[] point_array = position.getT();
        
        double x = point_array[0];
        double y = point_array[1];
        double z = point_array[2];

        for (int i = 0; i < dimensions; i++) {
            x = x + width; 
            for (int j = 0; j < dimensions; j++) {
                y = y + width;
                for (int k = 0; k < dimensions; k++) {
                    z = z + width;
                    pointGrid[i][j][k] = new Point(x, y, z);
                }
                z = point_array[2];
            }
            y = point_array[1];
        }
    }

    public String toString() {
		return "Point light at "+this.position+" with intensity "+this.intensity;
	}
	
    public double gridIsShadowed(Point p, World w) {
            double numNotShadowed = 0;
            double numTotalLights = pointGrid.length*pointGrid.length*pointGrid.length;

            for (int i = 0; i < pointGrid.length; i++) {
                for (int j = 0; j < pointGrid.length; j++) {
                    for (int k = 0; k < pointGrid.length; k++) {

                        Point curPoint = pointGrid[i][j][k];
                        double[] position_vals = curPoint.getT();
                        double[] p_vals = p.getT();		
                
                        double[] v_vals = new double[position_vals.length];
                
                        for (int z = 0; z < v_vals.length; z++) {
                            v_vals[z] = position_vals[z] - p_vals[z];
                        }
                
                        Vector v = new Vector(new Tuple(v_vals));
                
                        Ray r = new Ray(p, v);
                
                        ArrayList<Intersection> results = w.intersectWorld(r);
                                
                        Intersection temp = Traceable.hit(results);
                
                        if (temp == null || temp.t > 1) {
                            numNotShadowed = numNotShadowed + 1;
                        }
                    }
                }
            }

            return numNotShadowed/numTotalLights;
    }

    @Override
	public MyColor intensityAt(Point point, 
			World w) {

			return intensity.scale(gridIsShadowed(point, w));

		}
    

	
	public MyColor getIntensity() {
		return intensity;
	}



	public void setIntensity(MyColor intensity) {
		this.intensity = intensity;
	}



	public Point getPosition() {
		return position;
	}



	public void setPosition(Point position) {
		this.position = position;
	}


	public static void main(String[] args) {
        Grid grid = new Grid(3, 0.1, new MyColor(1,1,1), new Point(0.5, 0, 2));
	}

}
