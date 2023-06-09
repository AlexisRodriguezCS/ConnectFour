<a name="readme-top"></a>

<p align="center">
  <img src="https://raw.githubusercontent.com/AlexisRodriguezCS/ConnectFour/main/Images/ConnectFour.jpg" alt="Grid" style="display:block;margin:auto;" height="500">
</p>
<h1 align="center">Connect Four</h1>

<!-- TABLE OF CONTENTS -->
<p align="center">
  <a href="#about">About The Project</a> •
  <a href="#getting-started">Getting Started</a> •
  <a href="#usage">Usage</a> •
  <a href="#contact">Contact</a>
</p>

<!-- ABOUT THE PROJECT -->
<a name="about"></a>
## About The Project

Welcome to the Connect Four game project, a fun and exciting endeavor that challenges you to create a fully functional two-player game using JavaFX GUI. In this game, players take turns dropping checkers into a slot on a 7x6 game board, with the ultimate goal of getting four checkers in a row either vertically, horizontally, or diagonally.

The game board is represented by a GridPane, a JavaFX layout container that arranges nodes in a grid-like pattern. The checkers are represented by GameButton instances, which extend the Button class. Players make moves by clicking on a GameButton on the GridPane, which then updates the game board and displays the checkers accordingly.

The game's GUI includes a welcome screen and a gameplay screen. The welcome screen displays a brief description of the game and a "Start Game" button, which takes the player to the gameplay screen. The gameplay screen consists of a menu bar, a game board, an area displaying whose turn it is, and a history of moves made.

The menu bar has three menus: Game Play, Themes, and Options. The Game Play menu provides options for the players to control the game. For instance, there is a "reverse move" option to undo the last move, and players can also choose to start a new game or exit the game. The Themes menu provides the player with the option to choose from three different themes, such as Star Wars, Dragon Ball Z, and Rick & Morty. The Options menu provides additional settings for how to play, initiate a new game session, and exit the application.

The implementation of this game shows a comprehensive understanding of JavaFX, event handling, and game logic. Additionally, proper error handling, input validation, and edge cases are considered in the implementation to ensure a smooth and enjoyable gaming experience.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
<a name="getting-started"></a>
## Getting Started

To set up a project locally, follow these simple steps.

### Prerequisites

_Software used to run the program._
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)

* [Git](https://git-scm.com/)

### Installation
_Here's how to install and set up the program._

From your command line:

```bash
# Clone this repository
$ git clone https://github.com/AlexisRodriguezCS/ConnectFour.git

# Go into the repository
$ Click File
$ Open
$ Select ConnectFour Folder

# Run the program
$ Right click on Main and select Run 'Main.main()' 
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE -->
<a name="usage"></a>
## Usage

> Home Screen

The game's home screen, featuring the Star Wars theme by default, includes two buttons that allow the player to start the game and exit the application.

![Home Screen](https://raw.githubusercontent.com/AlexisRodriguezCS/ConnectFour/main/Images/HomeScreen.png "Home Screen")

> Gameplay Screen

The gameplay screen features the game board where players drop checkers into slots and strive to get four in a row, along with an indicator in the top left corner displaying whose turn it is. Additionally, the bottom left corner displays the previous move made. The menu bar, situated at the top of the screen, includes three menus: Game Play, Themes, and Options.

![Game Screen](https://raw.githubusercontent.com/AlexisRodriguezCS/ConnectFour/main/Images/GridScreenSW.png "Game Screen")

> Menu

The Game Play menu, accessible from the menu bar, allows players to control the game. One of the options available is the "reverse move" feature, which allows the player to undo the last move made in the game.

![Reverse](https://raw.githubusercontent.com/AlexisRodriguezCS/ConnectFour/main/Images/Reverse.PNG "Reverse")

The Theme menu provides the player with three different options to change the look and feel of the game, including Star Wars, Rick & Morty, and Dragon Ball Z. The default theme is Star Wars, but players can switch to a different theme at any time. When a new theme is selected, the button icons, background, and player's icon will change to match the new theme.

> Themes

![Theme](https://raw.githubusercontent.com/AlexisRodriguezCS/ConnectFour/main/Images/Themes.PNG "Theme")

Below is the Rick & Morty theme, featuring button icons, background, and player's icon inspired by the popular animated series.

![Rick & Morty](https://raw.githubusercontent.com/AlexisRodriguezCS/ConnectFour/main/Images/GridScreenRM.png "Rick & Morty")

Below is the Dragon Ball Z theme, featuring button icons, background, and player's icon inspired by the iconic anime and manga franchise.

![Dragon Ball Z](https://raw.githubusercontent.com/AlexisRodriguezCS/ConnectFour/main/Images/GridScreenDBZ.png "Dragon Ball Z")

Below is the Star Wars theme, which serves as the default theme for the game, featuring button icons, background, and player's icon inspired by the legendary space opera.

![Star Wars](https://raw.githubusercontent.com/AlexisRodriguezCS/ConnectFour/main/Images/GridScreenSW.png "Star Wars")

> Options

The "Options" menu provides additional settings for gameplay, including "How to play" which displays an alert text explaining how to play the game, "New game" which restarts the game, and "Exit" which quits the game application.

![Options](https://raw.githubusercontent.com/AlexisRodriguezCS/ConnectFour/main/Images/Options.PNG "Options")

> Play

To make a move in Connect Four, simply click on a valid spot on the game board during your turn to drop your checker into the corresponding slot.

![Play](https://raw.githubusercontent.com/AlexisRodriguezCS/ConnectFour/main/Images/GridScreenSW.png "Play")

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
<a name="contact"></a>
## Contact

Alexis Rodriguez - [LinkedIn](https://www.linkedin.com/in/alexisrodriguezcs/) - alexisrodriguezdev@gmail.com

Project Link: [https://github.com/AlexisRodriguezCS/ConnectFour](https://github.com/AlexisRodriguezCS/ConnectFour)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
