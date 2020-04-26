/**
 * 
 */
package geometries;

import java.util.Iterator;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * class geometries is a composite class that treat list of geometries
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public class Geometries implements Intersectable {

	/**
	 * list of geometries
	 */
	List<Intersectable> _geometries;

	/**
	 * default constructor
	 */
	public Geometries() {
		// TODO Auto-generated constructor stub
		_geometries = null;
	}

	/**
	 * constructor for a collection of geometries
	 * 
	 * @param collection of geometries
	 */
	public Geometries(Intersectable... geometries) {
		_geometries = List.of(geometries);
	}

	/**
	 * appends a collection of geometries to an exist list
	 * 
	 * @param geometries the collection to add
	 */
	public void add(Intersectable... geometries) {
		for (int i = 1; i < geometries.length; ++i) {
			_geometries.add(geometries[i]);
		}
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		List<Point3D> result = null;
		//if no geometries in the scene
		if (_geometries != null) {
			Iterator<Intersectable> iterator = _geometries.iterator();
			while (iterator.hasNext()) {
				if (result == null) {
					result = iterator.next().findIntersections(ray);
				} else {
					result.addAll(iterator.next().findIntersections(ray));/////////////////////////////
					// should add the Point (3.5, 1, 0) that comes from the plane find intersection
				}
			}
			return result;
		}
		return null;
	}

}
