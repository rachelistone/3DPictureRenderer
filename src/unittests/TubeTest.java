/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.Tube class
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 *
 */
public class TubeTest {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		Tube tube = new Tube(1, new Ray(Point3D.ZERO, new Vector(0, 1, 0)));
		
		//TC01: when the vector between the point and source of the axis is rectangle to the axis
		assertEquals("bad normal to tube", new Vector(1, 0, 0), tube.getNormal(new Point3D(1, 0, 0)));
		
		//TC02: the simple case of a point
		assertEquals("bad normal to tube", new Vector(1 , 0, 0), tube.getNormal(new Point3D(1, 4, 0)));
		
		//TC03 when the point is not on the tube
		assertEquals("doesn't return null for a that is not on the tube", tube.getNormal(new Point3D(7,7,7)),null);
	}

}
