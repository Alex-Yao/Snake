package SnakeGame;
import java.awt.*;

public class SnakeRunnable implements Runnable {
    private Snake snake;
    private Component component;

    public SnakeRunnable(Snake snake, Component component) {
        this.snake = snake;
        this.component = component;
    }

    public void run() {
        while (true) {
            try {
                snake.move();
                component.repaint();
                Thread.sleep(snake.speed);
            } catch (Exception e) {
            }
        }
    }
}
