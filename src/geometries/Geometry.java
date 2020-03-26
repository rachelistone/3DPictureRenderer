/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * @author User
 *
 */
public interface Geometry {
	
	/**
	 *
	 */
	public Vector getNormal(Point3D point);
}
