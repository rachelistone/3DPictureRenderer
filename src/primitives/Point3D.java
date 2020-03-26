package primitives;

/**
 * class Point3D is 
 * 
 * @author Yochi Shtrauber & Rachel Stone
 */
public final class Point3D {

	/**
	 * 
	 */
	final static Point3D ZERO = new Point3D(0, 0, 0);
	Coordinate _x;
	Coordinate _y;
	Coordinate _z;

	/**
	 * 
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		_x = new Coordinate(x);
		_y = new Coordinate(y);
		_z = new Coordinate(z);
	}
	
	/**
	 * 
	 */
	public Point3D(double x, double y, double z) {
		_x = new Coordinate(x);
		_y = new Coordinate(y);
		_z = new Coordinate(z);
	}
	
	/**
	 * 
	 */
	public Point3D(Point3D other) {
		_x = new Coordinate(other._x);
		_y = new Coordinate(other._y);
		_z = new Coordinate(other._z);
	}
	
	/**
	 * @return the _x
	 */
	public Coordinate get_x() {
		return _x;
	}
	public Coordinate get_y() {
		return _y;
	}
	public Coordinate get_z() {
		return _z;
	}
	
	
    @Override
	   public boolean equals(Object obj) {
	      if (this == obj) return true;
	      if (obj == null) return false;
	      if (!(obj instanceof Point3D)) return false;
	      Point3D other = (Point3D)obj;
	      return _x.equals(other._x) && _y.equals(other._y) && _z.equals(other._z);
	   }
	   
	   @Override
	    public String toString() {
	        return "" + _x.toString() + " " + _y.toString() + " " + _z.toString();
	    }
	   
	   /**
		 * @return the _x
		 */
	   public Vector subtract(Point3D other){
		   Vector vector = new Vector(_x._coord - other._x._coord, _y._coord - other._y._coord, _z._coord - other._z._coord);
		   return vector;
	   }
	   
	   /**
			 * 
			 */
	   public Point3D add(Vector vector){
		    Point3D result = new Point3D(_x._coord + vector._head._x._coord, _y._coord + vector._head._y._coord, _z._coord + vector._head._z._coord);
		    return result;
	   }
	   
	   /**
	    * 
	    * */
	   public double distanceSquared(Point3D other) {
		   return (_x._coord - other._x._coord)*(_x._coord - other._x._coord) + (_y._coord - other._y._coord)*(_y._coord - other._y._coord) + (_z._coord - other._z._coord)*(_z._coord - other._z._coord); 
	   }
	    
	   /**
	    * */
	   public double distance(Point3D other) {
		   return Math.sqrt(distanceSquared(other));
	   }
	   


}
