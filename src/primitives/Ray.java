package primitives;

/**
 * class Ray represents a Ray by a source point and the direction of the ray in cartesian system 
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
     * Ray constructor receiving values of the source point and directional vector 
     * 
     * @param p0 the source point
     * @param dir the value of the directional vector
     */
	public Ray(Point3D p0, Vector dir) {
		if(dir.length() != 1) {
			_dir = dir.normalized();
		}
		else
			_dir = new Vector(dir);
		_p0 = new Point3D(p0);
	}
	
	/**
	 *  copy constructor for a ray
	 *  
	 *  @param other ray
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
	   if (this == obj) return true;
	   if (obj == null) return false;
	   if (!(obj instanceof Ray)) return false;
	   Ray other = (Ray)obj;
	   return _p0.equals(other.get_p0()) && _dir.equals(other.get_dir());
    }
	
	@Override
    public String toString() {
        return "" + _p0.toString() + " " + _dir.toString();
    }
	

	/**
	 * calculate and return the point of the multiplication between the ray and scalar
	 * 
	 * @param t is the scalar
	 * @return point that the scaled ray arrived there
	 */
	public Point3D getPoint(double t) {
		return _p0.add(_dir.scale(t));
	}

}
