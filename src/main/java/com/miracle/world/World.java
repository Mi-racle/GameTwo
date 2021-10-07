package com.miracle.world;

import java.util.ArrayList;

public class World {

    private static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static ArrayList<Picture> pictures = new ArrayList<>();

    public static void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }

        for (Picture picture : pictures) {
            picture.update();
        }
    }

    public static void render() {
        for (GameObject gameObject : gameObjects) {
            gameObject.render();
        }

        for (Picture picture : pictures) {
            picture.render();
        }
    }

    public static void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public static void removeObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public static void addPicture(Picture picture) {
        pictures.add(picture);
    }

}
