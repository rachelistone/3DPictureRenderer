/**
 * 
 */
package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * class Geometry is an interface to all of the shapes in the scene
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public abstract class Geometry implements Intersectable {

	/**
	 * the material of the geometry
	 */
	protected Material _material;

	/**
	 * _emmission is the color of the geometry
	 */
	protected Color _emmission;

	/**
	 * default constructor sets the emmission to BLACK
	 */
	public Geometry() {
		this(new Material(0, 0, 0), Color.BLACK);
	}

	/**
	 * constructor gets the color of the geometry
	 * 
	 * @param emmission is the color of the geometry
	 */
	public Geometry(Color emmission) {
		this(new Material(0, 0, 0), emmission);
	}

	/**
	 * constructor gets the color and the material of the geometry
	 * 
	 * @param material  is the material of the geometry
	 * @param emmission is the color of the geometry
	 */
	public Geometry(Material material, Color emmission) {
		_material = material;
		_emmission = emmission;
	}

	/**
	 * copy constructor
	 * 
	 * @param other Geometry
	 */
	public Geometry(Geometry other) {
		_material = new Material(other._material.get_kD(), other._material.get_kS(), other._material.get_nShininess(),
				other._material.get_kT(), other._material.get_kR());
		_emmission = new Color(other._emmission);
	}

	/**
	 * @return the _material
	 */
	public Material get_material() {
		return _material;
	}

	/**
	 * computes the normal in a specific point
	 * 
	 * @param point where the normal vertical to the geometry
	 * @returns the normal vector
	 */
	public abstract Vector getNormal(Point3D point);

	/**
	 * @return the _emmission
	 */
	public Color get_emmission() {
		return _emmission;
	}

}
