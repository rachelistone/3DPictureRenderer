/**
 * 
 */
package geometries;

/**
 * @author User
 *
 */
public abstract class RadialGeometry implements Geometry {

	/**
	 * 
	 */
	protected double _radius;

	/**
	 * 
	 */
	public RadialGeometry(double radius) {
		_radius = radius;
	}
	
	/**
	 * 
	 */
	public RadialGeometry(RadialGeometry other) {
		_radius = other.get_radius();
	}
	
	public double get_radius() {
		return _radius;
	}

}
