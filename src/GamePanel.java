import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 650;
    static final int GAME_HEIGHT = 800;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 100;
    static final int PADDLE_HEIGHT = 25;
    ArrayList<Paddle> bricks;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Player paddle1;
    Ball ball;
    ArrayList<Lives> lives;
    boolean gameover;
    boolean gameover2;
    static int l = 0;
    Score score;

    GamePanel(){
        bricks = new ArrayList<Paddle>();
        lives = new ArrayList<Lives>();

        for(int i = 1; i<=3; i++){
            lives.add(new Lives(i*50,GAME_HEIGHT-50,25,25));
        }
        for(int i = 0; i< lives.size(); i++){
            System.out.println(lives.get(i));
        }
        for(int i = 1; i<=5; i++){
            for(int j = 1; j<=5; j++) {
                bricks.add(new Paddle(j * 100, i * 25, 95, 20));
            }
        }
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLACK);


        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_WIDTH/2-BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);
    }
    public void newPaddles() {
        paddle1 = new Player(GAME_WIDTH/2,GAME_HEIGHT-50,PADDLE_WIDTH+100,PADDLE_HEIGHT);

    }
    public void paint(Graphics g) {
        super.paint(g);
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

        if(gameover) {
            g.setColor(Color.green);
            g.setFont(new Font("Arial", Font.BOLD, 70));
            g.drawString("YOU WIN", GAME_WIDTH / 2 - 180, GAME_HEIGHT / 2);
        }
        if(gameover2) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 70));
            g.drawString("GAME OVER", GAME_WIDTH / 2 - 180, GAME_HEIGHT / 2);
        }


    }
    public void draw(Graphics g) {
        paddle1.draw(g);
        for(int i = 0; i<bricks.size(); i++){
            bricks.get(i).draw(g);
        }
        for(int j = 0; j<lives.size(); j++){
            lives.get(j).draw(g);
        }
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync(); // I forgot to add this line of code in the video, it helps with the animation

    }
    public void move() {
        paddle1.move();
        ball.move();
    }
    public void checkCollision() {
        if (paddle1.x <= 0)
            paddle1.x = 0;
        if (paddle1.x >= (GAME_WIDTH - PADDLE_WIDTH-100))
            paddle1.x = GAME_WIDTH - PADDLE_WIDTH-100;

        //bounce ball off top & bottom window edges
        if (ball.x <= 0) {
            ball.setXDirection(-ball.xVelocity);
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            ball.setXDirection(-ball.xVelocity);
        }
        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT- BALL_DIAMETER) {
            newBall();
            System.out.println(l);
            System.out.println(lives.remove(0));
        }
        //bounce ball off paddles
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if (ball.yVelocity > 0 && ball.yVelocity < 6)
                ball.yVelocity++; //optional for more difficulty
            else
                ball.yVelocity--;
            if (ball.id) {
                ball.setXDirection(-ball.xVelocity);
                ball.setYDirection(-ball.yVelocity);
            } else {
                ball.setXDirection(ball.xVelocity);
                ball.setYDirection(-ball.yVelocity);
            }
        }

        if(bricks.isEmpty()){
            gameover = true;
        }
        if(lives.isEmpty()) {
            gameover2 = true;
        }
        for (int i = 0; i < bricks.size(); i++) {
            if (ball.intersects(bricks.get(i))) {
                Random random = new Random();
                int rad = random.nextInt(500);
                ball.xVelocity = Math.abs(ball.xVelocity);
                ball.xVelocity++; //optional for more difficulty
                if (ball.yVelocity > 0 && ball.yVelocity < 6)
                    ball.yVelocity++; //optional for more difficulty
                else
                    ball.yVelocity--;
                if (ball.id) {
                    ball.setXDirection(-ball.xVelocity);
                    ball.setYDirection(-ball.yVelocity);
                    bricks.remove(i);
                } else {
                    ball.setXDirection(ball.xVelocity);
                    ball.setYDirection(-ball.yVelocity);
                    bricks.remove(i);
                }
            }
        }
    }


    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(!gameover && !gameover2) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
        setFocusable(false);
        repaint();
    }
}