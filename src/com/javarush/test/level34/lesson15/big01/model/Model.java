package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;

public class Model  {

    public static int FIELD_SELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("C:\\Program Files\\JetBrains\\JavaRushHomeWork\\src\\com\\javarush\\test\\level34\\lesson15\\big01\\res\\levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    private void restartLevel(int level) {
        this.gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction))
            return;
        if (checkBoxCollision(direction))
            return;
        switch (direction) {
            case LEFT:
                player.move(-FIELD_SELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_SELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_SELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_SELL_SIZE);
                break;
        }
        checkCompletion();
    }

    private boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls())
            if (gameObject.isCollision(wall, direction))
                return true;
        return false;
    }

    private boolean checkBoxCollision(Direction direction) {
        boolean boxCollision = false;
        Player player = gameObjects.getPlayer();
        for (Box box1 : gameObjects.getBoxes()) {
            if (player.isCollision(box1, direction)) {
                for (Box box2 : gameObjects.getBoxes()) {
                    if (box1.isCollision(box2, direction) || checkWallCollision(box1, direction))
                        boxCollision = true;
                }
                if (!boxCollision){
                    switch (direction) {
                        case LEFT:
                            box1.move(-FIELD_SELL_SIZE, 0);
                            break;
                        case RIGHT:
                            box1.move(FIELD_SELL_SIZE, 0);
                            break;
                        case UP:
                            box1.move(0, -FIELD_SELL_SIZE);
                            break;
                        case DOWN:
                            box1.move(0, FIELD_SELL_SIZE);
                        }
                }
            }
        }
        return boxCollision;
    }
    private void checkCompletion() {
        int count = 0;
        for (Home home : gameObjects.getHomes())
            for (Box box : gameObjects.getBoxes())
                if (home.getX() == box.getX() && home.getY() == box.getY())
                    count++;
        if (count == gameObjects.getHomes().size())
            eventListener.levelCompleted(currentLevel);
    }
}
