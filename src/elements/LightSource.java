/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * the interface LightSource gives the intensity and the direction of any light
 * source in a specific point
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public interface LightSource {

	/**
	 * gives the intensity of the light source in the point
	 * 
	 * @param point the point to get the color there
	 * @return the intensity
	 */
	public Color getIntensity(Point3D point);

	/**
	 * gives the direction vector of the light source to the point
	 * 
	 * @param point which the vector goes to 
	 * @return the direction vector
	 */
	public Vector getL(Point3D point);
	
	/**
	 * find the distance between the light source to the point
	 * 
	 * @param point to find the distance from
	 * @return the distance
	 */
	public double getDistance(Point3D point);

}
