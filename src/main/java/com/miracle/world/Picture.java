package com.miracle.world;

import com.jogamp.newt.event.KeyEvent;
import com.miracle.graphics.EventListener;
import com.miracle.graphics.Graphics;
import com.miracle.input.KeyInput;
import com.miracle.loader.ImageResource;

public class Picture extends GameObject {

    private ImageResource image;

    private int identification = 0;

    public Picture(ImageResource image, double x, double y, int identification) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.identification = identification;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    @Override
    public void update() {
        if (KeyInput.getKey(KeyEvent.VK_DOWN)) {
            if (this.equals(EventListener.currentPicture)) {
                image = new ImageResource("../reflection/" + identification + "A.bmp");
            }
        }
    }

    @Override
    public void render() {
        Graphics.drawImage(
                image,
                x * EventListener.ratioWidth,
                y * EventListener.ratioHeight,
                width * EventListener.ratioWidth,
                height * EventListener.ratioHeight
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null) {
            return false;
        }

        if (object instanceof Picture picture) {
            return this.identification == picture.identification;
        }

        return false;
    }
}
