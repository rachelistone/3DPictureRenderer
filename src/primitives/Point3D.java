package primitives;

/**
 * class Point3D represents a point with 3 coordinates in cartesian system 
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public final class Point3D {

	/**
	 * a static point that has the value zero 
	 */
    public final static Point3D ZERO = new Point3D(0, 0, 0);
    
    /**
	 *  the coordinates of the point 
	 */
	Coordinate _x;
	Coordinate _y;
	Coordinate _z;

	/**
     * Point3D constructor receiving values of coordinates
     * 
     * @param x the value of the first point's coordinate
     * @param y the value of the second point's coordinate
     * @param z the value of the third point's coordinate
     */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		_x = new Coordinate(x);
		_y = new Coordinate(y);
		_z = new Coordinate(z);
	}
	
	/**
     * Point3D constructor receiving values of coordinates
     * 
     * @param x the value of the first point's coordinate
     * @param y the value of the second point's coordinate
     * @param z the value of the third point's coordinate
     */
	public Point3D(double x, double y, double z) {
		_x = new Coordinate(x);
		_y = new Coordinate(y);
		_z = new Coordinate(z);
	}
	
	/**
     * copy constructor for Point3D
     * 
     * @param other the point to copy from
     */
	public Point3D(Point3D other) {
		_x = new Coordinate(other._x);
		_y = new Coordinate(other._y);
		_z = new Coordinate(other._z);
	}
	
	/**
	 * @return the _x, the first coordinates
	 */
	public Coordinate get_x() {
		return _x;
	}
	/**
	 * @return the _y, the second coordinates
	 */
	public Coordinate get_y() {
		return _y;
	}
	/**
	 * @return the _z, the third coordinates
	 */
	public Coordinate get_z() {
		return _z;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return _x.equals(other._x) && _y.equals(other._y) && _z.equals(other._z);
	}

	@Override
	public String toString() {
		return "" + _x.toString() + " " + _y.toString() + " " + _z.toString();
	}

	/**
	 * find the direction vector between two points
	 * 
	 * @param other the point to subtract from
	 * @return the direction vector
	 */
	public Vector subtract(Point3D other) {
		Vector vector = new Vector(_x._coord - other._x._coord, _y._coord - other._y._coord,
				_z._coord - other._z._coord);
		return vector;
	}

	/**
	 * add a vector to the point
	 * 
	 * @param vector to add
	 * @return the destination point
	 */
	public Point3D add(Vector vector) {
		Point3D result = new Point3D(_x._coord + vector._head._x._coord, _y._coord + vector._head._y._coord,
				_z._coord + vector._head._z._coord);
		return result;
	}

	/**
	 * the square of the distance between two points p1^2+p2^2+p3^2
	 * 
	 * @param other the point to find the squared distance from
	 * @return the square of the distance
	 */
	public double distanceSquared(Point3D other) {
		return (_x._coord - other._x._coord) * (_x._coord - other._x._coord)
				+ (_y._coord - other._y._coord) * (_y._coord - other._y._coord)
				+ (_z._coord - other._z._coord) * (_z._coord - other._z._coord);
	}

	/**
	 * the distance between two points: computes the root of p1^2+p2^2+p3^2
	 * 
	 * @param other the point to find the distance from
	 * @return the distance
	 */
	public double distance(Point3D other) {
		return Math.sqrt(distanceSquared(other));
	}

}
