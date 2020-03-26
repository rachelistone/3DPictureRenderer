/**
 * 
 */
package geometries;

/**
 * class RadialGeometry is an abstract class represents a shape with radius
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public abstract class RadialGeometry implements Geometry {

	/**
	 * the radius
	 */
	protected double _radius;

	/**
	 * a constructor receiving radius
	 * 
	 * @param radius the value of the radius
	 */
	public RadialGeometry(double radius) {
		_radius = radius;
	}
	
	/**
	 * copy constructor
	 * 
	 * @param other the radial geometry to copy from
	 */
	public RadialGeometry(RadialGeometry other) {
		_radius = other.get_radius();
	}
	
	/**
	 * getter to the radius
	 * 
	 * @return the radius
	 */
	public double get_radius() {
		return _radius;
	}

}
