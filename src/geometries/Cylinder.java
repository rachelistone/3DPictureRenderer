/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author User
 *
 */
public class Cylinder extends Tube {
	
	/**
	 *
	 */
	protected double _height;

	/**
	 * @param radius
	 * @param axisRay
	 */
	public Cylinder(double radius, Ray axisRay, double height) {
		super(radius, axisRay);
		_height = height;
	}
	
	/**
	 * @return the _axisRay
	 */
	public double get__height() {
		return _height;
	}

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "" + _height + " " + super.toString();
	}
}
