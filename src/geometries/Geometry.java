/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * class Geometry is an interface to all of the shapes in the scene 
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public interface Geometry extends Intersectable {
	
	/**
	 * computes the normal in a specific point
	 * 
	 * @param point where the normal vertical to the geometry
	 * @returns the normal vector
	 */
	public Vector getNormal(Point3D point);
}
