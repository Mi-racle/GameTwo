package com.miracle.graphics;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.miracle.input.KeyInput;
import com.miracle.loader.ImageResource;
import com.miracle.world.Picture;
import com.miracle.world.World;

public class EventListener implements GLEventListener {

    public static double ratioWidth = 1;
    public static double ratioHeight = 1;

    public static GL2 gl = null;

    public static Picture currentPicture = null;
    private int currentId = 0;

    private long lastCheckTime = 0;

    private static final int MAX_PICTURE = 9;
    private static final long REFLECTION_TIME = 500000000; // 500ms

    @Override
    public void init(GLAutoDrawable drawable) {
        ratioWidth = Renderer.getScreenWidth() / (double) Renderer.screenWidth;
        ratioHeight = Renderer.getScreenHeight() / (double) Renderer.screenHeight;

        gl = drawable.getGL().getGL2();

        gl.glClearColor(1, 1, 1, 1);
        gl.glEnable(GL2.GL_TEXTURE_2D);

        currentPicture =
                new Picture(new ImageResource("../reflection/" + currentId + ".bmp"), 0, 0, currentId);
        currentId ++;
        World.addPicture(currentPicture);
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
        if (currentId <= MAX_PICTURE) {
            if (currentTime - lastCheckTime >= REFLECTION_TIME) {
                KeyInput.disableKey(KeyEvent.VK_DOWN);
                int curY = (currentId - 1) / 3;
                currentPicture =
                        new Picture(
                                new ImageResource(
                                        "../reflection/" + currentId + ".bmp"),
                                56 + 56 * ((currentId - 1) % 3),
                                76 * curY,
                                currentId
                        );
                currentId++;
                World.addPicture(currentPicture);
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
}
