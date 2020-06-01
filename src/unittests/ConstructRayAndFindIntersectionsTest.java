/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author User
 *
 */
public class ConstructRayAndFindIntersectionsTest {

	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 * Test method for
	 * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
	 */
	@Test
	public void ConstructRayWithSphere() {

		// TC01: the radius of the sphere is one and after the view plane (2 points)
		Sphere sphere = new Sphere(new Point3D(0, 0, 3), 1d);
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
		int result = 0;
		List<GeoPoint> temp;
		int i;
		int j;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				temp = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 6, 6));
				// if there are intersection points between a specific ray and the sphere ->
				// find the number of points
				if (temp != null) {
					result += temp.size();
				}
			}
		}
		assertEquals("wrong number of intersections", 2, result);

		// TC02: the radius of the sphere is 2.5 and is at (0, 0, 2.5) the view plane in
		// distance
		// 1(18 points)
		sphere = new Sphere(new Point3D(0, 0, 2.5), 2.5d);
		camera = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
		result = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				temp = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				// if there are intersection points between a specific ray and the sphere ->
				// find the number of points
				if (temp != null) {
					result += temp.size();
				}
			}
		}
		assertEquals("wrong number of intersections", 18, result);

		// TC03: the radius of the sphere is 2 and the center is at (0, 0, 2), the view
		// plane in distance
		// 1(10 points)
		sphere = new Sphere(new Point3D(0, 0, 2), 2d);
		camera = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
		result = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				temp = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				// if there are intersection points between a specific ray and the sphere ->
				// find the number of points
				if (temp != null) {
					result += temp.size();
				}
			}
		}
		assertEquals("wrong number of intersections", 10, result);

		// TC04: the radius of the sphere is 4 and the center is at (0, 0, 1), the view
		// plane in distance 1(9 points)
		sphere = new Sphere(new Point3D(0, 0, 1), 4d);
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		result = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				temp = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				// if there are intersection points between a specific ray and the sphere ->
				// find the number of points
				if (temp != null) {
					result += temp.size();
				}
			}
		}
		assertEquals("wrong number of intersections", 9, result);

		// TC05: the radius of the sphere is 0.5 and the center is at (0, 0, -1), the
		// view
		// plane in distance 1(0 points)
		sphere = new Sphere(new Point3D(0, 0, -1), 0.5d);
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		result = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				temp = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				// if there are intersection points between a specific ray and the sphere ->
				// find the number of points
				if (temp != null) {
					result += temp.size();
				}
			}
		}
		assertEquals("wrong number of intersections", 0, result);
	}

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 * Test method for
	 * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
	 */
	@Test
	public void ConstructRayWithPlane() {
		Plane plane;
		Camera camera;
		int result;
		int i;
		int j;
		List<GeoPoint> temp;
		// TC01 the plane is parallel to the view plane, the view plane distance from
		// the camera is 1(9 points)
		plane = new Plane(new Point3D(0, 0, 2), new Vector(0, 0, 1));
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		result = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				temp = plane.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				// if there are intersection points between a specific ray and the plane ->
				// find the number of points
				if (temp != null) {
					result += temp.size();
				}
			}
		}
		assertEquals("wrong number of intersections", 9, result);

		// TC02 the plane is inclined , the view plane distance from
		// the camera is 1(9 points)
		plane = new Plane(new Point3D(0, 0, 4), new Vector(0, -1, 2));
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		result = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				temp = plane.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				// if there are intersection points between a specific ray and the plane ->
				// find the number of points
				if (temp != null) {
					result += temp.size();
				}
			}
		}
		assertEquals("wrong number of intersections", 9, result);

		// TC03 the plane is inclined , the view plane distance from
		// the camera is (6 points)
		plane = new Plane(new Point3D(0, 0, 4), new Vector(0, -1, 1));
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		result = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				temp = plane.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				// if there are intersection points between a specific ray and the plane ->
				// find the number of points
				if (temp != null) {
					result += temp.size();
				}
			}
		}
		assertEquals("wrong number of intersections", 6, result);

	}
	
	/**
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 * Test method for
	 * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
	 */
	@Test
	public void ConstructRayWithTriangle() {
		Triangle triangle;
		Camera camera;
		int result;
		int i;
		int j;
		List<GeoPoint> temp;
		
		//TC01: the triangle is behind the mid pixel
		triangle = new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		result = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				temp = triangle.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				// if there are intersection points between a specific ray and the plane ->
				// find the number of points
				if (temp != null) {
					result += temp.size();
				}
			}
		}
		assertEquals("wrong number of intersections", 1, result);
		
		//TC02: the triangle is behind the mid pixel and the pixel above it
		triangle = new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
		camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		result = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				temp = triangle.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				// if there are intersection points between a specific ray and the plane ->
				// find the number of points
				if (temp != null) {
					result += temp.size();
				}
			}
		}
		assertEquals("wrong number of intersections", 2, result);
	}
}
