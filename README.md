# Brick Breaker Game

The Brick Breaker Game project is a Java application that implements a classic arcade-style game where the player controls a paddle to bounce a ball and break bricks. This project consists of several classes that work together to create the game.

## Classes

1. **Ball**
   The `Ball` class represents the game's ball object. It handles movement, collision detection, and bouncing off walls and paddles.

2. **GamePanel**
   The `GamePanel` class is the core of the game. It manages the game loop, draws game elements on the screen, handles collisions, and updates the game state.

3. **Lives**
   The `Lives` class displays the player's remaining lives as red circles on the screen.

4. **Main**
   The `Main` class is the entry point of the game. It initializes the main game frame and starts the game loop.

5. **MyFrame**
   The `MyFrame` class creates the main game window, handles keyboard inputs, and provides a way to restart the game.

6. **Paddle**
   The `Paddle` class represents the player's controllable paddle. It moves horizontally and interacts with the ball.

7. **Player**
   The `Player` class is an alternative implementation of the player's paddle, with similar functionality to the `Paddle` class.

8. **Score**
   The `Score` class manages and displays the player's score on the screen.

## How to Play

1. Run the game by compiling and executing the `Main` class.
2. Control the paddle's movement using the left and right arrow keys.
3. Bounce the ball off the paddle to break the bricks.
4. Avoid letting the ball pass the paddle to prevent losing a life.
5. Aim to break all the bricks and complete the game while maintaining lives.

## License

This project is licensed under the MIT License - see the [LICENSE](link to license file) file for details.

