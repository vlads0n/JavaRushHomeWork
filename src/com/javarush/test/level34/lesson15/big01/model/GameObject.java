package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Влад on 06.04.2016.
 */
public abstract class GameObject {

    private int x;
    private int y;
    private int width;
    private int height;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = Model.FIELD_SELL_SIZE;
        this.height = Model.FIELD_SELL_SIZE;
    }

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        if (width == 0)
            this.width = Model.FIELD_SELL_SIZE;
        else
            this.width = width;
        if (height == 0)
            this.height = Model.FIELD_SELL_SIZE;
        else
            this.height = height;
    }

    public abstract void draw(Graphics graphics);
}
