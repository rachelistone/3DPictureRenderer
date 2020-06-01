/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * class Intersectable is an interface to deal with intersections of geometry
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public interface Intersectable {

	/**
	 * findIntersections is a function that finds and return a list of the
	 * pairs of intersection point with the ray and the geometry
	 * 
	 * @param ray is the ray that the function finds the points that intersect it
	 * @return list of pairs of geometry and point
	 */
	public List<GeoPoint> findIntersections(Ray ray);

	/**
	 * GeoPoint is a class that defines a pair of geometry and point
	 * 
	 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
	 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
	 */
	public static class GeoPoint {

		/**
		 * geometry is the geometry that on its there is intersection point with the ray
		 */
		public Geometry _geometry;

		/**
		 * point is the point on the geometry that intersect the ray
		 */
		public Point3D _point;

		/**
		 * the constructor of GeoPoint is getting the geometry and the point
		 * 
		 * @param geometry is the geometry that on its there is intersection point with
		 *                 the ray
		 * @param point    is the point on the geometry that intersect the ray
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			_geometry = geometry;
			_point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			return _geometry == _geometry && _point.equals(other._point);
		}
	}

}
