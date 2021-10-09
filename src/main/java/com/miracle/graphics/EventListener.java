package com.miracle.graphics;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.miracle.input.KeyInput;
import com.miracle.loader.ImageResource;
import com.miracle.world.Picture;
import com.miracle.world.World;

import java.util.Random;

public class EventListener implements GLEventListener {

    public static double ratioWidth = 1;
    public static double ratioHeight = 1;

    public static GL2 gl = null;

    private static Picture givenPicture = null;
    private int pictureCount = 0;

    private long lastCheckTime = 0;

    private static final int PICTURE_WIDTH = 56 * 3;
    private static final int PICTURE_HEIGHT = 76 * 3;
    private static final int MAX_PICTURE_COUNT = 3 * 5;
    private static final int PICTURE_TYPES = 9;
    private static final long REFLECTION_TIME = 500000000; // 500ms

    @Override
    public void init(GLAutoDrawable drawable) {
        ratioWidth = Renderer.getScreenWidth() / (double) Renderer.screenWidth;
        ratioHeight = Renderer.getScreenHeight() / (double) Renderer.screenHeight;

        gl = drawable.getGL().getGL2();

        gl.glClearColor(1, 1, 1, 1);
        gl.glEnable(GL2.GL_TEXTURE_2D);

        int givenId = (int) (Math.random() * 9);
        givenPicture =
                new Picture(new ImageResource("../reflection/" + givenId + ".bmp"), 0, 0, givenId, -1, 3);

        World.addPicture(givenPicture);
        lastCheckTime = System.nanoTime();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        System.exit(0);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        ratioWidth = Renderer.getScreenWidth() / (double) Renderer.screenWidth;
        ratioHeight = Renderer.getScreenHeight() / (double) Renderer.screenHeight;

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        long currentTime = System.nanoTime();
        if (pictureCount < MAX_PICTURE_COUNT) {
            if (currentTime - lastCheckTime >= REFLECTION_TIME) {
                KeyInput.disableKey(KeyEvent.VK_DOWN);
                int curX = pictureCount % 5;
                int curY = pictureCount / 5;
                int currentId = (int) (Math.random() * PICTURE_TYPES);
                Picture currentPicture = new Picture(
                        new ImageResource(
                                "../reflection/" + currentId + ".bmp"),
                        PICTURE_WIDTH + PICTURE_WIDTH * curX,
                        PICTURE_HEIGHT * curY,
                        currentId,
                        pictureCount,
                        3
                );
                World.addPicture(currentPicture);
                pictureCount ++;
                lastCheckTime = System.nanoTime();
            }
        }

        World.render();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int i, int i1, int i2, int i3) {
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, Renderer.getScreenWidth(), Renderer.getScreenHeight(), 0, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    public static Picture getGivenPicture() {
        return givenPicture;
    }
}
