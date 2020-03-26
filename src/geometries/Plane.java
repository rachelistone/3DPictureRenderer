package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * class plane represents a plane in a cartesian system by saving a 3D point on
 * the plane and a normal vector
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public class Plane implements Geometry {
	/**
	 * a point on the plane   
	*/
	protected Point3D _p;

	/**
	 * the normal vector for the plane    
	*/
	protected Vector _normal;

	/**
	 * constructor receiving three 3D points dissenters the plane
	 * 
	 * @param p0 the first point 
	 * @param p1 the second point 
	 * @param p2 the third point 
	 */
	public Plane(Point3D p0, Point3D p1, Point3D p2) {
		_p = new Point3D(p0);
		_normal = null;
	}

	/**
	 * constructor receiving the fields, point on the plane and a normal 
	 */
	public Plane(Point3D p0, Vector normal) {
		_p = new Point3D(p0);
		_normal = new Vector(normal);
	}

	/**
	 * getter to the point on the plane
	 * 
	 * @return the _p
	 */
	public Point3D get_p() {
		return _p;
	}

	/**
	 * getter to the normal
	 * 
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
