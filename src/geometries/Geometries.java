/**
 * 
 */
package geometries;

import java.util.ArrayList;
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
		_geometries = new ArrayList();
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
		for (int i = 0; i < geometries.length; ++i) {
			_geometries.add(geometries[i]);
		}
	}

	@Override
	public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
		// TODO Auto-generated method stub
		List<GeoPoint> result = null;
		// if no geometries in the scene
		if (_geometries != null) {
			Iterator<Intersectable> iterator = _geometries.iterator();
			while (iterator.hasNext()) {
				List<GeoPoint> tempList = iterator.next().findIntersections(ray, maxDistance);
				//if there are intersection points for a specific geometry in the list
				if (tempList != null) {
					//if it is the first list of intersection point -> build the list
					if (result == null) {
						result = new ArrayList<GeoPoint>();
					}
					//append the list from that geometry to the result list
					result.addAll(tempList);
				}
			}
			return result;
		}
		return null;
	}
}
