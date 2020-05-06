package unittests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * Test rendering a basic image
 * 
 * @author Dan & we
 */
public class RenderTests {

	/**
	 * Produce a scene with basic 3D model and render it into a jpeg image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(100);
		scene.set_background(new Color(75, 127, 90));
		scene.set_ambientLight(new AmbientLight(1, new Color(255, 191, 191)));

		scene.addGeometries(new Sphere(new Point3D(0, 0, 100), 50));

		scene.addGeometries(
				new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
				new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
				new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
				new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

		ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.printGrid(50, java.awt.Color.YELLOW);
		render.writeToImage();
	}

	/**
     * {@link Render.getClosestPoint#getClosestPoint(List<Point3D>)}.
     */
	public void getClosestPoint() {

		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
		
		ImageWriter imageWriter = new ImageWriter("closest point", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		
		assertEquals("the point is not the closest one", new Point3D(0, 0, 110),
				render.getClosestPoint(List.of(new Point3D(0, 0, 130), new Point3D(0, 0, 110), new Point3D(0, 0, 120))));

	}
}

