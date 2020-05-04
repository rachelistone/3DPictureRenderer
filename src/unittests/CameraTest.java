package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.Camera;
import primitives.*;

/**
 * Testing Camera Class
 * 
 * @author Dan
 *
 */
public class CameraTest {

	/**
	 * Test method for
	 * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
	 */
	@Test
	public void testConstructRayThroughPixel() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));

		// ============ Equivalence Partitions Tests ==============
		// TC01: 3X3 Corner (0,0)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, -2, 10)),
				camera.constructRayThroughPixel(3, 3, 0, 0, 10, 6, 6));

		// TC02: 4X4 Corner (0,0)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-3, -3, 10)),
				camera.constructRayThroughPixel(4, 4, 0, 0, 10, 8, 8));

		// TC03: 4X4 Side (0,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -3, 10)),
				camera.constructRayThroughPixel(4, 4, 1, 0, 10, 8, 8));

		// TC04: 4X4 Inside (1,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -1, 10)),
				camera.constructRayThroughPixel(4, 4, 1, 1, 10, 8, 8));

		// TC05: 3X4 the corner (0,0)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-3, -2, 10)),
				camera.constructRayThroughPixel(4, 3, 0, 0, 10, 8, 6));

		// TC06: 3X4 the side (0,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -2, 10)),
				camera.constructRayThroughPixel(4, 3, 1, 0, 10, 8, 6));

		// TC07: 4X3 the corner (0,0)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, -3, 10)),
				camera.constructRayThroughPixel(3, 4, 0, 0, 10, 6, 8));

		// =============== Boundary Values Tests ==================

		// TC08: 3X4 the Inside (1,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, 0, 10)),
				camera.constructRayThroughPixel(4, 3, 1, 1, 10, 8, 6));

		// TC9: 4X3 the corner (0,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, -3, 10)),
				camera.constructRayThroughPixel(3, 4, 1, 0, 10, 6, 8));
		
		// TC10: 4X3 the corner (1,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, -1, 10)),
				camera.constructRayThroughPixel(3, 4, 1, 1, 10, 6, 8));

		// TC11: 3X3 Center (1,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, 0, 10)),
				camera.constructRayThroughPixel(3, 3, 1, 1, 10, 6, 6));

		// TC12: 3X3 Center of Upper Side (0,1)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, -2, 10)),
				camera.constructRayThroughPixel(3, 3, 1, 0, 10, 6, 6));

		// TC13: 3X3 Center of Left Side (1,0)
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, 0, 10)),
				camera.constructRayThroughPixel(3, 3, 0, 1, 10, 6, 6));

	}

}
