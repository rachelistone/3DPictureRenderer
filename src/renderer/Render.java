/**
 * 
 */
package renderer;

import java.util.List;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * the class Render would generate the picture to the jpg file according to the
 * scene it has
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public class Render {

	/**
	 * the scene to render
	 */
	private Scene _scene;

	/**
	 * the image writer gives the util of writing to the file
	 */
	private ImageWriter _imageWriter;

	/**
	 * the Render constructor that gets Scene and ImageWriter
	 * 
	 * @param scene       the all the things in the image
	 * @param imageWriter gives the util of writing to the file
	 */
	public Render(Scene scene, ImageWriter imageWriter) {
		_scene = scene;
		_imageWriter = imageWriter;
	}

	/**
	 * write the image from the buffer to the file
	 */
	public void writeToImage() {
		_imageWriter.writeToImage();
	}

	/**
	 * renderImage generates the picture by building the rays to the view plane and
	 * calculating each pixel
	 */
	public void renderImage() {
		Camera camera = _scene.get_camera();
		Intersectable geometries = _scene.get_geometries();
		java.awt.Color background = _scene.get_background().getColor();
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		double distance = _scene.get_distance();
		double width = _imageWriter.getWidth();
		double height = _imageWriter.getHeight();
		// write to each pixel of the image
		for (int col = 0; col < nX; col++) {
			for (int row = 0; row < nY; row++) {
				// construct a ray to each pixel and find the intersection points of that ray
				// with the geometries of the scene
				Ray ray = camera.constructRayThroughPixel(nX, nY, col, row, distance, width, height);
				List<Point3D> intersectionPoints = geometries.findIntersections(ray);
				// if there is no geometry at that direction -> color as the background, else
				// color according to the geometry there and its position
				if (intersectionPoints == null) {
					_imageWriter.writePixel(col, row, background);
				} else {
					Point3D closestPoint = getClosestPoint(intersectionPoints);
					_imageWriter.writePixel(col, row, calcColor(closestPoint).getColor());
				}
			}
		}
	}

	/**
	 * from all the intersection points, this function find the closest point to the view plane and return it
	 * 
	 * @param intersectionPoints the points that intersect the ray in a specific pixel
	 * @return the closest point to the screen, that it is the point from the intersection points 
	 */
	public Point3D getClosestPoint(List<Point3D> intersectionPoints) {
		//p0 is the point of the camera
		Point3D p0 = _scene.get_camera().get_p0();
		//result is the closest point
		Point3D result = null;
		//minDist is the minimum destination 
		double minDist = Double.MAX_VALUE;
		//currentDistance the distance of the actual point 
		double currentDistance = 0;
        //this for scan the all points from the list and for every point it checks
		//if its distance is less than the last one
		for (Point3D point : intersectionPoints) {
			currentDistance = p0.distance(point);
			if (currentDistance < minDist) {
				minDist = currentDistance;
				result = point;
			}
		}
		return result;
	}

	/**
	 * calculate the color in a specific point
	 * 
	 * @param closestPoint the point to calculate the color to
	 */
	private Color calcColor(Point3D closestPoint) {
		return _scene.get_ambientLight().getIntensity();
	}

	/**
	 * prints the grid on the picture according to the intervals which defines the size of the square and the color it gets
	 * 
	 * @param interval to color the screen in every (number) interval pixels with the color specified
	 * @param color of the grid
	 */
	public void printGrid(int interval, java.awt.Color color) {
		// the resolution of the picture
		int Nx = _imageWriter.getNx();
		int Ny = _imageWriter.getNy();
		//print the grid by scanning all the pixels in the screen
		for (int i = 0; i < Ny; i++) {
			for (int j = 0; j < Nx; j++) {
				// color just the pixels in the interval
				if (i % interval == 0 || j % interval == 0) {
					_imageWriter.writePixel(j, i, color);
				}
			}
		}
	}
}
