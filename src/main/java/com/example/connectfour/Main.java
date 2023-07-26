/***************************************************************
 * File: Main.java
 * Purpose: Represents the main class for the Connect Four game.
 *          It contains the graphical user interface and game logic.
 * Author: Alexis Rodriguez
 ***************************************************************/

// Import statements
package com.example.connectfour;

import java.util.*;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    final int boardBackground = 2;
    final int winingBackground = 4;
    final int WIDTH = 900;
    final int HEIGHT = 600;
    boolean win = true;
    int player = 0;
    String theme = "StarWars";
    String[] playerString = new String[] {
            "Player One's Turn",
            "Player Two's Turn"
    };
    GameButton button;
    Map< Integer, GameButton > map = new HashMap<>();
    Stack< Integer > stack = new Stack<>();
    // Create border pane that will hold all elements of the Game Scene, background is updated with theme
    BorderPane borderPane = new BorderPane();
    // Text Box that will show who is next
    Text playerText = new Text("");
    // Show image of player who is next
    Circle circleImage = new Circle(75, 75, 45);
    // Text Box that shows the moves
    Text movesText = new Text("");
    // Holds the images and background of each theme
    Map < String, String[] > themeMap = new HashMap<>();
    Set< Integer > winningButtons = new HashSet<>();
    HashMap < String, Scene > sceneMap;
    StackPane itemStart;
    Button playButton = new Button("Play Again");
    EventHandler<ActionEvent> myHandler;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // This HashMap will hold all the scenes
        sceneMap = new HashMap<>();
        primaryStage.setTitle("Connect Four");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icon.png"))));

        //this handler is used by all the game buttons
        myHandler = new EventHandler<>() {
            public void handle(ActionEvent e) {
                GameButton temp = (GameButton) e.getSource();
                // Checks if button below has been clicked or if button is on lower bound
                if ((temp.getY() == 5) || map.get(temp.getID() + 1).isClicked()) {
                    // Push button to stack, used for reverse
                    stack.push(temp.getID());
                    // Show move
                    movesText.setText("Player " + (player + 1) + " moved to [" + (temp.getX() + 1) + "," + (temp.getY() + 1) + "]");
                    movesText.setFill(Color.WHITE);
                    movesText.setStyle("-fx-font-size: 25px;" + "-fx-font-family: 'Roboto';" + "-fx-background-color: black;" + "-fx-text-fill: white;");
                    movesText.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));

                    // Update the button
                    temp.click(player, new Image(Objects.requireNonNull(getClass().getResourceAsStream(themeMap.get(theme)[player]))));
                    if (checkWin(temp.getID())) { // Check if recent move connected 4
                        // clear all buttons except winning buttons
                        map.forEach((k, v) -> {
                            if (!winningButtons.contains(k)) {
                                v.clear();
                            }
                        });

                        sceneMap.put("win", winScene());
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.play();
                        pause.setOnFinished((v) -> primaryStage.setScene(sceneMap.get("win")));
                    } else if (stack.size() == 42) {
                        // If all the buttons have been pressed, the stack has 42 buttons, and it is a tie
                        win = false;
                        sceneMap.put("win", winScene());
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.play();
                        pause.setOnFinished((v) -> primaryStage.setScene(sceneMap.get("win")));
                    }
                    // Switch to next player and update texts in screen
                    updatePlayer();
                } else { // If move is not valid, show in moves text
                    movesText.setText("Player " + (player + 1) + " moved to " + (temp.getX() + 1) + "," + (temp.getY() + 1) + ". This is NOT a valid move. Player " + (player + 1) + " pick again.");
                }
            }
        };

        //Three scenes returned from two methods; put in hashmap
        sceneMap.put("welcome", welcome());
        sceneMap.put("gameplay", gameplay());
        sceneMap.put("win", winScene());
        itemStart.setOnMouseClicked(e -> primaryStage.setScene(sceneMap.get("gameplay")));
        playButton.setOnAction(e -> {
            resetGame();
            primaryStage.setScene(sceneMap.get("gameplay"));
        });
        primaryStage.setScene(sceneMap.get("welcome"));
        primaryStage.show();
    }

    // This is the welcome scene where the user can click either
    // the start or exit game button
    public Scene welcome() {

        // Create title
        Text title = new Text("Connect\nFour");
        title.setFill(Color.GOLD);
        title.setStyle("-fx-font-weight: 800");
        title.setTextAlignment(TextAlignment.CENTER);
        VBox titleBox = new VBox(20, title);
        titleBox.setAlignment(Pos.CENTER);
        title.setFont(Font.loadFont(
                Objects.requireNonNull(Main.class.getResource("sf-distant-galaxy.outline.ttf")).toExternalForm(), 58));

        // Create menu box to put start and exit items
        // Menu button should be added to border pane at center
        VBox menuBox = new VBox(15);

        // Create pane that will hold the outline and text of the menu items
        itemStart = new StackPane();
        VBox itemExit = new VBox();

        //Create the shadow effects for menu items
        DropShadow shadow = new DropShadow(20, Color.GOLD);
        DropShadow noShadow = new DropShadow(0, Color.GOLD);

        // Button design shape------------------------------------------------------>
        // Create outlines for menu items
        Polygon outlineStart = new Polygon(
                0, 0,
                190, 0,
                205, 20,
                190, 40,
                0, 40);
        outlineStart.setStroke(Color.GOLD);
        outlineStart.setStrokeWidth(2);
        outlineStart.fillProperty().bind(Bindings.when(itemStart.hoverProperty())
                .then(Color.GOLD)
                .otherwise(Color.BLACK));
        outlineStart.setEffect(shadow);
        outlineStart.effectProperty().bind(Bindings.when(itemStart.hoverProperty())
                .then(shadow)
                .otherwise(noShadow));
        //<---------------------------------------------------------------------------

        // Button design shape------------------------------------------------------>
        Polygon outlineExit = new Polygon(
                0, 0,
                190, 0,
                205, 20,
                190, 40,
                0, 40);
        outlineExit.setStroke(Color.GOLD);
        outlineExit.setStrokeWidth(2);
        outlineExit.fillProperty().bind(Bindings.when(itemExit.hoverProperty())
                .then(Color.GOLD)
                .otherwise(Color.BLACK));
        outlineExit.setEffect(shadow);
        outlineExit.effectProperty().bind(Bindings.when(itemExit.hoverProperty())
                .then(shadow)
                .otherwise(noShadow));
        //<---------------------------------------------------------------------------

        // Create text for menu items
        Text startText = new Text("Start Game");
        startText.setFont(Font.loadFont(Objects.requireNonNull(Main.class.getResource("sfdistantgalaxy.ttf")).toExternalForm(), 20));
        startText.fillProperty().bind(Bindings.when(itemStart.hoverProperty())
                .then(Color.BLACK)
                .otherwise(Color.WHITE));

        Text exitText = new Text("Exit Game");
        exitText.setTranslateX(-10);
        exitText.setTranslateY(-30);
        exitText.setFont(Font.loadFont(Objects.requireNonNull(Main.class.getResource("sfdistantgalaxy.ttf")).toExternalForm(), 20));
        exitText.fillProperty().bind(Bindings.when(itemExit.hoverProperty())
                .then(Color.BLACK)
                .otherwise(Color.WHITE));

        // Join outline and text into same item
        itemStart.getChildren().addAll(outlineStart, startText);
        itemStart.setAlignment(Pos.CENTER);
        itemExit.getChildren().addAll(outlineExit, exitText);
        itemExit.setAlignment(Pos.CENTER);

        // Add items to menu box
        menuBox.getChildren().addAll(itemStart, itemExit);
        menuBox.setAlignment(Pos.CENTER);

        // Add the title and menuBox to center box
        VBox centerBox = new VBox(20, titleBox, menuBox);
        centerBox.setAlignment(Pos.CENTER);

        // Add centerBox to a border pane
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(centerBox);
        borderPane.setStyle("-fx-background-image: url('" + Objects.requireNonNull(getClass().getResource("HomeScreen.gif")).toExternalForm() + "');");


        // Exit menu item closes the game
        itemExit.setOnMouseClicked(e -> Platform.exit());

        //Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        return new Scene(borderPane, WIDTH, HEIGHT);
    }

    // This is the scene that will include the "Connect 4" board
    public Scene gameplay() {
        loadThemes();
        circleImage.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResourceAsStream(themeMap.get(theme)[player])))));
        circleImage.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));

        playerText.setText(playerString[player]);
        playerText.setStyle("-fx-font-size: 2em");
        playerText.setFill(Color.WHITE);
        playerText.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));

        VBox menuBox = new VBox();
        menuBox.getChildren().addAll(createMenu(), playerText);
        borderPane.setTop(menuBox);
        borderPane.setLeft(circleImage);
        borderPane.setBottom(movesText);

        // Create grid pane
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: black;" + "-fx-background-radius: 20");
        gridPane.setEffect(new DropShadow(25, Color.BLACK));
        gridPane.setHgap(7);
        gridPane.setVgap(7);

        // Add buttons to grid pane
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                button = new GameButton();
                String id = x + String.valueOf(y);
                button.setID(Integer.parseInt(id));
                button.setX(x);
                button.setY(y);
                button.setOnAction(myHandler);
                map.put(Integer.valueOf(id), button);
                gridPane.add(button, x, y);
            }
        }
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMaxSize(500, 470);
        borderPane.setCenter(gridPane);
        borderPane.setStyle("-fx-background-image: url('" + Objects.requireNonNull(getClass().getResource(themeMap.get(theme)[boardBackground])).toExternalForm() + "');"
                + "-fx-background-size: cover;"
                + "-fx-background-position: center center;");
        
        return new Scene(borderPane, WIDTH, HEIGHT);
    }
    // This scene is called when someone wins or the game
    // comes to tie and will let the user exit or play again
    // by clicking a button
    public Scene winScene() {
        // Create a borderpane to hold all the buttons and update
        // the background of the borderpane
        BorderPane winBorderPane = new BorderPane();
        winBorderPane.setStyle("-fx-background-image: url('" + Objects.requireNonNull(getClass().getResource(themeMap.get(theme)[winingBackground])).toExternalForm() + "');"
                + "-fx-background-repeat: no-repeat;"
                + "-fx-background-position: center center;");

        //  A message announcing who won the game or if there was a tie game
        Text message = new Text("");
        if (win) {
            message.setText("Player " + (player + 1) + " has won the game!");
        } else {
            message.setText("Tie game!");
        }
        message.setStyle("-fx-font-family: 'Roboto'; -fx-font-size: 40; ");
        message.setFill(Color.WHITE);
        message.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));
        StackPane stp = new StackPane();
        Rectangle rec = new Rectangle();
        rec.setHeight(60);
        rec.setWidth(520);
        rec.setArcWidth(30.0);
        rec.setArcHeight(20.0);
        stp.getChildren().addAll(rec, message);
        // An option to exit the program (could also be a button)
        Button exitButton = new Button("Exit Game");
        playButton.setStyle("-fx-font-size: 18px;" + "-fx-font-family: 'Roboto';" + "-fx-background-color: black;" + "-fx-text-fill: white;");
        exitButton.setStyle("-fx-font-size: 18px;" + "-fx-font-family: 'Roboto';" + "-fx-background-color: black;" + "-fx-text-fill: white;");
        // Event handler when the exit button gets clicked
        exitButton.setOnAction(e -> Platform.exit());
        // Horizontal box to hold both of the buttons
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(playButton, exitButton);
        buttonBox.setSpacing(5);
        // Vertical box to hold the message and the button box
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(stp, buttonBox);
        box.setSpacing(10);
        // Create the scene and put the box in center
        winBorderPane.setCenter(box);
        Scene scene = new Scene(winBorderPane, WIDTH, HEIGHT);
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Roboto");
        return scene;
    }

    // Method will check the left, right, diagonals, up, down win condition
    public boolean checkWin(int id) {
        // Check right and left of current button
        if (checkNeighbors(id, -10) + checkNeighbors(id + 10, 10) >= 4) {
            return true;
        }
        winningButtons.clear();
        // check diagonal to the right
        if (checkNeighbors(id, -9) + checkNeighbors(id + 9, 9) >= 4) {
            return true;
        }
        winningButtons.clear();
        // check diagonal to the left
        if (checkNeighbors(id, -11) + checkNeighbors(id + 11, 11) >= 4) {
            return true;
        }
        winningButtons.clear();
        // check up and down
        if (checkNeighbors(id, -1) + checkNeighbors(id + 1, 1) >= 4) {
            return true;
        }

        winningButtons.clear();
        // if there was no win condition met
        return false;
    }

    // Method will check the neighbors of the current button
    // to check if any nearby buttons are the same(win condition)
    public int checkNeighbors(int current, int step) {
        int counter = 0;
        while (map.containsKey(current)) { // check button is within the limits
            if (map.get(current).isClicked() && map.get(current).getPlayer() == player) { // check the button has been clicked by the current player
                counter++; // add one to the counter
                winningButtons.add(current);
            } else {
                break; // if the button wasn't clicked or was clicked by someone else, stop counting
            }

            if (counter == 4) {
                return counter; // return if it already connected 4
            }
            current = current + step; // continue moving to next button
        }
        return counter; // return how many were connected in the same direction, maybe fix to return the ids of the connected buttons too?
    }

    // Method is used when the menu button "reverse" is clicked and then the stack
    // pops off the last move
    public void reverseMove() {
        if (!stack.isEmpty()) {
            int reverse = stack.pop();
            map.get(reverse).clear();
            updatePlayer();
            movesText.setText("Player " + (player + 1) + " reversed moved.");
        }
    }


    // This function creates the menu buttons and
    // makes them have all the same design
    public static Menu createMenuBttn(String title, String color) {
        Menu menuBttn = new Menu("");
        Label menuLabel = new Label(title);
        menuLabel.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));
        menuLabel.setStyle("-fx-text-fill: " + color + ";");
        menuBttn.setGraphic(menuLabel);
        return menuBttn;
    }

    // This method will create the menu that is on top of the "Connect 4" board
    // in the game play scene
    public MenuBar createMenu() {
        String menuTextColor = "White";
        String menuBackgroundColor = "Black";

        // Main Menu Buttons
        Menu gamePlay = createMenuBttn("Game Play", menuTextColor);
        Menu themes = createMenuBttn("Themes", menuTextColor);
        Menu options = createMenuBttn("Options", menuTextColor);

        // Game play menu
        MenuItem reverse = new MenuItem("Reverse");
        reverse.setOnAction(e -> reverseMove());
        gamePlay.getItems().add(reverse);

        // Themes menu item buttons
        RadioMenuItem starWars = new RadioMenuItem("Star Wars");
        starWars.setSelected(true);
        starWars.setOnAction(e -> {
            theme = "StarWars";
            updateTheme();

        });
        RadioMenuItem rickMorty = new RadioMenuItem("Rick & Morty");
        rickMorty.setOnAction(e -> {
            theme = "RickMorty";
            updateTheme();
        });
        RadioMenuItem dragonBall = new RadioMenuItem("Dragon Ball Z");
        dragonBall.setOnAction(e -> {
            theme = "DragonBallZ";
            updateTheme();
        });

        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(starWars, rickMorty, dragonBall); // Themes must be added to toggle group to make them mutually exclusive
        themes.getItems().addAll(starWars, rickMorty, dragonBall);

        // Options menu
        MenuItem howToPlay = new MenuItem("How To Play");
        howToPlay.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("How To Play Connect Four");
            alert.setHeaderText(null);
            alert.setContentText("""
                    It is a two player game where each player takes a turn dropping a checker into a slot (one of the columns) on the game board.
                    That checker will fall down the column and either land on top of another checker or land on the bottom row.
                    To win the game, a player needs to get 4 of their checkers in a vertical, horizontal or diagonal row before the other player""");

            alert.showAndWait();
        });
        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(e -> resetGame());
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> Platform.exit());
        options.getItems().addAll(howToPlay, newGame, exit);

        // Add menus to menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(gamePlay, themes, options);
        menuBar.setStyle("-fx-background-color: " + menuBackgroundColor + ";" +
                "-fx-selection-bar: black;" +
                "-fx-font-size: 14pt;");
        return menuBar;
    }

    // This method updates the current player after each move
    public void updatePlayer() {
        if (player == 0) {
            player = 1;
        } else {
            player = 0;
        }
        playerText.setText(playerString[player]);
        circleImage.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResourceAsStream(themeMap.get(theme)[player])))));
    }

    // This method will make a map of all the resources needed for the themes
    // which will be needed when the themes button is clicked in the gameplay menu
    public void loadThemes() {
        themeMap.put("StarWars",
                new String[] {
                        "StarWarsPlayerOne.png", //player 1 image
                        "StarWarsPlayerTwo.png", // player 2 image
                        "StarWarsBackground.png", //background
                        "sfdistantgalaxy.ttf", // font 1
                        "StarWarsWiningBackground.jpg"
                }); // wining background
        themeMap.put("RickMorty",
                new String[] {
                        "Rick&MortyPlayerOne.jpg",
                        "Rick&MortyPlayerTwo.jpg",
                        "Rick&MortyBackground.png",
                        "get_schwifty.ttf", // front 1
                        "Rick&MortyWiningBackground.jpg"
                }); // wining background});
        themeMap.put("DragonBallZ",
                new String[] {
                        "DragonBallZPlayerOne.jpg",
                        "DragonBallZPlayerTwo.jpg",
                        "DragonBallZBackground.PNG",
                        "Saiyan-Sans.ttf", // front 1
                        "DragonBallZWiningBackground.png"
                }); // wining background});
    }

    // This method will update the theme it whatever the user clicks for a new theme
    public void updateTheme() {
        // The circle with the current player
        circleImage.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResourceAsStream(themeMap.get(theme)[player])))));
        // the background of the border
        borderPane.setStyle("-fx-background-image: url('" + Objects.requireNonNull(getClass().getResource(themeMap.get(theme)[boardBackground])).toExternalForm() + "');" +
                "-fx-background-size: stretch;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center center;");
        // Updates the theme in the clicked buttons
        map.forEach((k, v) -> {
            if (v.isClicked()) {
                v.updateTheme(new Image(Objects.requireNonNull(getClass().getResourceAsStream(themeMap.get(theme)[v.getPlayer()]))));
            }
        });
    }

    // This method will be called when either the new game button is clicked or after the game is
    // over and the user wants to play again. It will clear the map holding the buttons
    // and the stack containing the players moves
    public void resetGame() {
        // clear button map
        map.forEach((k, v) -> v.clear());
        // clear stack
        stack.clear();
        // clear winning buttons?
        winningButtons.clear();
        //set player back to player 1
        player = 0;
        playerText.setText(playerString[player]);
        circleImage.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResourceAsStream(themeMap.get(theme)[player])))));
        // clear moves text
        movesText.setText("");
    }
}
