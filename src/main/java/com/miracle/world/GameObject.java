package com.miracle.world;

public abstract class GameObject {

    double x = 0;
    double y = 0;

    double width = 1;
    double height = 1;

    void update() {};

    void render() {};

    void setX(double x) {this.x = x;}

    void setY(double y) {this.y = y;}

    void setWidth(double width) {this.width = width;}

    void setHeight(double height) {this.height = height;}

}
