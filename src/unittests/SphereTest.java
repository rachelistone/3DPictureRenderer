/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.Sphere class
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 *
 */
public class SphereTest {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		Sphere s1 = new Sphere(Point3D.ZERO, 4);
		Sphere s2 = new Sphere(new Point3D(1, 1, 1), 1);

		// tests for 6 direction of the space
		assertEquals("bad normal to sphere", new Vector(1, 0, 0), s1.getNormal(new Point3D(4, 0, 0)));
		assertEquals("bad normal to sphere", new Vector(-1, 0, 0), s1.getNormal(new Point3D(-4, 0, 0)));
		assertEquals("bad normal to sphere", new Vector(0, 1, 0), s1.getNormal(new Point3D(0, 4, 0)));
		assertEquals("bad normal to sphere", new Vector(0, -1, 0), s1.getNormal(new Point3D(0, -4, 0)));
		assertEquals("bad normal to sphere", new Vector(0, 0, 1), s1.getNormal(new Point3D(0, 0, 4)));
		assertEquals("bad normal to sphere", new Vector(0, 0, -1), s1.getNormal(new Point3D(0, 0, -4)));

		// test for a Sphere which its center in (1, 1, 1)
		assertEquals("bad normal to sphere", new Vector(0, 0, -1), s2.getNormal(new Point3D(1, 1, 0)));
		assertEquals("bad normal to sphere", new Vector(-1, 0, 0), s2.getNormal(new Point3D(0, 1, 1)));
		assertEquals("bad normal to sphere", new Vector(0, -1, 0), s2.getNormal(new Point3D(1, 0, 1)));

		// when the point is not on the sphere
		assertEquals(s1.getNormal(new Point3D(7, 7, 7)), null);
	}

	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray's line is outside the sphere (0 points)
		assertEquals("Ray's line out of sphere", null,
				sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

		// TC02: Ray starts before and crosses the sphere (2 points)
		Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
		Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
		List<GeoPoint> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0)._point.get_x().get() > result.get(1)._point.get_x().get())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray crosses sphere", List.of(new GeoPoint(sphere, p1), new GeoPoint(sphere, p2)), result);

		// TC03: Ray starts inside the sphere (1 point)
		result = sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, 0), new Vector(3, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray not crosses sphere", List.of(new GeoPoint(sphere, p2)), result);

		// TC04: Ray starts after the sphere (0 points)
		result = sphere.findIntersections(new Ray(new Point3D(3.5, 1.5, 0), new Vector(3, 1, 0)));
		assertEquals("Ray crosses sphere", null, result);

		// =============== Boundary Values Tests ==================

		// **** Group: Ray's line crosses the sphere (but not the center)
		// TC11: Ray starts at sphere and goes inside (1 points)
		result = sphere.findIntersections(new Ray(new Point3D(p1), new Vector(3, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray not crosses the sphere at the right point", List.of(new GeoPoint(sphere, p2)), result);

		// TC12: Ray starts at sphere and goes outside (0 points)
		result = sphere.findIntersections(new Ray(new Point3D(p2), new Vector(3, 1, 0)));
		assertEquals("Ray should not crosse the sphere", null, result);

		// **** Group: Ray's line goes through the center
		// TC13: Ray starts before the sphere (2 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0)._point.get_y().get() > result.get(1)._point.get_y().get())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray not crosses the sphere at the right points",
				List.of(new GeoPoint(sphere, new Point3D(1, -1, 0)), new GeoPoint(sphere, new Point3D(1, 1, 0))), result);

		// TC14: Ray starts at sphere and goes inside (1 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray not crosses the sphere at the right point", List.of(new GeoPoint(sphere, new Point3D(1, 1, 0))), result);

		// TC15: Ray starts inside (1 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray not crosses the sphere at the right point", List.of(new GeoPoint(sphere, new Point3D(1, 1, 0))), result);

		// TC16: Ray starts at the center (1 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray not crosses the sphere at the right point", List.of(new GeoPoint(sphere, new Point3D(1, 1, 0))), result);

		// TC17: Ray starts at sphere and goes outside (0 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0)));
		assertEquals("Ray should not cross the sphere", null, result);

		// TC18: Ray starts after sphere (0 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0)));
		assertEquals("Ray should not cross the sphere", null, result);

		// **** Group: Ray's line is tangent to the sphere (all tests 0 points)
		// TC19: Ray starts before the tangent point
		result = sphere.findIntersections(new Ray(new Point3D(2, -1, 0), new Vector(0, 1, 0)));
		assertEquals("Ray should not cross the sphere with with tangent", null, result);

		// TC20: Ray starts at the tangent point
		result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0, 1, 0)));
		assertEquals("Ray should not cross the sphere with with tangent", null, result);

		// TC21: Ray starts after the tangent point
		result = sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(0, 1, 0)));
		assertEquals("Ray should not cross the sphere with with tangent", null, result);

		// **** Group: Special cases
		// TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
		// center line
		result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(0, 1, 0)));
		assertEquals("Ray should not cross the sphere when the Ray is outside the sphere "
				+ "and orthogonal to ray start to sphere's center line", null, result);

	}

}
