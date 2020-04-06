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
	 * it takes 2 vectors from the vertices and calculates their normal to the plane
	 * 
	 * @param p0 the first point 
	 * @param p1 the second point 
	 * @param p2 the third point 
	 */
	public Plane(Point3D p0, Point3D p1, Point3D p2) {
		_p = new Point3D(p0);
		Vector v1 = p1.subtract(p0);
		Vector v2 = p2.subtract(p0);
		if (v1.normalize() == v2.normalize())
			throw new IllegalArgumentException("there are two vertices on the same line");
		_normal = v1.crossProduct(v2).normalize();
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
		Vector v = point.subtract(_p);
		if (v.dotProduct(_normal) != 0)
			throw new IllegalArgumentException("the point is not on the plane");
		return _normal;
	}

}
