package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class point defines point light source that spreads light to all the directions
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public class PointLight extends Light implements LightSource {

	/**
	 * the place in the space where the light source resides
	 */
	protected Point3D _position;

	/**
	 * kC, kL, kQ are the coeficients of the attenuation of the light source
	 */
	protected double _kC;
	protected double _kL;
	protected double _kQ;

	/**
	 * constructor initializes all the fields
	 * 
	 * @param intensity the color of the light source
	 * @param position the position of the light source
	 * @param kC coefficient of attenuation
	 * @param kL coefficient of attenuation
	 * @param kQ coefficient of attenuation
	 */
	public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
		super(intensity);
		_position = new Point3D(position);
		_kC = kC;
		_kL = kL;
		_kQ = kQ;
	}

	@Override
	public Color getIntensity(Point3D point) {
		// TODO Auto-generated method stub
		double attenuation = _kC + _kL*_position.distance(point) + _kQ*_position.distanceSquared(point);
		return _intensity.reduce(attenuation);
	}

	@Override
	public Vector getL(Point3D point) {
		// TODO Auto-generated method stub
		return point.subtract(_position).normalize();
	}
	
	@Override
	public double getDistance(Point3D point) {
		return _position.distance(point);
	}

}
