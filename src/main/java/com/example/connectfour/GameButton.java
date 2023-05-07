package com.example.connectfour;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class GameButton extends Button{
    // Keeps track of what buttons have been clicked
    private boolean clicked;
    /*
    ID is xy together
    x and y are the coordinates of the button
    player is either player 1 or player 2
    */
    private int id, x, y, player;

    // Constructor that also styles the button into a circle
    public GameButton() {
        clicked = false;
        player = 0;
        id = 0;
        x = 0;
        y= 0;
        this.setStyle( "-fx-background-radius: 5em; " +
                "-fx-min-width: 4.8em;" +
                "-fx-min-height: 4.8em;" +
                "-fx-max-width: 4.8em;" +
                "-fx-max-height: 4.8em;");
    }


    // This method is called when a Button is click and
    // will update the button image and disable it and
    // then disable it
    public void click(int player, Image image) {
        this.clicked = true;
        this.player = player;
        this.setContentDisplay(ContentDisplay.CENTER);
        this.setDisable(true);
        Circle cicrleImage = new Circle(50,50,30);
        cicrleImage.setFill(new ImagePattern(image));
        cicrleImage.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));
        this.setGraphic(cicrleImage);
        // make it a circle again
        this.setStyle( "-fx-background-radius: 5em; " +
                "-fx-background-position: center center;" +
                "-fx-background-size: cover;"+
                "-fx-min-width: 4.8em;" +
                "-fx-min-height: 4.8em;" +
                "-fx-max-width: 4.8em;" +
                "-fx-max-height: 4.8em;" +
                "-fx-opacity: 1.0");
    }

    // This method will clear a button by re enabling it
    // and removing the previous player image
    public void clear() {
        clicked = false;
        player = 0;
        this.setDisable(false);
        this.setGraphic(null);
    }

    // This method updates the theme of the button
    public void updateTheme(Image image) {
        Circle cicrleImage = new Circle(50,50,30);
        cicrleImage.setFill(new ImagePattern(image));
        cicrleImage.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));
        this.setGraphic(cicrleImage);
        // make it a circle again
        this.setStyle( "-fx-background-radius: 5em; " +
                "-fx-background-position: center center;" +
                "-fx-background-size: cover;"+
                "-fx-min-width: 4.8em;" +
                "-fx-min-height: 4.8em;" +
                "-fx-max-width: 4.8em;" +
                "-fx-max-height: 4.8em;" +
                "-fx-opacity: 1.0");
    }

    //--------------------------------->
    // Setters for private data members of "Button"
    public void setPlayer(int player) {
        this.player = player;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    //<--------------------------------

    //--------------------------------->
    // Getters for private data members of "Button"
    public int getPlayer() {
        return player;
    }

    public boolean isClicked() {
        return clicked;
    }

    public int getID() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    //<--------------------------------
}