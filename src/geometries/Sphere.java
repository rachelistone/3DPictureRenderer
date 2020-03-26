/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * @author User
 *
 */
public class Sphere extends RadialGeometry {

	/**
	 * 
	 */
	protected Point3D _center;
	
	/**
	 * @param radius
	 */
	public Sphere(Point3D center, double radius) {
		super(radius);
		_center = new Point3D(center);
	}


	/**
	 * @return the _center
	 */
	public Point3D get_center() {
		return _center;
	}


	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String toString() {
		return "" + _center + " " + super.toString();
	}

}
