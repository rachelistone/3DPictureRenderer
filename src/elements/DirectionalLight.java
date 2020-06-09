/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class Directional Light defines a light source which has only direction
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public class DirectionalLight extends Light implements LightSource {

	/**
	 * the direction vector of the light source
	 */
	private Vector _direction;
	
	/**
	 * constructor initializes the fields
	 * 
	 * @param intensity the color of the light source
	 * @param the direction of the light source
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		_direction = direction.normalized();
	}

	@Override
	public Color getIntensity(Point3D point) {
		// TODO Auto-generated method stub
		return _intensity;
	}

	@Override
	public Vector getL(Point3D point) {
		// TODO Auto-generated method stub
		return _direction;
	}
	
	@Override
	public double getDistance(Point3D point) {
		return Double.POSITIVE_INFINITY;
	}

}
