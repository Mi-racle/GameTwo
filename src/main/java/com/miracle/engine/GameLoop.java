package com.miracle.engine;

import com.miracle.graphics.Renderer;
import com.miracle.world.World;

public class GameLoop {

    private static boolean running = false;
    private static int updates = 0;

    private static final int MAX_UPDATES = 5;

    private static long lastUpdateTime = 0;

    private static final int TARGET_FPS = 120;
    private static final int TARGET_TIME = 1000000000 / TARGET_FPS;

    public static void start() {
        Thread thread = new Thread(
                () -> {

                    running = true;

                    lastUpdateTime = System.nanoTime();

                    int fps = 0;
                    long lastFPSCheck = System.nanoTime();

                    while (running) {
                        long currentTime = System.nanoTime();

                        updates = 0;

                        while (currentTime - lastUpdateTime >= TARGET_TIME) {
                            World.update();
                            lastUpdateTime += TARGET_TIME;
                            updates++;

                            if (updates > MAX_UPDATES) {
                                break;
                            }
                        }

                        Renderer.render();

                        fps++;
                        if (System.nanoTime() >= lastFPSCheck + 1000000000) {
                            //System.out.println(fps);
                            fps = 0;
                            lastFPSCheck = System.nanoTime();
                        }

                        long timeTaken = System.nanoTime() - currentTime;
                        if (TARGET_TIME > timeTaken) {
                            try {
                                Thread.sleep((TARGET_TIME - timeTaken) / 1000000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
        );
        thread.setName("GameLoop");
        thread.start();
    }

}
