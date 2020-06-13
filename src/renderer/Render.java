/**
 * 
 */
package renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

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
 * scene it has, and responsible for generating pixel color map from the graphic
 * scene, using ImageWriter class
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
	 * the maximum times to enter the recursion of calcColor
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;

	/**
	 * the bound of the opacity to enter the recursion of calcColor
	 */
	private static final double MIN_CALC_COLOR_K = 0.001;

	/**
	 * renderImage generates the picture by building the rays to the view plane and
	 * calculating each pixel
	 */
	public void renderImage() {
		Camera camera = _scene.get_camera();
		java.awt.Color background = _scene.get_background().getColor();
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		double distance = _scene.get_distance();
		double width = _imageWriter.getWidth();
		double height = _imageWriter.getHeight();
		// write to each pixel of the image
		for (int col = 0; col < nX; col++) {
			for (int row = 0; row < nY; row++) {
				// construct a ray to each pixel and find the closest intersection point of that
				// ray
				// with the geometries of the scene
				if (col == 145 && row == 355) {
					System.out.print('g');;
				}
				Ray ray = camera.constructRayThroughPixel(nX, nY, col, row, distance, width, height);
				GeoPoint closestPoint = findCLosestIntersection(ray);
				// if there is no geometry at that direction -> color as the background, else
				// color according to the geometry there and its position
				if (closestPoint == null) {
					_imageWriter.writePixel(col, row, background);
				} else {
					_imageWriter.writePixel(col, row, calcColor(closestPoint, ray).getColor());
				}
			}
		}
	}

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
	 * from all the intersection points, this function find the closest point to the any point and return it
	 * 
	 * @param intersectionPoints the points that intersect the ray in a specific
	 *                           pixel
	 * @param point to check the closest point to
	 * @return the closest point to the screen, that it is the point from the
	 *         intersection points
	 */
	public GeoPoint getClosestPoint(Point3D point, List<GeoPoint> intersectionPoints) {
		// result is the closest point
		GeoPoint result = null;
		// minDist is the minimum destination
		double minDist = Double.MAX_VALUE;
		// currentDistance the distance of the actual point
		double currentDistance = 0;
		// this for scan the all points from the list and for every point it checks
		// if its distance is less than the last one
		for (GeoPoint geoPoint : intersectionPoints) {
			currentDistance = point.distance(geoPoint._point);
			if (currentDistance < minDist) {
				minDist = currentDistance;
				result = geoPoint;
			}
		}
		return result;
	}
	
	/**
	 * from all the intersection points, this function find the closest point to the camera
	 * and return it
	 * 
	 * @param intersectionPoints the points that intersect the ray in a specific
	 *                           pixel
	 * @return the closest point to the screen, that it is the point from the
	 *         intersection points
	 */
	public GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
		return getClosestPoint(_scene.get_camera().get_p0(), intersectionPoints);
	}

	/**
	 * find the closest intersection with a ray
	 * 
	 * @param ray
	 * @return the pair of point and its geometry
	 */
	private GeoPoint findCLosestIntersection(Ray ray) {
		List<GeoPoint> intersections = _scene.get_geometries().findIntersections(ray);
		if (intersections == null)
			return null;
		return getClosestPoint(ray.get_p0(), intersections);
	}

	/**
	 * calculate the color in a specific point according to model phong, calculated
	 * by the color of the elements and geometries of the scene
	 * 
	 * @param ray          the ray of camera to calculate the color of the point it
	 *                     intersects
	 * @param closestPoint the p air of geometry and point to calculate the color to
	 * @return the color of the pixel according to model phong:
	 */
	private Color calcColor(GeoPoint closestPoint, Ray ray) {
		return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.get_ambientLight().get_intensity());
	}

	/**
	 * calculate the color in a specific point according to model phong:
	 *
	 * @param closestPoint the pair of geometry and point to calculate the color to
	 * @param inRay        the ray that we want to calculate the intersection with
	 *                     it
	 * @param level        the depth of the recursion
	 * @param k            counting the cumulative opacity trough the recursion
	 * @return the color of the ambient light plus the color of the geometry
	 */
	private Color calcColor(GeoPoint closestPoint, Ray inRay, int level, double k) {
		Color color = closestPoint._geometry.get_emmission();
		// the diffuse of the light on the surface
		double diffuse = closestPoint._geometry.get_material().get_kD();
		// the specular reflection of the light
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
			if (normal.dotProduct(l) * normal.dotProduct(v) > 0) {
				double ktr = transparency(ls, l, normal, closestPoint);
				if (ktr * k > MIN_CALC_COLOR_K) {
					intensity = ls.getIntensity(closestPoint._point);// .scale(ktr);
					color = color.add(calcDiffuse(diffuse, l, normal, intensity),
							calcSpecular(specular, l, normal, v, nShinines, intensity));
				}
			}
		}

		// calculate reflection and refraction
		if (level == 1)
			return Color.BLACK;
		double kr = closestPoint._geometry.get_material().get_kR();
		double glossy = closestPoint._geometry.get_material().get_glossy();
		double kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(normal, closestPoint._point, inRay);
//			if (glossy > 0) {
//				List<Ray> rays = constructRaysGroup(reflectedRay, glossy);
//				color = color.add(calcColor(rays).scale(kr));
//			} else {
			GeoPoint reflectedPoint = findCLosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
//			}
		}
		double kt = closestPoint._geometry.get_material().get_kT();
		double kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(normal, closestPoint._point, inRay);
			GeoPoint refractedPoint = findCLosestIntersection(refractedRay);
			if (refractedPoint != null)
				color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
		}
		return color;
	}

	/**
	 * the function calculates the reflection ray according to the normal
	 * 
	 * @param normal of the geometry
	 * @param point  on the geometry
	 * @param ray    that we want to calculate the reflection ray occording to
	 * @return the reflection ray
	 */
	public Ray constructReflectedRay(Vector normal, Point3D point, Ray ray) {
		// dot product between L and the normal
		Vector v = ray.get_dir();
		double vn = v.dotProduct(normal);
		Vector reflectionDir = v.subtract(normal.scale(vn * 2));
		// build the ray with moving the source by DELTA from the point
		return new Ray(point, normal, reflectionDir);
	}

	/**
	 * the function calculates the refraction ray in assumption that the coefficient
	 * of the refraction is 1
	 * 
	 * @param normal of the geometry
	 * @param point  on the geometry
	 * @param ray    that we want to calculate the refraction ray occording to
	 * @return the refraction ray
	 */
	public Ray constructRefractedRay(Vector normal, Point3D point, Ray ray) {
		return new Ray(point, normal, ray.get_dir());
	}

	/**
	 * this function checks for a specific point if it is blocked by any geometry
	 * from the scene that resides between the light source and the calculated point
	 * 
	 * @param light the light source to check if occluded
	 * @param l     the direction between the light source to the point calculated
	 * @param n     the normal to the geometry of the calculated point
	 * @param gp    the pair of the calculated point and its geometry
	 * @return true if no geometry blocking light to the point of gp
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp) {
		Vector lightDirection = l.scale(-1); // from point to light source
		// build the shadow ray in DELTA distance from the geometry
		Ray lightRay = new Ray(gp._point, n, lightDirection);
		List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay,
				light.getDistance(gp._point));
		if (intersections == null) {
			return true;
		}
		for (GeoPoint geoPoint : intersections) {
			if (geoPoint._geometry.get_material().get_kT() == 0.0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * this function checks for a specific point if it is blocked by any geometry
	 * from the scene that resides between the light source and the calculated point
	 * 
	 * @param light the light source to check if occluded
	 * @param l     the direction between the light source to the point calculated
	 * @param n     the normal to the geometry of the calculated point
	 * @param gp    the pair of the calculated point and its geometry
	 * @return intensity of the light attenuated according to level of refraction
	 */
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint gp) {
		Vector lightDirection = l.scale(-1); // from point to light source
		// build the shadow ray in DELTA distance from the geometry
		Ray lightRay = new Ray(gp._point, n, lightDirection);
		List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay,
				light.getDistance(gp._point));
		if (intersections == null) {
			return 1.0;
		}
		double ktr = 1.0;
		for (GeoPoint geoPoint : intersections) {
			ktr *= geoPoint._geometry.get_material().get_kT();
			if (ktr < MIN_CALC_COLOR_K)
				return 0.0;
		}
		return ktr;
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
		double ln = l.dotProduct(normal);
		// the reflection of the vector L according to the normal
		Vector r = l.subtract(normal.scale(ln * 2)).normalize();
		// dot product between v and r multiplied in -1
		double vr = v.dotProduct(r) * -1;
		// the max value between 0 and vr
		double max;
		if (vr > 0) {
			max = vr;
		} else
			max = 0;
		// the max value pow with the shininess
		double maxPow = Math.pow(max, nShinines);
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
