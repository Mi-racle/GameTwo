package com.miracle.graphics;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.miracle.input.KeyInput;

public class Renderer {

    private static GLProfile profile = null;
    private static GLWindow window = null;

    public static int screenWidth = 1024;
    public static int screenHeight = 768;

    public static double unitsWide = 1024;
    public static double unitsHigh = 768;

    public static void init() {
        GLProfile.initSingleton();
        profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);

        window = GLWindow.create(caps);
        window.setTitle("Reflect!");
        window.setSize(screenWidth, screenHeight);
        window.setResizable(false);
        window.addGLEventListener(new EventListener());
        window.addKeyListener(new KeyInput());

        //window.setFullscreen(true);

        //FPSAnimator animator = new FPSAnimator(window, 120);
        //animator.start();

        window.setVisible(true);
    }

    public static GLProfile getProfile() {
        return profile;
    }

    public static void render() {
        if (window == null) {
            return;
        }
        window.display();
    }

    public static int getScreenWidth() {
        return window.getWidth();
    }

    public static int getScreenHeight() {
        return window.getHeight();
    }

}
