package SnakeGame;
import javax.swing.*;
import java.awt.*;

public class SnakePanel extends JPanel {
    Snake snake;

    public SnakePanel(Snake snake) {
        this.snake = snake;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Node node = null;
        for (int i = 0; i < snake.body.size(); i++) {// ---红蓝间隔画蛇身
            if (i % 2 == 0)
                g.setColor(Color.blue);
            else
                g.setColor(Color.yellow);
            node = snake.body.get(i);
            g.fillRect(node.x, node.y, node.H, node.W);
        }
        node = snake.food;
        g.setColor(Color.red);
        g.fillRect(node.x, node.y, node.H, node.W);
    }
}
