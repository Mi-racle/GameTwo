package com.miracle.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.miracle.loader.ImageResource;

public class Graphics {

    public static void drawImage(ImageResource image, double x, double y, double width, double height) {
        GL2 gl = EventListener.gl;

        Texture tex = image.getTexture();

        if (tex != null) {
            gl.glBindTexture(GL2.GL_TEXTURE_2D, tex.getTextureObject());
        }

        gl.glBegin(GL2.GL_QUADS);

        gl.glTexCoord2d(0, 0);
        gl.glVertex2d(x, y);
        gl.glTexCoord2d(1, 0);
        gl.glVertex2d(width + x, y);
        gl.glTexCoord2d(1, 1);
        gl.glVertex2d(width + x, height + y);
        gl.glTexCoord2d(0, 1);
        gl.glVertex2d(x, height + y);

        gl.glEnd();
        gl.glFlush();
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);

    }

}
