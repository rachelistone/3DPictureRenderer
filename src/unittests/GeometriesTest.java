/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.Geometries class
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 *
 */
public class GeometriesTest {

	/**
	 * Test method for
	 * {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Triangle triangle = new Triangle(new Point3D(0, 2, 2), new Point3D(3, 2, 2), new Point3D(1.5, 5, 2));
		Sphere sphere = new Sphere(new Point3D(3, 2, 0), 1d);
		Plane plane = new Plane(new Point3D(0, 1, 0), new Vector(0, 1, 0));
		Geometries geometries = new Geometries(triangle, sphere, plane);

		// ============ Equivalence Partitions Tests ==============
		// TC01: some of the geometries intersected, but not all of them
		assertEquals("the number of the intersections is not correct", 3,
				geometries.findIntersections(new Ray(new Point3D(3.5, 0, 0), new Vector(0, 1, 0))).size());

		// =============== Boundary Values Tests ==================
		// TC02: geometry list is empty
		assertEquals("the number of the intersections is not correct", null,
				new Geometries().findIntersections(new Ray(new Point3D(0, 0, -1), new Vector(0, 0, 1))));

		// TC03: no geometry has intersected by the ray
		assertEquals("the number of the intersections is not correct", null,
				geometries.findIntersections(new Ray(new Point3D(0, 0, -1), new Vector(0, 0, 1))));

		// TC04: only one of the geometries intersected
		assertEquals("the number of the intersections is not correct", 1,
				geometries.findIntersections(new Ray(new Point3D(3, 1, 0), new Vector(0, 1, 0))).size());

		// TC05: the ray intersects all the geometries of the collection
		assertEquals("the number of the intersections is not correct", 4,
				geometries.findIntersections(new Ray(new Point3D(2, 4, 3), new Vector(0, -2, -3))).size());
	}

}
