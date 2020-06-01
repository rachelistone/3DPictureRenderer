package elements;

import primitives.Color;

/**
 * the class AmbientLight defines the base color of the scene
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public class AmbientLight extends Light{

	/**
	 * constructor getting the coefficient of the attenuation and the intensity of the ambient light and scales them
	 * 
	 * @param ka the coefficient of the attenuation 
	 * @param Ia the intensity of the ambient light
	 */
	public AmbientLight(double ka, Color Ia) {
		super(Ia.scale(ka));
	}

}
