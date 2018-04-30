package SnakeGame;
import java.util.ArrayList;

public class Snake {
    boolean isRun;// ---------是否运动中
    ArrayList<Node> body;// -----蛇体
    Node food;// --------食物
    int derection;// --------方向
    int score;
    int status;
    int speed;
    public static final int SLOW = 500;
    public static final int MID = 300;
    public static final int FAST = 100;
    public static final int RUNNING = 1;
    public static final int PAUSED = 2;
    public static final int GAMEOVER = 3;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int RIGHT = 3;
    public static final int DOWN = 4;

    public Snake() {
        speed = Snake.SLOW;
        score = 0;
        isRun = false;
        status = Snake.PAUSED;
        derection = Snake.RIGHT;
        body = new ArrayList<Node>();
        body.add(new Node(60, 20));
        body.add(new Node(40, 20));
        body.add(new Node(20, 20));
        makeFood();
    }

    // ------------判断食物是否被蛇吃掉
    // -------如果食物在蛇运行方向的正前方，并且与蛇头接触，则被吃掉
    private boolean isEaten() {
        Node head = body.get(0);
        if (derection == Snake.RIGHT && (head.x + Node.W) == food.x
                && head.y == food.y)
            return true;
        if (derection == Snake.LEFT && (head.x - Node.W) == food.x
                && head.y == food.y)
            return true;
        if (derection == Snake.UP && head.x == food.x
                && (head.y - Node.H) == food.y)
            return true;
        if (derection == Snake.DOWN && head.x == food.x
                && (head.y + Node.H) == food.y)
            return true;
        else
            return false;
    }

    // ----------是否碰撞
    private boolean isCollsion() {
        Node node = body.get(0);
        // ------------碰壁
        if (derection == Snake.RIGHT && node.x == 280)
            return true;
        if (derection == Snake.UP && node.y == 0)
            return true;
        if (derection == Snake.LEFT && node.x == 0)
            return true;
        if (derection == Snake.DOWN && node.y == 380)
            return true;
        // --------------蛇头碰到蛇身
        Node temp = null;
        int i = 0;
        for (i = 3; i < body.size(); i++) {
            temp = body.get(i);
            if (temp.x == node.x && temp.y == node.y)
                break;
        }
        if (i < body.size())
            return true;
        else
            return false;
    }

    // -------在随机的地方产生食物
    public void makeFood() {
        Node node = new Node(0, 0);
        boolean isInBody = true;
        int x = 0, y = 0;
        int X = 0, Y = 0;
        int i = 0;
        while (isInBody) {
            x = (int) (Math.random() * 15);
            y = (int) (Math.random() * 20);
            X = x * Node.W;
            Y = y * Node.H;
            for (i = 0; i < body.size(); i++) {
                if (X == body.get(i).x && Y == body.get(i).y)
                    break;
            }
            if (i < body.size())
                isInBody = true;
            else
                isInBody = false;
        }
        food = new Node(X, Y);
    }

    // ---------改变运行方向
    public void changeDerection(int newDer) {
        if (derection % 2 != newDer % 2)// -------如果与原来方向相同或相反，则无法改变
            derection = newDer;
    }

    public void move() {
        if (isEaten()) {// -----如果食物被吃掉
            body.add(0, food);// --------把食物当成蛇头成为新的蛇体
            score += 10;
            makeFood();// --------产生食物
        } else if (isCollsion())// ---------如果碰壁或自身
        {
            isRun = false;
            status = Snake.GAMEOVER;// -----结束
        } else if (isRun) {// ----正常运行（不吃食物，不碰壁，不碰自身）
            Node node = body.get(0);
            int X = node.x;
            int Y = node.y;
            // ------------蛇头按运行方向前进一个单位
            switch (derection) {
                case 1:
                    X -= Node.W;
                    break;
                case 2:
                    Y -= Node.H;
                    break;
                case 3:
                    X += Node.W;
                    break;
                case 4:
                    Y += Node.H;
                    break;
            }
            body.add(0, new Node(X, Y));
            // ---------------去掉蛇尾
            body.remove(body.size() - 1);
        }
    }
}
