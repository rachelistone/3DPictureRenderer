package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * class Sphere represents a ball by saving a center point and radius inherited
 * from RadialGeometry
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
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
			return null;
		return v.normalize();
	}

	@Override
	public String toString() {
		return "" + _center + " " + super.toString();
	}
	
	 /**
	  * finds the intersection/s point/s between a ray and the sphere
	  * finds scalar t which multiply the ray to reach the intersection point if exist
	  * 
	  * @param ray to find the intersections
	  * @return list of the intersection points
	  */
	public List<Point3D> findIntersections(Ray ray){
		//a vector from the source of the ray to the center of the sphere
		Vector u;
		try {
		u = _center.subtract(ray.get_p0());
		// if the ray starts at the center of the sphere, u is zero vector and it can't be built
		} catch (IllegalArgumentException e) {
            return List.of(ray.getPoint(_radius));
        } 
		//the projection of u on the ray
		double tm = Util.alignZero(ray.get_dir().dotProduct(u));
		// d indicates the distance between the center of the sphere to the ray
		double d;
		if (Util.isZero(tm)){
			// u is vertical to the ray, the source of the ray is on the vertical line from the center to the ray
			d = u.length();
		}
		else {
		//the vertical to the middle of the base side of the triangle created by the radiuses to the intersection points
		//computed by pitagoras in the triangle created by tm and the length between the ray source and the center of the sphere
		d = Math.sqrt(u.lengthSquared() - tm*tm);
		}
		//if the ray is outside the sphere or tangent -> no intersection point
		if(d >= _radius)
			return null;
		else {
			//find the half of the chord between the intersection points, using pitagoras
			double th = Math.sqrt(_radius*_radius - d*d);
			// the source point of the ray is after the vertical line from the center to the ray , after the half of the chord
			if (tm < 0) {
				// if the ray begins on the edge of the sphere or after it -> no intersection points
				if (Util.isZero(Util.alignZero((-1*tm) - th)) || ((-1*tm) > th)) {
					return null;
				}
			}
			//if the source of the ray is inside the sphere -> only one intersection point, just add the half of the chord to scale the vector of the ray
			if (tm <= th) {
				return List.of(new Point3D(new Point3D(ray.getPoint(tm + th))));
			}
			// the list of the point computes by the scaled ray
			return List.of(new Point3D(ray.getPoint(tm - th)), new Point3D(ray.getPoint(tm + th)));
		}
	}

}
