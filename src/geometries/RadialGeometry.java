/**
 * 
 */
package geometries;

import primitives.Color;
import primitives.Material;

/**
 * class RadialGeometry is an abstract class represents a shape with radius
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public abstract class RadialGeometry extends Geometry {

	/**
	 * the radius
	 */
	protected double _radius;


	/**
	 * a constructor receiving color and radius and material
	 * 
	 * @param material the material of the geometry
	 * @param color the color of the geometry
	 * @param radius the value of the radius
	 */
	public RadialGeometry(Material material, Color color, double radius) {
		super(material, color);
		if (radius == 0)
			throw new IllegalArgumentException("radius can not be zero");
		_radius = radius;
	}
	
	/**
	 * a constructor receiving color and radius
	 * 
	 * @param color the color of the geometry
	 * @param radius the value of the radius
	 */
	public RadialGeometry(Color color, double radius) {
		this(new Material(0, 0, 0), color, radius);
	}
	
	/**
	 * a constructor receiving radius
	 * 
	 * @param radius the value of the radius
	 */
	public RadialGeometry(double radius) {
		this(new Material(0, 0, 0), Color.BLACK, radius);
	}
	
	/**
	 * copy constructor
	 * 
	 * @param other the radial geometry to copy from
	 */
	public RadialGeometry(RadialGeometry other) {
		super(other);
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
