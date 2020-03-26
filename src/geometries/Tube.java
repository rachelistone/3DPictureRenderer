package geometries;
import primitives.Ray;

import primitives.Point3D;
import primitives.Vector;

/**
 * @author User
 *
 */
public class Tube extends RadialGeometry {

	/**
	 * 
	 */
	protected Ray _axisRay;
	
	/**
	 * @param radius
	 */
	public Tube(double radius, Ray axisRay) {
		super(radius);
		_axisRay = new Ray(axisRay);
	}

	/**
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
