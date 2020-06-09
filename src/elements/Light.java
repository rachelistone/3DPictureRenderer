/**
 * 
 */
package elements;

import primitives.Color;

/**
 * Light is the father class which all the light source generalizes from
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public abstract class Light {

	/**
	 * the intensity of the light
	 */
	protected Color _intensity;
	
	public Light(Color intensity) {
		_intensity = intensity;
	}

	/**
	 * @return the _intensity
	 */
	public Color get_intensity() {
		return _intensity;
	}

}
