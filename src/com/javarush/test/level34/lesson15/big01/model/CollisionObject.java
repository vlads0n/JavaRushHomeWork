package com.javarush.test.level34.lesson15.big01.model;


/**
 * Created by Влад on 08.04.2016.
 */
public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    boolean isCollision(GameObject gameObject, Direction direction) {
        switch (direction) {
            case LEFT:
                return (gameObject.getX() == this.getX() - Model.FIELD_SELL_SIZE) && (gameObject.getY() == this.getY());
            case RIGHT:
                return (gameObject.getX() == this.getX() + Model.FIELD_SELL_SIZE) && (gameObject.getY() == this.getY());
            case UP:
                return (gameObject.getX() == this.getX()) && (gameObject.getY() == this.getY() - Model.FIELD_SELL_SIZE);
            case DOWN:
                return (gameObject.getX() == this.getX()) && (gameObject.getY() == this.getY() + Model.FIELD_SELL_SIZE);
        }
        return false;
    }
}
