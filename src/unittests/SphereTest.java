/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Sphere;
import primitives.Point3D;
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
		Sphere s2 = new Sphere(new Point3D(1,1,1), 1);
	
		// tests for 6 direction of the space 
		assertEquals("bad normal to sphere", new Vector(1, 0, 0), s1.getNormal(new Point3D(4, 0, 0)));
		assertEquals("bad normal to sphere", new Vector(-1, 0, 0), s1.getNormal(new Point3D(-4, 0, 0)));
		assertEquals("bad normal to sphere", new Vector(0, 1, 0), s1.getNormal(new Point3D(0, 4, 0)));
		assertEquals("bad normal to sphere", new Vector(0, -1, 0), s1.getNormal(new Point3D(0, -4, 0)));
		assertEquals("bad normal to sphere", new Vector(0, 0, 1), s1.getNormal(new Point3D(0, 0, 4)));
		assertEquals("bad normal to sphere", new Vector(0, 0, -1), s1.getNormal(new Point3D(0, 0, -4)));
		
		// test for a Sphere which its center in (1, 1, 1)
		assertEquals("bad normal to sphere", new Vector(0,0,-1), s2.getNormal(new Point3D(1,1,0)));
	    assertEquals("bad normal to sphere", new Vector(-1,0,0), s2.getNormal(new Point3D(0,1,1)));
	    assertEquals("bad normal to sphere", new Vector(0,-1,0), s2.getNormal(new Point3D(1,0,1)));

	    //when the point is not on the sphere
	    assertEquals(s1.getNormal(new Point3D(7,7,7)),null);
	}

}
