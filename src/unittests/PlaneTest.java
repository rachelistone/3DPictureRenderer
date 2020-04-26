/**
 * 
 */
package unittests;
import geometries.Plane;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.Plane class
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
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
	

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(new Point3D(0, 1, 0), new Vector(0, 1, 0));
        List<Point3D> result; 
        // ============ Equivalence Partitions Tests ==============
        Vector vector = new Vector(1, 2, 0);
        //TC 01: instance when the ray intersects the plane and not orthogonal
        result = plane.findIntersections(new Ray(new Point3D(0, 0, 0), vector));
        assertEquals("ray should intersect the plane", List.of(new Point3D(0.5, 1, 0)), result);
        
        //TC 02: instance when the ray not intersects the plane and not orthogonal
        result = plane.findIntersections(new Ray(new Point3D(1, 2, 0), vector));
        assertEquals("ray should not intersect the plane", null, result); 
        
        // =============== Boundary Values Tests ==================
        //group of parallel ray cases
        //TC03: the ray is parallel to the plane and the source is on the plane
        vector = new Vector(1, 0, 0);
        result = plane.findIntersections(new Ray(new Point3D(0, 1, 0), vector));
        assertEquals("ray should not intersect the plane", null, result); 
        
        //TC04: the ray is parallel to the plane 
        result = plane.findIntersections(new Ray(new Point3D(0, 2, 0), vector));
        assertEquals("ray should not intersect the plane", null, result); 
	
        //group of orthogonal ray cases
        vector = new Vector(0, 1, 0);
        //TC05: the ray is orthogonal to the plane and the source point is before the plane (under)
        result = plane.findIntersections(new Ray(new Point3D(0, 0, 0), vector));
        assertEquals("ray should intersect the plane", List.of(new Point3D(0, 1, 0)), result); 
        
        //TC06: the ray is orthogonal to the plane and the source point is on the plane
        result = plane.findIntersections(new Ray(new Point3D(0, 1, 0), vector));
        assertEquals("ray should not intersect the plane", null, result); 
        
        //TC07: the ray is orthogonal to the plane and the source point is after the plane (above)
        result = plane.findIntersections(new Ray(new Point3D(0, 2, 0), vector));
        assertEquals("ray should not intersect the plane", null, result); 
        
        //group of rays that is not orthogonal an not parallel and the source point of the ray is on the plane
        vector = new Vector(1, 2, 0);
        //TC08: the source point is the reference point of the plane (Q0)
        result = plane.findIntersections(new Ray(new Point3D(0, 1, 0), vector));
        assertEquals("ray should not intersect the plane", null, result); 
        
        //TC09: the source point is any other except of the reference point on the plane (Q0)
        result = plane.findIntersections(new Ray(new Point3D(0.5, 1, 0), vector));
        assertEquals("ray should not intersect the plane", null, result); 
    }
        
}
