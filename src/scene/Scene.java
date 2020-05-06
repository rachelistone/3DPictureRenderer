/**
 * 
 */
package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * the class scene defines the picture, the geometries, lights, camera and ect.
 * 
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public class Scene {

	/**
	 * the name of the scene
	 */
	private String _name;
	
	/**
	 * the color of the background
	 */
	private Color _background;
	
	/**
	 * the intensity of the ambient light of the scene
	 */
	private AmbientLight _ambientLight;
	
	/**
	 * the collection of the geometries in the scene
	 */
	private Geometries _geometries;
	
	/**
	 * the camera, the view point to the scene
	 */
	private Camera _camera;
	
	/**
	 * the distance of the camera from the view plane
	 */
	private double _distance;
	
	/**
	 * 
	 */
	public Scene(String sceneName) {
		_name = sceneName;
		_geometries = new Geometries();
	}

	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}

	/**
	 * @return the _background
	 */
	public Color get_background() {
		return _background;
	}

	/**
	 * @return the _ambientLight
	 */
	public AmbientLight get_ambientLight() {
		return _ambientLight;
	}

	/**
	 * @return the _geometries
	 */
	public Geometries get_geometries() {
		return _geometries;
	}

	/**
	 * @return the _camera
	 */
	public Camera get_camera() {
		return _camera;
	}

	/**
	 * @return the distance
	 */
	public double get_distance() {
		return _distance;
	}

	/**
	 * @param _background the _background to set
	 */
	public void set_background(Color _background) {
		this._background = _background;
	}

	/**
	 * @param _ambientLight the _ambientLight to set
	 */
	public void set_ambientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}

	/**
	 * @param _camera the _camera to set
	 */
	public void set_camera(Camera _camera) {
		this._camera = _camera;
	}

	/**
	 * @param distance the distance to set
	 */
	public void set_distance(double distance) {
		this._distance = distance;
	}

	/**
	 * add geometries to the scene 
	 * 
	 * @param geometries to add to the scene
	 */
	public void addGeometries(Intersectable ... geometries) {
		_geometries.add(geometries);
	}
}
