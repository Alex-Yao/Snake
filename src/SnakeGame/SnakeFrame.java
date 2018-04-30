package SnakeGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeFrame extends JFrame {
    private JLabel statusLabel;
    private JLabel speedLabel;
    private JLabel scoreLabel;
    private JPanel snakePanel;
    private Snake snake;
    private JMenuBar bar;
    JMenu gameMenu;
    JMenu helpMenu;
    JMenu speedMenu;
    JMenuItem newItem;
    JMenuItem pauseItem;
    JMenuItem beginItem;
    JMenuItem helpItem;
    JMenuItem aboutItem;
    JMenuItem slowItem;
    JMenuItem midItem;
    JMenuItem fastItem;

    public SnakeFrame() {
        init();
        ActionListener l = e -> {
            if (e.getSource() == pauseItem)
                snake.isRun = false;
            if (e.getSource() == beginItem)
                snake.isRun = true;
            if (e.getSource() == newItem) {
                newGame();
            }
            // ------------菜单控制运行速度
            if (e.getSource() == slowItem) {
                snake.speed = Snake.SLOW;
                speedLabel.setText("Slow");
            }
            if (e.getSource() == midItem) {
                snake.speed = Snake.MID;
                speedLabel.setText("Mid");
            }
            if (e.getSource() == fastItem) {
                snake.speed = Snake.FAST;
                speedLabel.setText("Fast");
            }
        };
        pauseItem.addActionListener(l);
        beginItem.addActionListener(l);
        newItem.addActionListener(l);
        aboutItem.addActionListener(l);
        slowItem.addActionListener(l);
        midItem.addActionListener(l);
        fastItem.addActionListener(l);
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    // ------------方向键改变蛇运行方向
                    case KeyEvent.VK_DOWN:
                        snake.changeDerection(Snake.DOWN);
                        break;
                    case KeyEvent.VK_UP:
                        snake.changeDerection(Snake.UP);
                        break;
                    case KeyEvent.VK_LEFT:
                        snake.changeDerection(Snake.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.changeDerection(Snake.RIGHT);
                        break;
                    // 空格键，游戏暂停或继续
                    case KeyEvent.VK_SPACE:
                        if (snake.isRun == true) {
                            snake.isRun = false;
                            snake.status = Snake.PAUSED;
                            break;
                        }
                        if (snake.isRun == false) {
                            snake.isRun = true;
                            snake.status = Snake.RUNNING;
                            break;
                        }
                }
            }

            public void keyReleased(KeyEvent k) {
            }
            public void keyTyped(KeyEvent k) {
            }
        });
    }

    private void init() {
        speedLabel = new JLabel();
        snake = new Snake();
        setSize(400, 460);
        setLayout(null);
        this.setResizable(false);
        bar = new JMenuBar();
        gameMenu = new JMenu("Game");
        newItem = new JMenuItem("New Game");
        gameMenu.add(newItem);
        pauseItem = new JMenuItem("Pause");
        gameMenu.add(pauseItem);
        beginItem = new JMenuItem("Continue");
        gameMenu.add(beginItem);
        helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        speedMenu = new JMenu("Speed");
        slowItem = new JMenuItem("Slow");
        fastItem = new JMenuItem("Fast");
        midItem = new JMenuItem("Middle");
        speedMenu.add(slowItem);
        speedMenu.add(midItem);
        speedMenu.add(fastItem);
        bar.add(gameMenu);
        bar.add(helpMenu);
        bar.add(speedMenu);
        setJMenuBar(bar);
        statusLabel = new JLabel();
        scoreLabel = new JLabel();
        snakePanel = new JPanel();
        snakePanel.setBounds(0, 0, 300, 400);
        snakePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        add(snakePanel);
        statusLabel.setBounds(300, 25, 60, 20);
        add(statusLabel);
        scoreLabel.setBounds(300, 20, 60, 20);
        add(scoreLabel);
        JLabel temp = new JLabel("状态");
        temp.setBounds(310, 5, 60, 20);
        add(temp);
        temp = new JLabel("分数");
        temp.setBounds(310, 105, 60, 20);
        add(temp);
        temp = new JLabel("速度");
        temp.setBounds(310, 55, 60, 20);
        add(temp);
        speedLabel.setBounds(310, 75, 60, 20);
        add(speedLabel);
    }

    private void newGame() {
        this.remove(snakePanel);
        this.remove(statusLabel);
        this.remove(scoreLabel);
        speedLabel.setText("Slow");
        statusLabel = new JLabel();
        scoreLabel = new JLabel();
        snakePanel = new JPanel();
        snake = new Snake();
        snakePanel = new SnakePanel(snake);
        snakePanel.setBounds(0, 0, 300, 400);
        snakePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        Runnable r1 = new SnakeRunnable(snake, snakePanel);
        Runnable r2 = new StatusRunnable(snake, statusLabel, scoreLabel);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        add(snakePanel);
        statusLabel.setBounds(310, 25, 60, 20);
        add(statusLabel);
        scoreLabel.setBounds(310, 125, 60, 20);
        add(scoreLabel);
    }
}
