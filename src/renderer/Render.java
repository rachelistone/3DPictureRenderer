/**
 * 
 */
package renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
import primitives.Util;
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
	private static final int MAX_CALC_COLOR_LEVEL = 3;

	/**
	 * how many times to add the middle ray in the beam of rays
	 */
	private static int MID_RAY_WEIGHT = 30;

	/**
	 * the bound of the opacity to enter the recursion of calcColor
	 */
	private static final double MIN_CALC_COLOR_K = 0.001;

	/**
	 * @return the _numOfRays
	 */
	public int get_numOfRays() {
		return _numOfRays;
	}

	/**
	 * @param _numOfRays the _numOfRays to set
	 */
	public Render set_numOfRays(int _numOfRays) {
		this._numOfRays = _numOfRays;
		return this;
	}

	/**
	 * the number of rays to build rays' group
	 */
	private int _numOfRays;

	private int _threads = 1;
	private final int SPARE_THREADS = 2;
	private boolean _print = false;

	/**
	 * Pixel is an internal helper class whose objects are associated with a Render
	 * object that they are generated in scope of. It is used for multithreading in
	 * the Renderer and for follow up its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each
	 * thread.
	 * 
	 * @author Dan
	 *
	 */
	private class Pixel {
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * 
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print)
				System.out.printf("\r %02d%%", _percents);
		}

		/**
		 * Default constructor for secondary Pixel objects
		 */
		public Pixel() {
		}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object
		 * - this function is critical section for all the threads, and main Pixel
		 * object data is the shared data of this critical section.<br/>
		 * The function provides next pixel number each call.
		 * 
		 * @param target target secondary Pixel object to copy the row/column of the
		 *               next pixel
		 * @return the progress percentage for follow up: if it is 0 - nothing to print,
		 *         if it is -1 - the task is finished, any other value - the progress
		 *         percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) {
			++col;
			++_counter;
			if (col < _maxCols) {
				target.row = this.row;
				target.col = this.col;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			++row;
			if (row < _maxRows) {
				col = 0;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			return -1;
		}

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * 
		 * @param target target secondary Pixel object to copy the row/column of the
		 *               next pixel
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print)
					System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print)
				System.out.printf("\r %02d%%", 100);
			return false;
		}
	}

	/**
	 * This function renders image's pixel color map from the scene included with
	 * the Renderer object
	 */
	public void renderImage() {
		final int nX = _imageWriter.getNx();
		final int nY = _imageWriter.getNy();
		java.awt.Color background = _scene.get_background().getColor();
		final double dist = _scene.get_distance();
		final double width = _imageWriter.getWidth();
		final double height = _imageWriter.getHeight();
		final Camera camera = _scene.get_camera();

		final Pixel thePixel = new Pixel(nY, nX);

		// Generate threads
		Thread[] threads = new Thread[_threads];
		for (int i = _threads - 1; i >= 0; --i) {
			threads[i] = new Thread(() -> {
				Pixel pixel = new Pixel();
				while (thePixel.nextPixel(pixel)) {
					Ray ray = camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row, //
							dist, width, height);
					GeoPoint closestPoint = findCLosestIntersection(ray);
					// if there is no geometry at that direction -> color as the background, else
					// color according to the geometry there and its position
					if (closestPoint == null) {
						_imageWriter.writePixel(pixel.col, pixel.row, background);
					} else {
						_imageWriter.writePixel(pixel.col, pixel.row, calcColor(closestPoint, ray).getColor());
					}
				}
			});
		}

		// Start threads
		for (Thread thread : threads)
			thread.start();

		// Wait for all threads to finish
		for (Thread thread : threads)
			try {
				thread.join();
			} catch (Exception e) {
			}
		if (_print)
			System.out.printf("\r100%%\n");
	}

//	
//	public void renderImage() {
//		Camera camera = _scene.get_camera();
//		java.awt.Color background = _scene.get_background().getColor();
//		int nX = _imageWriter.getNx();
//		int nY = _imageWriter.getNy();
//		double distance = _scene.get_distance();
//		double width = _imageWriter.getWidth();
//		double height = _imageWriter.getHeight();
//		// write to each pixel of the image
//		for (int col = 0; col < nX; col++) {
//			for (int row = 0; row < nY; row++) {
//				// construct a ray to each pixel and find the closest intersection point of that
//				// ray
//				// with the geometries of the scene
//				if (col > 150 && row > 375) {
//					System.out.print("hi");
//				}
//				Ray ray = camera.constructRayThroughPixel(nX, nY, col, row, distance, width, height);
//				GeoPoint closestPoint = findCLosestIntersection(ray);
//				// if there is no geometry at that direction -> color as the background, else
//				// color according to the geometry there and its position
//				if (closestPoint == null) {
//					_imageWriter.writePixel(col, row, background);
//				} else {
//					_imageWriter.writePixel(col, row, calcColor(closestPoint, ray).getColor());
//				}
//			}
//		}
//	}
	/**
	 * Set multithreading <br>
	 * - if the parameter is 0 - number of coress less 2 is taken
	 * 
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) {
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
		if (threads != 0)
			_threads = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			if (cores <= 2)
				_threads = 1;
			else
				_threads = cores;
		}
		return this;
	}

	/**
	 * Set debug printing on
	 * 
	 * @return the Render object itself
	 */
	public Render setDebugPrint() {
		_print = true;
		return this;
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
	 * from all the intersection points, this function find the closest point to the
	 * any point and return it
	 * 
	 * @param intersectionPoints the points that intersect the ray in a specific
	 *                           pixel
	 * @param point              to check the closest point to
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
	 * from all the intersection points, this function find the closest point to the
	 * camera and return it
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
					intensity = ls.getIntensity(closestPoint._point).scale(ktr);
					color = color.add(calcDiffuse(diffuse, l, normal, intensity),
							calcSpecular(specular, l, normal, v, nShinines, intensity));
				}
			}
		}

		// calculate reflection and refraction
		if (level == 1)
			return Color.BLACK;
		double kr = closestPoint._geometry.get_material().get_kR();
		double glossyDistance = closestPoint._geometry.get_material().get_glossyDistance();
		double glossyRadius = closestPoint._geometry.get_material().get_glossyRadius();
		double kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(normal, closestPoint._point, inRay);
			color = color.add(calcBeamColor(glossyRadius, glossyDistance, level -1, reflectedRay, normal, kkr).scale(kr));
		}

		double kt = closestPoint._geometry.get_material().get_kT();
		double kkt = k * kt;
		double diffDistance = closestPoint._geometry.get_material().get_diffDistance();
		double diffRadius =  closestPoint._geometry.get_material().get_diffRadius();
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(normal, closestPoint._point, inRay);
			color = color.add(calcBeamColor(diffRadius, diffDistance, level -1, refractedRay, normal, kkt).scale(kt));
		}
		return color;
	}

	/**
	 * 
	 * @param radius
	 * @param int distance
	 * @param level
	 * @param ray    the middle ray of the beam
	 * @param n
	 * @param k
	 * @return
	 */
	private Color calcBeamColor(double radius, double distance, int level, Ray ray, Vector normal, double k) {
		Point3D source = ray.get_p0();
		Vector dir = ray.get_dir();
		Point3D head = dir.get_head();
		double ndir = primitives.Util.alignZero(normal.dotProduct(dir));
		// if the angle between the normal and the reflection -> no significant to the reflected ray 
		if (ndir == 0)
			return Color.BLACK;

		GeoPoint closestPoint = findCLosestIntersection(ray);
		Color background = _scene.get_background();
		Color color;
		if (closestPoint == null)
		    color = background;
		else
			color = calcColor(closestPoint, ray, level - 1, k);

		if (radius == 0)
			return color;
	
		if (_numOfRays == 0)
			return color;

		double x = head.get_x().get();
		double y = head.get_y().get();
		double z = head.get_z().get();

		// build 2 normals to the ray to define the circle in that direction
		Vector normal1 = null;
		if (x <= y) {
			if (x <= z)
				normal1 = new Vector(0, z, -y).normalize();
			else
				normal1 = new Vector(-y, x, 0).normalize();
		} else {
			if (y <= z)
				normal1 = new Vector(-z, 0, x).normalize();
			else
				normal1 = new Vector(-y, x, 0).normalize();
		}
		Vector normal2 = normal1.crossProduct(dir).normalize();

		Point3D center = source.add(dir.scale(distance));
		for (int i = _numOfRays - 1; i > 0; --i) {
			Point3D point;
			Vector newRayDir;
			double nw;
			Random rand = new Random();
			double distanceFromCenter;
			do {
				// select random angle defined by sinus and cosinus of it
				point = center;
				x = rand.nextDouble() * 2 - 1;
				y = Math.sqrt(1 - x * x);
				if (!Util.isZero(x))
					point = point.add(normal1.scale(x));
				if (!Util.isZero(y))
					point = point.add(normal2.scale(y));
				
				///select any distance to go inside the circle, in the range of the radius
				distanceFromCenter = radius * (rand.nextDouble() * 2 - 1);
				point = center.add(point.subtract(center).scale(distanceFromCenter));
				newRayDir = point.subtract(source);
				nw = primitives.Util.alignZero(normal.dotProduct(newRayDir));
			} while (ndir > 0 && nw < 0 || ndir < 0 && nw > 0);

			Ray newRay = new Ray(source, newRayDir);
			closestPoint = findCLosestIntersection(newRay);
			color = color.add(closestPoint == null ? background : calcColor(closestPoint, newRay, level - 1, k));
		}
		return color.reduce(_numOfRays + 1);
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
