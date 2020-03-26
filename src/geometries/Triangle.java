package geometries;

import primitives.Point3D;

/**
 * class Triangle represents a triangle based on polygon but with 3 vertices
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public class Triangle extends Polygon {

	/**
	 * constructor receiving 3 vertices
	 * 
	 * @param p0 the first point
	 * @param p1 the second point
	 * @param p2 the third point
	 */
	public Triangle(Point3D p0, Point3D p1, Point3D p2) {
		super(p0, p1, p2);
	}

}
