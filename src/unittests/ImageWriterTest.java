/**
 * 
 */
package unittests;

import java.awt.Color;

import org.junit.Test;

import renderer.ImageWriter;

/**
 * test the ImageWriter by writing grid to each pixel in the image
 *
 * @author Yochi Shtrauber 204023055 & Rachel Stone 315353938 email:
 *         yochishtrauber@gmail.com rachelstone1996@gmail.com
 */
public class ImageWriterTest {

	/**
     * {@link ImageWriter.writeToImage#writeToImage()}.
     */
	@Test
	public void testWriteToImage() {
		int width = 1600;
		int height = 1000;
		int nx = 800;
		int ny = 500;
		ImageWriter imageWriter = new ImageWriter("first test", width, height, nx, ny);
		for (int col = 0; col < nx; col++) {
			for (int row = 0; row < ny; row++) {
				if (col % 50 == 0 || row % 50 == 0) {
					imageWriter.writePixel(col, row, Color.GREEN);
				}
				else {
					imageWriter.writePixel(col, row, Color.ORANGE);
				}
			}
		}
		imageWriter.writeToImage();
	}

}
