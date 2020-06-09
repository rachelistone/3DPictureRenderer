/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class spotLight defines light that goes only in a specific direction and has
 * position and attenuation
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public class SpotLight extends PointLight {

	/**
	 * the direction of the light source
	 */
	private Vector _direction;

	/**
	 * Constructor setting all the fields
	 * 
	 * @param direction the direction vector of the light source
	 * @param intensity the color of the light source
	 * @param position  the position of the light source
	 * @param kC        coefficient of attenuation
	 * @param kL        coefficient of attenuation
	 * @param kQ        coefficient of attenuation
	 */
	public SpotLight(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
		super(intensity, position, kC, kL, kQ);
		_direction = direction.normalized();
	}

	@Override
	public Color getIntensity(Point3D point) {
		// the dot product between the direction of the light source and the direction
		// vector from the position of the light to the point
		double product = _direction.dotProduct(super.getL(point));
		// if the vector of the light source does not reaches the point -> no affect from this light source
		if(product < 0) {
			return new Color(0, 0, 0);
		} else 
			// add the attenuation of the direction
			return super.getIntensity(point).scale(product);
	}

}
