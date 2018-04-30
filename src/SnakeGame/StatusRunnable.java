package SnakeGame;
import javax.swing.*;

public class StatusRunnable implements Runnable {
    private JLabel scoreLabel;
    private JLabel statusLabel;
    private Snake snake;

    public StatusRunnable(Snake snake, JLabel statusLabel, JLabel scoreLabel) {
        this.statusLabel = statusLabel;
        this.scoreLabel = scoreLabel;
        this.snake = snake;
    }

    public void run() {
        String sta = "";

        while (true) {

            switch (snake.status) {
                case Snake.RUNNING:
                    sta = "Running";
                    break;
                case Snake.PAUSED:
                    sta = "Paused";
                    break;
                case Snake.GAMEOVER:
                    sta = "GameOver";
                    break;
            }
            statusLabel.setText(sta);
            scoreLabel.setText("" + snake.score);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
