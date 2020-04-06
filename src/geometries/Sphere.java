package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * class Sphere represents a ball by saving a center point and radius inherited from RadialGeometry
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public class Sphere extends RadialGeometry {

	/**
	 * the center point of the sphere
	 */
	protected Point3D _center;
	
	/**
	 * constructor receiving a center point and the radius 
	 * 
	 * @param radius
	 */
	public Sphere(Point3D center, double radius) {
		super(radius);
		_center = new Point3D(center);
	}


	/**
	 * getter to the center point 
	 * 
	 * @return the _center
	 */
	public Point3D get_center() {
		return _center;
	}


	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		Vector v = point.subtract(_center);
		if (v.length() != _radius)
			throw new IllegalArgumentException("the point is not on the sphere");
		return v.normalize();
	}


	@Override
	public String toString() {
		return "" + _center + " " + super.toString();
	}

}
