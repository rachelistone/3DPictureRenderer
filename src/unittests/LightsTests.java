package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class LightsTests {

    /**
     * Produce a picture of a sphere lighted by a directional light
     */
    @Test
    public void sphereDirectional() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(0, Color.BLACK));

        scene.addGeometries(
                new Sphere(new Material(0.5, 0.5, 100), new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50), 50));

        scene.addLights(new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1)));

        ImageWriter imageWriter = new ImageWriter("sphereDirectional", 150, 150, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a point light
     */
    @Test
    public void spherePoint() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(0, Color.BLACK));

        scene.addGeometries(
                new Sphere(new Material(0.5, 0.5, 100), new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50), 50));

        scene.addLights(new PointLight(new Color(500, 300, 0), new Point3D(-50, 50, -50), 1, 0.00001, 0.000001));

        ImageWriter imageWriter = new ImageWriter("spherePoint", 150, 150, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void sphereSpot() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(0, Color.BLACK));

        scene.addGeometries(
                new Sphere(new Material(0.5, 0.5, 100), new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50), 50));

        scene.addLights(new SpotLight(new Color(500, 300, 0), new Point3D(-50, 50, -50),
                new Vector(1, -1, 2), 1, 0.00001, 0.00000001));

        ImageWriter imageWriter = new ImageWriter("sphereSpot", 150, 150, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a directional light
     */
    @Test
    public void trianglesDirectional() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(0.15, new Color(java.awt.Color.WHITE)));

        scene.addGeometries(
                new Triangle(new Material(0.8, 0.2, 300), Color.BLACK,
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(new Material(0.8, 0.2, 300), Color.BLACK, 
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene.addLights(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1)));

        ImageWriter imageWriter = new ImageWriter("trianglesDirectional", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a point light
     */
    @Test
    public void trianglesPoint() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(0.15, new Color(java.awt.Color.WHITE)));

        scene.addGeometries(
                new Triangle(new Material(0.5, 0.5, 300), Color.BLACK, 
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(new Material(0.5, 0.5, 300), Color.BLACK, 
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene.addLights(new PointLight(new Color(500, 250, 250),
                new Point3D(10, 10, 130),
                1, 0.0005, 0.0005));

        ImageWriter imageWriter = new ImageWriter("trianglesPoint", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light
     */
    @Test
    public void trianglesSpot() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(0.15, new Color(java.awt.Color.WHITE)));

        scene.addGeometries(
                new Triangle(new Material(0.5, 0.5, 300), Color.BLACK, 
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(new Material(0.5, 0.5, 300), Color.BLACK, 
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene.addLights(new SpotLight(new Color(500, 250, 250),
                new Point3D(10, 10, 130), new Vector(-2, 2, 1),
                1, 0.0001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("trianglesSpot", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.writeToImage();
    }
    
    /**
     * Produce a picture of a sphere lighted by multiply light sources
     */
    @Test
    public void sphereMultiplyLights() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(0, Color.BLACK));

        scene.addGeometries(
                new Sphere(new Material(0.5, 0.5, 100), new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50), 50));

        scene.addLights(new PointLight(new Color(100, 300, 0), new Point3D(200, -150, -100), 1, 0.00001, 0.000001));
        scene.addLights(new SpotLight(new Color(500, 300, 0), new Point3D(100, 50, -60),
                new Vector(1, -1, 2), 1, 0.00001, 0.00000001));
        scene.addLights(new SpotLight(new Color(500, 300, 500), new Point3D(-100, -100, -50),
                new Vector(1, 10, 10), 1, 0.00001, 0.00000001));
        scene.addLights(new DirectionalLight(new Color(500, 300, 0), new Vector(0, 10, -10)));

        ImageWriter imageWriter = new ImageWriter("sphereMultiplyLights", 150, 150, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.writeToImage();
    }
    
    /**
     * Produce a picture of a two triangles lighted by multiply light sources
     */
    @Test
    public void trianglesmultiplyLights() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(0.15, new Color(java.awt.Color.WHITE)));

        scene.addGeometries(
                new Triangle(new Material(0.5, 0.5, 300), Color.BLACK, 
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(new Material(0.5, 0.5, 300), Color.BLACK, 
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene.addLights(new PointLight(new Color(255, 255, 15),
                new Point3D(-30, 50, 100),
                1, 0.000005, 0.000005));
        scene.addLights(new PointLight(new Color(100, 0, 0),
                new Point3D(20, -50, 130),
                1, 0.0005, 0.0005));
        scene.addLights(new SpotLight(new Color(255, 165, 500), new Point3D(0, 0, 0),
                new Vector(1, -1, 1), 1, 0.00001, 0.00000001));
        scene.addLights(new DirectionalLight(new Color(0, 100, 150), new Vector(-2, 0, 1)));

        ImageWriter imageWriter = new ImageWriter("trianglesMultiplyLights", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.writeToImage();
    }

    
}
  
