package geometries;
import primitives.Ray;

import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * class Tube represents a shape of pipe using radius and the axis ray of the tube center
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public class Tube extends RadialGeometry {

	/**
	 * the axis of the center of the tube
	 */
	protected Ray _axisRay;
	
	
	/**
	 * constructor receiving radius, axis ray of the tube center, the color and the material
	 * 
	 * @param material is the material of the geometry
	 * @param color is the color of the Tube
	 * @param radius
	 * @param axisRay the axis ray of the tube center
	 */
	public Tube(Material material, Color color, double radius, Ray axisRay) {
		super(material, color, radius);
		_axisRay = new Ray(axisRay);
	}
	
	/**
	 * constructor receiving radius and axis ray of the tube center
	 * 
	 * @param color is the color of the Tube
	 * @param radius
	 * @param axisRay the axis ray of the tube center
	 */
	public Tube(Color color, double radius, Ray axisRay) {
		this(new Material(0, 0, 0), color, radius, axisRay);
	}
	
	/**
	 * constructor receiving radius and axis ray of the tube center
	 * 
	 * @param radius
	 * @param axisRay the axis ray of the tube center
	 */
	public Tube(double radius, Ray axisRay) {
		this(new Material(0, 0, 0), Color.BLACK, radius, axisRay);
	}

	/**
	 * getter to the axis ray
	 * 
	 * @return the _axisRay
	 */
	public Ray get_axisRay() {
		return _axisRay;
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
		return "" + _axisRay + " " + super.toString();
	}
	
	public List<GeoPoint> findIntersections(Ray ray){
		return null;
	}

}
