package primitives;

/**
 * @author User
 *
 */
public final class Vector {

	Point3D _head;
	
	/**
	 * 
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		_head = new Point3D(x, y, z);
	}
	
	/**
	 * 
	 */
	public Vector(double x, double y, double z) {
		_head = new Point3D(x, y, z);
	}
	
	/**
	 * 
	 */
	public Vector(Point3D other) {
		_head = new Point3D(other);
	}
	
	/**
	 * 
	 */
	public Vector(Vector other) {
		_head = new Point3D(other._head);
	}

	/**
	 * @return the _head
	 */
	public Point3D get_head() {
		return _head;
	}
	
	@Override
	public boolean equals(Object obj) {
	   if (this == obj) return true;
	   if (obj == null) return false;
	   if (!(obj instanceof Vector)) return false;
	   Vector other = (Vector)obj;
	   return _head.equals(other.get_head());
    }
	
	@Override
    public String toString() {
        return "" + _head.toString();
    }

	
	/**
	 * 
	 */
	public Vector add(Vector other) {
		Vector result = new Vector(_head.add(other));
		return result;
	}
	
	/**
	 * 
	 */
	public Vector subtract(Vector other) {
		return _head.subtract(other._head);
	}
	
	/**
	 * 
	 */
	public Vector scale(double scalar) {
		Vector result = new Vector(scalar*_head._x._coord, scalar*_head._y._coord, scalar*_head._z._coord);
		return result;
	}
	
	/**
	 * 
	 */
	public double dotProduct(Vector other){
		return _head._x._coord*other._head._x._coord + _head._y._coord*other._head._y._coord + _head._z._coord*other._head._z._coord;
	}
	
	/**
	 * 
	 */
	public Vector crossProduct(Vector other){
		Vector result = new Vector(_head._y._coord*other._head._z._coord - _head._z._coord*other._head._y._coord, _head._z._coord*other._head._x._coord - _head._x._coord*other._head._z._coord, _head._x._coord*other._head._y._coord - _head._y._coord*other._head._x._coord);
		return result;
	}
	
	/**
	 * 
	 */
	public double lengthSquared() {
		return _head.distanceSquared(Point3D.ZERO);
	}
	
	/**
	 * 
	 */
	public double length() {
		return _head.distance(Point3D.ZERO);
	}
	
	/**
	 * 
	 */
	public Vector normalize() {
		_head._x = new Coordinate(_head._x._coord/length());
		_head._y = new Coordinate(_head._y._coord/length());
		_head._z = new Coordinate(_head._z._coord/length());
		return this;
	}
	
	/**
	 * 
	 */
	public Vector normalized() {
		Vector result = new Vector(_head._x._coord/length(), _head._y._coord/length(), _head._z._coord/length());
		return result;
	}

}
