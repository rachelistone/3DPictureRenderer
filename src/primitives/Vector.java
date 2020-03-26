package primitives;

/**
 * class Vector represents a directional vector in a cartesian system 
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public final class Vector {
	
	/**
	 * the head point of the vector
	 */
	Point3D _head;
	
	/**
     * Vector constructor receiving values of coordinates of the head point
     * 
     * @param x the value of the first head's coordinate
     * @param y the value of the second head's coordinate
     * @param z the value of the third head's coordinate
     * @throw illegalArgument exception when the value of point coordinates is zero 
     */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		if(x._coord == 0 && y._coord == 0 && z._coord == 0)
			throw new IllegalArgumentException("the arguments cant be zero");
		_head = new Point3D(x, y, z);
	}
	
	/**
     * Vector constructor receiving values of coordinates of the head point
     * 
     * @param x the value of the first head's coordinate
     * @param y the value of the second head's coordinate
     * @param z the value of the third head's coordinate
     * @throw illegalArgument exception when the value of point coordinates is zero 
     */
	public Vector(double x, double y, double z) {
		if(x == 0 && y == 0 && z == 0)
			throw new IllegalArgumentException("the arguments cant be zero");
		_head = new Point3D(x, y, z);
	}
	
	/**
     * Vector constructor receiving point to the head
     * 
     * @param point the head point of the vector
     * @throw illegalArgument exception when the value of point coordinates is zero 
     */
	public Vector(Point3D point) {
		if(point.equals(Point3D.ZERO))
			throw new IllegalArgumentException("the arguments cant be zero");
		_head = new Point3D(point);
	}
	
	/**
     * copy constructor for Vector
     * 
     * @param other the vector to copy from
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
	 * add two vectors
	 * 
     * @param other the vector to add
	 * @return result of the addition
	 */
	public Vector add(Vector other) {
		Vector result = new Vector(_head.add(other));
		return result;
	}
	
	/**
	 * find the subtraction between two vectors.
	 * 
	 * @param other the vector to subtract from
	 * @return result of the subtract
	 */
	public Vector subtract(Vector other) {
		return _head.subtract(other._head);
	}
	
	/**
	 * multiply the vector with scalar.
	 * 
	 * @param scalar the argument to multiply with vector
	 * @return result of the multiplication
	 */
	public Vector scale(double scalar) {
		Vector result = new Vector(scalar*_head._x._coord, scalar*_head._y._coord, scalar*_head._z._coord);
		return result;
	}
	
	/**
	 * multiply coordinates of two vectors and return the dot product.
	 * 
	 * @param other the vector to multiply
	 * @return the dot product
	 */
	public double dotProduct(Vector other){
		return _head._x._coord*other._head._x._coord + _head._y._coord*other._head._y._coord + _head._z._coord*other._head._z._coord;
	}
	
	/**
	 * computes the cross product which returns the orthogonal vector to two vectors
	 * 
	 * @param other the second vector
	 * @return the cross product
	 */
	public Vector crossProduct(Vector other){
		Vector result = new Vector(_head._y._coord*other._head._z._coord - _head._z._coord*other._head._y._coord, _head._z._coord*other._head._x._coord - _head._x._coord*other._head._z._coord, _head._x._coord*other._head._y._coord - _head._y._coord*other._head._x._coord);
		return result;
	}
	
	/**
	 * computes square of the length of the vector
	 * 
	 * @return the square of the length of the vector
	 */
	public double lengthSquared() {
		return _head.distanceSquared(Point3D.ZERO);
	}
	
	/**
	 * computes the length of the vector
	 * 
	 * @return the length of the vector
	 */
	public double length() {
		return _head.distance(Point3D.ZERO);
	}
	
	/**
	 * normalizes the vector to be in length 1 by dividing each coordinate of the head with the length of the vector
	 * 
	 * @return normalized vector 
	 */
	public Vector normalize() {
		_head._x = new Coordinate(_head._x._coord/length());
		_head._y = new Coordinate(_head._y._coord/length());
		_head._z = new Coordinate(_head._z._coord/length());
		return this;
	}
	
	/**
	 * normalizes the vector to be in length 1 by dividing each coordinate of the head with the length of the vector and returns the new vector 
	 * 
	 * @return normalized vector
	 */
	public Vector normalized() {
		Vector result = new Vector(_head._x._coord/length(), _head._y._coord/length(), _head._z._coord/length());
		return result;
	}

}
