/**
 * 
 */
package unittests;

import org.junit.Test;

import elements.*;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 *
 */
public class ReflectionRefractionTests {

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(0, Color.BLACK));

		scene.addGeometries(
				new Sphere(new Material(0.4, 0.3, 100,  0.3, 0), new Color(java.awt.Color.BLUE),
						new Point3D(0, 0, 50), 50),
				new Sphere(new Material(0.5, 0.5, 100), new Color(java.awt.Color.RED), new Point3D(0, 0, 50), 25));

		scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
				0.0004, 0.0000006));

		ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(10000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(0.1, new Color(255, 255, 255)));

		scene.addGeometries(
				new Sphere(new Material(0.25, 0.25, 20, 0.5, 0), new Color(0, 0, 100), new Point3D(-950, 900, 1000),
						400),
				new Sphere(new Material(0.25, 0.25, 20), new Color(100, 20, 20), new Point3D(-950, 900, 1000), 200),
				new Triangle(new Material(0, 0, 0, 0, 1), new Color(20, 20, 20), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
				new Triangle(new Material(0, 0, 0, 0, 0.5), new Color(20, 20, 20), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

		scene.addLights(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, 750, 150), new Vector(-1, 1, 4), 1,
				0.00001, 0.000005));

		ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(0.15, new Color(java.awt.Color.WHITE)));

		scene.addGeometries( //
				new Triangle(new Material(0.5, 0.5, 60), Color.BLACK, //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(new Material(0.5, 0.5, 60), Color.BLACK, //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Material(0.2, 0.2, 30, 0.6, 0), new Color(java.awt.Color.BLUE), // )
						new Point3D(60, -50, 50), 30));

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

		ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void TrianglesOnSphere() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -8000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(10000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(0.1, new Color(255, 255, 255)));

		scene.addGeometries(
				new Sphere(new Material(0.5, 0.5, 20, 0.0, 0.8), new Color(0, 100, 50), new Point3D(-400, 300, 1000),
						400),
				new Triangle(new Material(0, 0, 0, 0.2, 0), new Color(100, 0, 200), new Point3D(-100, -100, 1000),
						new Point3D(100, -300, 1200), new Point3D(0, 50, -1100)),
				new Triangle(new Material(0, 0, 0, 0.2, 0.5), new Color(100, 0, 200), new Point3D(-100, -100, 1000),
						new Point3D(100, -300, 1200), new Point3D(-300, -200, 1500)),
				new Triangle(new Material(0.25, 0.25, 10, 0.2, 1), new Color(100, 0, 200), new Point3D(-100, -100, 1000),
						new Point3D(100, -300, 1200), new Point3D(-200, -300, -900)),
				new Triangle(new Material(0, 0, 0, 0, 1), new Color(0, 50, 50), new Point3D(-1500, -1500, 2500),
						new Point3D(1100, -1100, 1800), new Point3D(1500, 1500, 2500)));

		scene.addLights(
				new SpotLight(new Color(1000, 1000, 10000), new Point3D(350, -500, 1000), new Vector(-1, 1, -1), 1,
						0.00001, 0.000005),
				new SpotLight(new Color(5000, 2000, 2000), new Point3D(-2000, 1300, -400), new Vector(1, -1, 1), 1,
						0.00001, 0.000005));

		ImageWriter imageWriter = new ImageWriter("TrianglesOnSphere", 2500, 2500, 1000, 1000);
		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.writeToImage();
	}
}
