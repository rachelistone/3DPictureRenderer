package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * class Camera has place of the camera, were all the rays starts
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public class Camera {

	/**
	 * place of the camera
	 */
	private Point3D _p0;

	/**
	 * vectors of the directions from the camera defines the space
	 */
	private Vector _vUp;
	private Vector _vTo;
	private Vector _vRight;

	public Camera(Point3D p0, Vector vTo, Vector vUp) {
		// TODO Auto-generated constructor stub
		_p0 = p0;
		if (Util.isZero(vUp.dotProduct(vTo))) {
			_vUp = vUp.normalized();
			_vTo = vTo.normalized();
			_vRight = vTo.crossProduct(vUp).normalize();
		} else {
			throw new IllegalArgumentException("the vectors are not orthogonal");
		}
	}

	/**
	 * @return the _p0
	 */
	public Point3D get_p0() {
		return _p0;
	}

	/**
	 * @return the _vUp
	 */
	public Vector get_vUp() {
		return _vUp;
	}

	/**
	 * @return the _vTo
	 */
	public Vector get_vTo() {
		return _vTo;
	}

	/**
	 * @return the _vRight
	 */
	public Vector get_vRight() {
		return _vRight;
	}

	/**
	 * the function gets parameters that define the view plane matrix, and the index
	 * of the pixel on it, and returns a ray from the place point of the camera
	 * through the pixel
	 * 
	 * @param nX             is the number of the pixel in the axis x
	 * @param nY             is the number of the pixel in the axis y
	 * @param j              define the column of the pixel in the matrix
	 * @param i              define the row of the pixel in the matrix
	 * @param screenDistance is the distance between the place of the camera and the
	 *                       view plane
	 * @param screenWidth    is the width of the view plane
	 * @param screenHeight   is the height of the view plane
	 * @return ray from the camera through the specific pixel i,j
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth,
			double screenHeight) {
		// the center point of the view plane
		Point3D pc = _p0.add(_vTo.scale(screenDistance));
		// the width of the pixel
		double rX = screenWidth / nX;
		// the height of the pixel
		double rY = screenHeight / nY;
		// the x, y coordinate of the vector of the ray
		// subtract half the number of pixels in half screen from the index j, i of the
		// pixel in the matrix
		// and multiply it with the size of the pixel
		// and add half width pixel to get the center of the pixel
		double xj = (j - (double) nX / 2) * rX + rX / 2;
		double yi = (i - (double) nY / 2) * rY + rY / 2;

		// the point of the middle of pixel i, j
		// build the point of the specific pixel on the view plane and add that point to
		// the center reference point of the view plane
		Point3D pij = null;
		// if the number of pixels in the row or/ and in the column is odd
		if (((nX % 2 == 1) && (nY % 2 == 1)) || ((nX % 2 == 0) && (nY % 2 == 1)) || ((nX % 2 == 1) && (nY % 2 == 0))) {
			if (xj == 0) {
				if (yi == 0) {
					// i, j is the mid pixel
					pij = pc;
				} else {
					// pixel is on the mid column -> go just on vUp
					pij = pc.add(_vUp.scale(-1 * yi));
				}
			} else {
				// pixel is on the mid row -> go just on vRight
				if (yi == 0) {
					pij = pc.add(_vRight.scale(xj));
				} else {
					// if the pixel i, j is not on the mid row or column and there are odd number of
					// pixels
					pij = pc.add(_vRight.scale(xj).subtract(_vUp.scale(yi)));
				}
			}
		} else {
			// the number of pixels is even
			pij = pc.add(_vRight.scale(xj).subtract(_vUp.scale(yi)));
		}
		return new Ray(new Point3D(_p0), new Vector(pij.subtract(_p0)));
	}

}
