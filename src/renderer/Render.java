/**
 * 
 */
package renderer;

import java.util.Iterator;
import java.util.List;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
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
				List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
				// if there is no geometry at that direction -> color as the background, else
				// color according to the geometry there and its position
				if (intersectionPoints == null) {
					_imageWriter.writePixel(col, row, background);
				} else {
					GeoPoint closestPoint = getClosestPoint(intersectionPoints);
					_imageWriter.writePixel(col, row, calcColor(closestPoint).getColor());
				}
			}
		}
	}

	/**
	 * from all the intersection points, this function find the closest point to the
	 * view plane and return it
	 * 
	 * @param intersectionPoints the points that intersect the ray in a specific
	 *                           pixel
	 * @return the closest point to the screen, that it is the point from the
	 *         intersection points
	 */
	public GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
		// p0 is the point of the camera
		Point3D p0 = _scene.get_camera().get_p0();
		// result is the closest point
		GeoPoint result = null;
		// minDist is the minimum destination
		double minDist = Double.MAX_VALUE;
		// currentDistance the distance of the actual point
		double currentDistance = 0;
		// this for scan the all points from the list and for every point it checks
		// if its distance is less than the last one
		for (GeoPoint geoPoint : intersectionPoints) {
			currentDistance = p0.distance(geoPoint._point);
			if (currentDistance < minDist) {
				minDist = currentDistance;
				result = geoPoint;
			}
		}
		return result;
	}

	/**
	 * calculate the color in a specific point according to model phong:
	 * 
	 * 
	 * @param closestPoint the p air of geometry and point to calculate the color to
	 * @return the color of the ambient light plus the color of the geometry
	 */
	private Color calcColor(GeoPoint closestPoint) {
		Color result = _scene.get_ambientLight().get_intensity();
		result = result.add(closestPoint._geometry.get_emmission());

		// the diffuse reflection of the light on the surface
		double diffuse = closestPoint._geometry.get_material().get_kD();
		// the specular reflection
		double specular = closestPoint._geometry.get_material().get_kS();
		// the shininess of the material
		int nShinines = closestPoint._geometry.get_material().get_nShininess();
		// the normal of the geometry
		Vector normal = closestPoint._geometry.getNormal(closestPoint._point);
		// the vector from the position of the camera to the calculated point
		Vector v = closestPoint._point.subtract(_scene.get_camera().get_p0()).normalize();
		// the direction vector between the light source and the point
		Vector l;
		// the intensity of a specific light source in a specific point
		Color intensity;

		Iterator<LightSource> iterator = _scene.get_Lights().iterator();
		while (iterator.hasNext()) {
			LightSource ls = iterator.next();
			l = ls.getL(closestPoint._point);
			intensity = ls.getIntensity(closestPoint._point);
			// if the light source and the camera is in the same direction -> this light
			// source is significant for the point - calculate the diffuse and specular
			if ((normal.dotProduct(l) > 0) == (normal.dotProduct(v) > 0)) {
				result = result.add(calcDiffuse(diffuse, l, normal, intensity));
				result = result.add(calcSpecular(specular, l, normal, v, nShinines, intensity));
			}
		}

		return result;
	}

	/**
	 * the function calculates the diffuse of the light source upon the surface of
	 * the geometry
	 * 
	 * @param kD        the coefficient of the diffuse of the geometry
	 * @param l         the direction vector from the light source to the point
	 * @param normal    the normal to the geometry in the point
	 * @param intensity the intensity of the light source
	 * @return
	 */
	public Color calcDiffuse(double kD, Vector l, Vector normal, Color intensity) {
		// dot product between L and the normal
		double ln;
		ln = l.dotProduct(normal);
		// absolute value of ln
		if (ln < 0) {
			ln *= -1;
		}
		return intensity.scale(ln * kD);
	}

	/**
	 * the function calculates the specular reflection of the light source upon the
	 * geometry
	 * 
	 * @param kS        the coefficient of the specular of the geometry
	 * @param l         the direction vector from the light source to the point
	 * @param normal    the normal to the geometry in the point
	 * @param v         the direction vector from the camera to the point
	 * @param nShinines of the geometry
	 * @param intensity the intensity of the light source
	 * @return
	 */
	public Color calcSpecular(double kS, Vector l, Vector normal, Vector v, double nShinines, Color intensity) {
		// dot product between L and the normal
		double ln;
		// dot product between v and r multiplied in -1
		double vr;
		// the max value between 0 and vr
		double max;
		// the max value pow with the shininess
		double maxPow;
		ln = l.dotProduct(normal);
		// the reflection of the vector L according to the normal
		Vector r;
		r = l.subtract(normal.scale(ln * 2)).normalize();
		vr = v.dotProduct(r) * -1;
		if (vr > 0) {
			max = vr;
		} else
			max = 0;
		maxPow = Math.pow(max, nShinines);
		return intensity.scale(maxPow * kS);
	}

	/**
	 * prints the grid on the picture according to the intervals which defines the
	 * size of the square and the color it gets
	 * 
	 * @param interval to color the screen in every (number) interval pixels with
	 *                 the color specified
	 * @param color    of the grid
	 */
	public void printGrid(int interval, java.awt.Color color) {
		// the resolution of the picture
		int Nx = _imageWriter.getNx();
		int Ny = _imageWriter.getNy();
		// print the grid by scanning all the pixels in the screen
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
