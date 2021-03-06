package ak.planets.util;

import org.lwjgl.BufferUtils;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

/**
 * Created by Aleksander on 11/10/2015.
 */
public class TextureUtil {
    public static ByteBuffer createTexture(BufferedImage image) {
        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        //4 for RGBA, 3 for RGB
        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);

        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                buffer.put((byte) (pixel & 0xFF));             // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));     // Alpha component. Only for RGBA
            }
        }
        buffer.flip();

        return buffer;
    }
}
