/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class Cylinder represents a tube with defined height inherits from Tube class
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public class Cylinder extends Tube {
	
	/**
	 * the height of the Cylinder
	 */
	protected double _height;

	/**
	 * constructor receiving the radius and the axis ray of the cylinder center and the height   
	 * 
	 * @param radius the distance from the center axis to the bordure
	 * @param axisRay the axis of the cylinder center
	 * @param height the height of the cylinder
	 */
	public Cylinder(double radius, Ray axisRay, double height) {
		super(radius, axisRay);
		_height = height;
	}
	
	/**
	 * getter to the axis ray
	 * 
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
