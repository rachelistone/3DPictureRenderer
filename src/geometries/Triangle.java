package geometries;

import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class Triangle represents a triangle based on polygon but with 3 vertices
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public class Triangle extends Polygon {

	
	/**
	 * constructor receiving 3 vertices
	 * 
	 * @param material the material of the geometry
	 * @param color the color of the triangle
	 * @param p0 the first point
	 * @param p1 the second point
	 * @param p2 the third point
	 */
	public Triangle(Material material, Color color, Point3D p0, Point3D p1, Point3D p2) {
		super(material, color, p0, p1, p2);
	}
	/**
	 * constructor receiving 3 vertices
	 * 
	 * @param color the color of the triangle
	 * @param p0 the first point
	 * @param p1 the second point
	 * @param p2 the third point
	 */
	public Triangle(Color color, Point3D p0, Point3D p1, Point3D p2) {
		this(new Material(0, 0, 0), color, p0, p1, p2);
	}
	

	/**
	 * constructor receiving 3 vertices
	 * 
	 * @param p0 the first point
	 * @param p1 the second point
	 * @param p2 the third point
	 */
	public Triangle(Point3D p0, Point3D p1, Point3D p2) {
		this(Color.BLACK, p0, p1, p2);
	}

	/**
	 * find the intersection point between the the ray and the triangle, if exist
	 * 
	 * @param ray to check if it intersects the triangle
	 * @return list of pairs of geometry and point
	 */
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> result = _plane.findIntersections(ray);
		// if the ray intersects the plane that the triangle on it
		if (result != null) {
			// two vectors that between the source of the ray and the vertexes
			Vector vector1;
			Vector vector2;
			// the vector that is orthogonal to the vector1 and vector2
			Vector normal;
			// the projection of the ray on the normal to the vectors
			double projection;
			// sign indicates the sign of the last projection
			boolean sign = false;

			// the loop passes between the ribs of the triangle
			for (int i = 0; i < 3; i++) {
				vector1 = _vertices.get(i).subtract(ray.get_p0());
				vector2 = _vertices.get((i + 1) % 3).subtract(ray.get_p0());
				normal = vector1.crossProduct(vector2).normalize();
				//if the ray is on the edge of the triangle
				if (ray.get_dir().dotProduct(normal) == 0) {
					return null;
				}
				projection = ray.get_dir().dotProduct(normal);
				// if this is the first rib, so save the sign of the projection
				// else checks if the sign of the the projection is the same as the last one
				if (i == 0) {
					sign = (projection > 0);
				} else {
					if (sign != (projection > 0))
						return null;
				}
			}
			// if all the signs of the projections are the same, so the point is on the
			// triangle,
			// so return the list of pairs of intersection points between the ray and the Triangle
			// that the triangle is on it
			return List.of(new GeoPoint(this, result.get(0)._point));
		} else
			return null;
	}

}
