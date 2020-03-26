/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * class Geometry is an interface to all of the shapes in the scene 
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public interface Geometry {
	
	/**
	 * computes the normal in a specific point
	 * 
	 * @param point where the normal vertical to the geometry
	 * @returns the normal vector
	 */
	public Vector getNormal(Point3D point);
}
