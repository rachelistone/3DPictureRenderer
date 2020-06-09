/**
 * 
 */
package primitives;

/**
 * Material class defines the kind of material that the geometry is made of it
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public class Material {

	/**
	 * the coefficient of the diffuse of the material
	 */
	double _kD;

	/**
	 * the coefficient of the the specular
	 */
	double _kS;

	/**
	 * the coefficient of the the reflection
	 */
	double _kR;
	
	/**
	 * the coefficient of the the refraction
	 */
	double _kT;

	/**
	 * the shininess
	 */
	int _nShininess;

	/**
	 * constructor initialized the fields of the class
	 * 
	 * @param _kD the coefficient of the diffuse
	 * @param _kS the coefficient of the specular
	 * @param _kR the coefficient of the reflection
	 * @param _kT the coefficient of the refraction
	 * @param _nShininess shininess
	 */
	public Material(double kD, double kS, int nShininess, double kT, double kR) {
		_kD = kD;
		_kS = kS;
		_kR = kR;
		_kT = kT;
		_nShininess = nShininess;
	}
	
	/**
	 * constructor initialized the fields of the class
	 * 
	 * @param _kD         diffuse
	 * @param _kS         specular
	 * @param _nShininess shininess
	 */
	public Material(double kD, double kS, int nShininess) {
		 this(kD, kS, nShininess, 0.0, 0.0);
	}

	/**
	 * @return the _kD
	 */
	public double get_kD() {
		return _kD;
	}

	/**
	 * @return the _kS
	 */
	public double get_kS() {
		return _kS;
	}

	/**
	 * @return the _kR
	 */
	public double get_kR() {
		return _kR;
	}

	/**
	 * @return the _kT
	 */
	public double get_kT() {
		return _kT;
	}

	/**
	 * @return the _nShininess
	 */
	public int get_nShininess() {
		return _nShininess;
	}

}
