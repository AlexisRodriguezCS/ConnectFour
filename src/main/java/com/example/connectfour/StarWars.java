package com.example.connectfour;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StarWars {

    public static Menu createMenuBttn(String title,Font font, String color) {
        Menu menuBttn = new Menu("");
        Label menuLabel = new Label(title);
        menuLabel.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));
        menuLabel.setStyle("-fx-text-fill: " + color + ";");
        menuLabel.setFont(font);
        menuBttn.setGraphic(menuLabel);
        return menuBttn;
    }

    public static void createMenuItems(Menu Root,String title,Font font, String color, String menuBackgroundColor) {
        MenuItem item = new MenuItem("");
        Label itemLabel = new Label(title);
        itemLabel.setFont(font);
        itemLabel.setMinWidth(Region.USE_PREF_SIZE);
        itemLabel.setStyle("-fx-text-fill: " + color + ";"
                + "-fx-background-color: " + menuBackgroundColor + ";");
        item.setGraphic(itemLabel);
        Root.getItems().add(item);
    }

    public static MenuBar createMenu() {
        String menuTextColor = "White";
        String menuBackgroundColor = "#7B4736";
        Font font = Font.loadFont(Main.class.getResource("sfdistantgalaxy.ttf").toExternalForm(), 20);

        // Main Menu Buttons
        Menu gamePlay = createMenuBttn("Game Play", font, menuTextColor);
        Menu themes = createMenuBttn("Themes", font, menuTextColor);
        Menu options = createMenuBttn("Options", font, menuTextColor);

        // Add menu items to Menus
        createMenuItems(gamePlay,"Reverse",font,menuTextColor, menuBackgroundColor);
        createMenuItems(options,"How To Play",font,menuTextColor, menuBackgroundColor);
        createMenuItems(options,"New Game",font,menuTextColor, menuBackgroundColor);
        createMenuItems(options,"Exit",font,menuTextColor, menuBackgroundColor);

        // add menu items to themes Menu
        RadioMenuItem starWars = new RadioMenuItem("Star Wars");
        RadioMenuItem rickMorty = new RadioMenuItem("Rick & Morty");
        RadioMenuItem dragonBall = new RadioMenuItem("Dragon Ball Z");
        starWars.setSelected(true);
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(starWars, rickMorty, dragonBall); // Themes must be added to toggle group to make them mutually exclusive
        themes.getItems().addAll(starWars, rickMorty, dragonBall);

        // Add menus to menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(gamePlay, themes, options);

        // Style the menu
        menuBar.setStyle("-fx-background-color: #7B4736;" +
                "-fx-selection-bar: black;" +
                //"-fx-background-radius: 10 10 10 10;" +
                "-fx-font-size: 16pt;");
        return menuBar;
    }

}
