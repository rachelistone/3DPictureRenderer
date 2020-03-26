package geometries;
import primitives.Ray;

import primitives.Point3D;
import primitives.Vector;

/**
 * class Tube represents a shape of pipe using radius and the axis ray of the tube center
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public class Tube extends RadialGeometry {

	/**
	 * the axis of the center of the tube
	 */
	protected Ray _axisRay;
	
	/**
	 * constructor receiving radius and axis ray of the tube center
	 * 
	 * @param radius
	 * @param axisRay the axiix ray of the tube center
	 */
	public Tube(double radius, Ray axisRay) {
		super(radius);
		_axisRay = new Ray(axisRay);
	}

	/**
	 * getter to the axis ray
	 * 
	 * @return the _axisRay
	 */
	public Ray get_axisRay() {
		return _axisRay;
	}

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "" + _axisRay + " " + super.toString();
	}

}
