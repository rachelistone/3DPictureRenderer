/**
 * 
 */
package unittests;
import geometries.Plane;
import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

/**
 * Unit tests for geometries.Plane class
 * 
 * @author Yochi Shtrauber & Rachel Stone
 *
 */
public class PlaneTest {

	@Test
	public void testGetNormal() {
		Plane p = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		// ============ Equivalence Partitions Tests ==============
		// simple test for Normal of the plane
		double sqrt3 = Math.sqrt(1d / 3);
		assertEquals("Bad normal to plane", new Vector(sqrt3, sqrt3, sqrt3), p.getNormal(new Point3D(0, 1, 0)));
	}

}
