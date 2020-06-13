package primitives;

/**
 * class Ray represents a Ray by a source point and the direction of the ray in
 * cartesian system
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public class Ray {

	/**
	 * the source point of the ray
	 */
	Point3D _p0;

	/**
	 * the direction vector of the ray
	 */
	Vector _dir;

	/**
	 * constant for moving the source of the rays for shadow rays
	 */
	private static final double DELTA = 0.1;

	/**
	 * Ray constructor receiving values of the source point and directional vector
	 * 
	 * @param p0  the source point
	 * @param dir the value of the directional vector
	 */
	public Ray(Point3D p0, Vector dir) {
		if (dir.length() != 1) {
			_dir = dir.normalized();
		} else
			_dir = new Vector(dir);
		_p0 = new Point3D(p0);
	}

	/**
	 * constructor getting a point and normal (of a geometry) and moves the point in
	 * the normal's direction with delta length, and that point is the source of the
	 * built ray, and getting the direction of the ray (of the shadow ray, reflection or refraction)
	 * 
	 * @param point base place 
	 * @param normal direction to move the source point to
	 * @param direction of the ray to build
	 */
	public Ray(Point3D point, Vector normal, Vector direction) {
		// delta is a vector to move the source of the ray to, by scaling the normal
		// with the delta
		// if the normal is towards the inside of the geometry -> reverse the direction
		// of the normal
		_dir = direction.normalized();
		Vector delta = normal.scale(normal.dotProduct(direction) >= 0 ? DELTA : -DELTA);
		_p0 = point.add(delta);
	}

//	/**
//	 * constructor getting 2 points , one for the head of the ray, and the second for the source of it,
//	 *  and subtract between them to get the direction vector of the ray
//	 * 
//	 * @param p0 the source of the ray
//	 * @param head the head of the ray
//	 */
//	public Ray(Point3D p0, Point3D head) {
//		_dir = head.subtract(p0).normalize();;
//		_p0 = p0;
//	}
	
	/**
	 * copy constructor for a ray
	 * 
	 * @param other ray
	 */
	public Ray(Ray other) {
		_p0 = new Point3D(other._p0);
		_dir = new Vector(other._dir);
	}

	/**
	 * source point getter
	 * 
	 * @return the source point _p0
	 */
	public Point3D get_p0() {
		return _p0;
	}

	/**
	 * directed vector getter
	 * 
	 * @return the vector _dir
	 */
	public Vector get_dir() {
		return _dir;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return _p0.equals(other.get_p0()) && _dir.equals(other.get_dir());
	}

	@Override
	public String toString() {
		return "" + _p0.toString() + " " + _dir.toString();
	}

	/**
	 * calculate and return the point of the multiplication between the ray and
	 * scalar
	 * 
	 * @param t is the scalar
	 * @return point that the scaled ray arrived there
	 */
	public Point3D getPoint(double t) {
		return _p0.add(_dir.scale(t));
	}

}
