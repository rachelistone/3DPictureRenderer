/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Triangle;
import geometries.Intersectable.GeoPoint;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.triangle class
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 *
 */
public class TriangleTest {

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Triangle triangle = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		// ============ Equivalence Partitions Tests ==============
		// TC01: simple test for point if on the triangle
		double sqrt3 = Math.sqrt(1d / 3);
		assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3),
				triangle.getNormal(new Point3D(0, 1, 0)));
	}

	/**
	 * Test method for
	 * {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Triangle triangle = new Triangle(new Point3D(0, 1, 0), new Point3D(0, 3, 0), new Point3D(1, 2, 0));
		// ============ Equivalence Partitions Tests ==============
		// TC01: the intersection point is inside the triangle
		assertEquals("the ray should intersect the triangle", List.of(new GeoPoint(triangle, new Point3D(0.5, 2, 0))),
				triangle.findIntersections(new Ray(new Point3D(0.5, 2, -1), new Vector(0, 0, 1))));
		// TC02: the intersections point is out of the triangle and against a rib of the
		// triangle
		assertEquals("the ray should not intersect the triangle ", null,
				triangle.findIntersections(new Ray(new Point3D(0, 2, -1), new Vector(1, 1, 1))));
		// TC03: the intersections point is out of the triangle and against a vertex of
		// the triangle
		assertEquals("the ray should not intersect the triangle ", null,
				triangle.findIntersections(new Ray(new Point3D(2, 2, -1), new Vector(0, 0, 1))));

		// =============== Boundary Values Tests ==================
		// TC04: the intersections point is on the rib of the triangle
		assertEquals("the ray should not intersect the triangle ", null,
				triangle.findIntersections(new Ray(new Point3D(0, 2, -1), new Vector(0, 0, 1))));
		// TC05: the intersections point is on the vertex of the triangle
		assertEquals("the ray should not intersect the triangle ", null,
				triangle.findIntersections(new Ray(new Point3D(0, 1, -1), new Vector(0, 0, 1))));
		// TC06: the intersections point is on the continuous of the rib of the triangle
		assertEquals("the ray should not intersect the triangle ", null,
				triangle.findIntersections(new Ray(new Point3D(0, 4, -1), new Vector(0, 0, 1))));
	}
}
