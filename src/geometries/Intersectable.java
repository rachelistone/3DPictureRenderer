/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * 
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public interface Intersectable {
	public List<Point3D> findIntersections(Ray ray);
}
