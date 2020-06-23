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
	private double _kD;

	/**
	 * the coefficient of the the specular
	 */
	private double _kS;

	/**
	 * the coefficient of the the reflection
	 */
	private double _kR;

	/**
	 * the coefficient of the the refraction
	 */
	private double _kT;

	/**
	 * the shininess
	 */
	private int _nShininess;

	/**
	 * the glossyDistance of the geometry as _glosssy has higher values the heads of
	 * the rays in the group of rays is farther from their source, it is more sharp,
	 * the circles of the rays' Group closer to the geometry as dlossyDistance is
	 * lower, 0 means no glossy(only one reflected ray = super sharp mirroring
	 */
	private double _glossyDistance;

	/**
	 * the radius of the diffusion of the glossy rays' head
	 */
	private double _glossyRadius;
	
	/**
	 * 
	 */
	private double  _diffDistance;
	
	/**
	 * the radius of the diffusion of the refractions rays' head
	 */
	private double _diffRadius;

	/**
	 * constructor initialized the fields of the class
	 * 
	 * @param kD         the coefficient of the diffuse
	 * @param kS         the coefficient of the specular
	 * @param nShininess shininess
	 * @param kR         the coefficient of the reflection
	 * @param kT         the coefficient of the refraction
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
	 * 
	 * @param glossyDistance
	 * @param glossyRadius
	 * @return
	 */
	public Material setGlossy(double glossyDistance, double glossyRadius) {
		if (glossyDistance <= 0) {
			throw new IllegalArgumentException("distance must be bigger then zero");
		}
		_glossyDistance = glossyDistance;

		if (glossyRadius < 0) {
			throw new IllegalArgumentException("radius must be positive");
		}
		_glossyRadius = glossyRadius;
		return this;
	}
	
	/**
	 * @return the _diffDistance
	 */
	public double get_diffDistance() {
		return _diffDistance;
	}

	/**
	 * @return the _diffRadius
	 */
	public double get_diffRadius() {
		return _diffRadius;
	}

	/**
	 * 
	 * @param diffDistance
	 * @param diffRadius
	 * @return
	 */
	public Material setDiffusion(double diffDistance, double diffRadius) {
		if (diffDistance <= 0) {
			throw new IllegalArgumentException("distance must be bigger then zero");
		}
		_diffDistance = diffDistance;

		if (diffRadius < 0) {
			throw new IllegalArgumentException("radius must be positive");
		}
		_diffRadius = diffRadius;
		return this;
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

	/**
	 * @return the _glossyDistance
	 */
	public double get_glossyDistance() {
		return _glossyDistance;
	}

	/**
	 * @return the _glossyRadius
	 */
	public double get_glossyRadius() {
		return _glossyRadius;
	}

}
