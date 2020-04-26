/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

/**
 * Unit tests for primitives.Vector class
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 *
 */
public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#Vector(primitives.Vector)}.
	 */
	@Test
	public void testConstructor() {
		// ============ Boundary Values Tests ==============

		// Correct the point is not zero
		try {
			new Vector(Point3D.ZERO);
			fail("Failed constructing a correct Vector");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		// ============ Equivalence Partitions Tests ==============
		// check that add operation is correct
		assertEquals("add() on Vector result is not correct", new Vector(1, 1, 1),
				new Vector(1, 2, 3).add(new Vector(0, -1, -2)));
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		// check that subtract operation is correct
		assertEquals("subtract() on Vector result is not correct", new Vector(1, 1, 1),
				new Vector(2, 3, 4).subtract(new Vector(1, 2, 3)));

	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		// ============ Equivalence Partitions Tests ==============
		// check the scalar multiply
		assertEquals("scale() on Vector result is not correct", new Vector(2, 4, 8), new Vector(1, 2, 4).scale(2));
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(1, 0, 0);

		// ============ Equivalence Partitions Tests ==============
		// TC01: partition of acute angle should be positive
		assertEquals("dotProduct() for acute angle between the vectors is not correct", 1,
				(int) v1.dotProduct(new Vector(1, 1, 0)));

		// TC02: partition of Dark angle
		assertEquals("dotProduct() for dark angle between the vectors is not correct", -1,
				(int) v1.dotProduct(new Vector(-1, 1, 0)));

		// =============== Boundary Values Tests ==================
		// TC03: test case when the vectors co-lined
		assertEquals("dotProduct() for co-lined vectors is not corect", 8, (int) v1.dotProduct(new Vector(8, 0, 0)));

		// TC04: test case when the vectors are orthogonal
		assertTrue("dotProduct() between the orthogonal vectors is not correct",
				primitives.Util.isZero(v1.dotProduct(new Vector(0, 1, 0))));

		// TC05: test case when the vectors are inverse
		assertEquals("dotProduct() for the inveraed vectors is not correct", -1,
				(int) v1.dotProduct(new Vector(-1, 0, 0)));
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -4, -6);

		// ============ Equivalence Partitions Tests ==============
		Vector v3 = new Vector(0, 3, -2);
		Vector vr = v1.crossProduct(v3);

		// TC01: Test that length of cross-product is proper (orthogonal vectors taken
		// for simplicity)
		assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

		// TC02: Test cross-product result orthogonality to its operands
		assertTrue("crossProduct() result is not orthogonal to 1st operand", primitives.Util.isZero(vr.dotProduct(v1)));
		assertTrue("crossProduct() result is not orthogonal to 2nd operand", primitives.Util.isZero(vr.dotProduct(v3)));

		// =============== Boundary Values Tests ==================
		// TC03: test zero vector from cross-product of co-lined vectors
		try {
			v1.crossProduct(v2);
			fail("crossProduct() for parallel vectors does not throw an exception");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		// test that check if the length's square of the vector is correct
		assertEquals("lengthSquare() is not correct", 21, (int) new Vector(1, 2, 4).lengthSquared());
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		// ============ Equivalence Partitions Tests ==============
		// test that check if the length of the vector is correct
		assertEquals("length() is not correct", 5, (int) new Vector(5, 0, 0).length());
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector v = new Vector(1, 2, 3);
		Vector vCopy = new Vector(v);
		Vector vCopyNormalize = vCopy.normalize();
		
		// ============ Equivalence Partitions Tests ==============
		// TC01: test that check if the normalized vector doesn't create new vector
		assertEquals("normalize() function creates a new vector", vCopyNormalize, vCopy);
		
		// TC02: test that the length is 1
		assertEquals("ERROR: normalize() result is not a unit vector", 1, (int)vCopyNormalize.length());

	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v = new Vector(1, 2, 3);
		Vector u = v.normalized();
		
		// ============ Equivalence Partitions Tests ==============
	    // TC01: test that check if the normalized vector create new vector
		assertTrue("normalizated() function does not create a new vector", !u.equals(v));

		// TC02: test that the length is 1
		assertEquals("ERROR: normalize() result is not a unit vector", 1, (int)u.length());
	}

}
