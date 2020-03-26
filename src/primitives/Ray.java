/**
 * 
 */
package primitives;

/**
 * @author User
 *
 */
public class Ray {

	/**
	 * 
	 */
	Point3D _p0;
	Vector _dir;
	
	/**
	 * 
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
	 *  
	 */
	public Ray(Ray other) {
		_p0 = new Point3D(other._p0);
		_dir = new Vector(other._dir);
	}

	/**
	 * @return the _p0
	 */
	public Point3D get_p0() {
		return _p0;
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
	 * @return the _dir
	 */
	public Vector get_dir() {
		return _dir;
	}
	

}
