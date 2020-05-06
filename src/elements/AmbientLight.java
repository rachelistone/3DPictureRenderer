/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author User
 *
 */
public class AmbientLight {

	/**
	 * 
	 */
	private Color _Intensity;

	/**
	 * 
	 */
	public AmbientLight(double ka, Color Ia) {
		// TODO Auto-generated constructor stub
		_Intensity = Ia.scale(ka);
	}
	
	/**
	 * @return the _Intensity
	 */
	public Color getIntensity() {
		return _Intensity;
	}

}
