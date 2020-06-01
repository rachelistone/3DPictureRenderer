/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class Cylinder represents a tube with defined height inherits from Tube class
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public class Cylinder extends Tube {
	
	/**
	 * the height of the Cylinder
	 */
	protected double _height;
	
	/**
	 * constructor setting the fields   
	 * 
	 * @param material the material of the geometry
	 * @param color the color of the geometry
	 * @param radius the distance from the center axis to the bordure
	 * @param axisRay the axis of the cylinder center
	 * @param height the height of the cylinder
	 */
	public Cylinder(Material material, Color color, double radius, Ray axisRay, double height) {
		super(material, color, radius, axisRay);
		_height = height;
	}

	/**
	 * constructor setting the fields     
	 * 
	 * @param color the color of the geometry
	 * @param radius the distance from the center axis to the bordure
	 * @param axisRay the axis of the cylinder center
	 * @param height the height of the cylinder
	 */
	public Cylinder(Color color, double radius, Ray axisRay, double height) {
		this(new Material(0, 0, 0), color, radius, axisRay, height);
	}
	
	/**
	 * constructor setting the fields     
	 * 
	 * @param radius the distance from the center axis to the bordure
	 * @param axisRay the axis of the cylinder center
	 * @param height the height of the cylinder
	 */
	public Cylinder(double radius, Ray axisRay, double height) {
	      this(new Material(0, 0, 0), Color.BLACK, radius, axisRay, height);
	}
	
	/**
	 * getter to the axis ray
	 * 
	 * @return the _axisRay
	 */
	public double get__height() {
		return _height;
	}

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		// the vector between the given point to the source of the ray
		Vector v = point.subtract(_axisRay.get_p0());
		//the projection of the vector between the given point to the source of the ray, on the axis of the tube
		double height = v.dotProduct(_axisRay.get_dir().normalize());
		//if v is already rectangle to the tube axis 
		if (height == 0)
			return v.normalize();
		//vector with the axis direction and the projection's length:
		Vector axisHeight = _axisRay.get_dir().scale(height);
		//if the distance between the given point to the head of the vector which in the given point's height is the radius, it is on the tube
		if (point.distance(axisHeight.get_head()) != _radius)
			return null;
		return point.subtract(axisHeight.get_head()).normalize();
	}
	
	@Override
	public String toString() {
		return "" + _height + " " + super.toString();
	}
	
	public List<GeoPoint> findIntersections(Ray ray){
		return null;
	}
}
