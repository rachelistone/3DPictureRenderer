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
public class Plane implements Geometry {

	/**
	 * 
	 */
    protected Point3D _p;
	protected Vector _normal;
	
	/**
	 * 
	 */
	public Plane(Point3D p0, Point3D p1, Point3D p2) {
		_p = new Point3D(p0);
		_normal = null;
	}
	
	/**
	 * 
	 */
	public Plane(Point3D p0, Vector normal) {
		_p = new Point3D(p0);
		_normal = new Vector(normal);
	}

	/**
	 * @return the _p
	 */
	public Point3D get_p() {
		return _p;
	}

	/**
	 * @return the _normal
	 */
	public Vector getNormal() {
		return _normal;
	}

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

}
