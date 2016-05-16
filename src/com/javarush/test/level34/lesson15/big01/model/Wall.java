package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Влад on 08.04.2016.
 */
public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
