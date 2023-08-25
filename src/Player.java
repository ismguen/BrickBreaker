import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Player extends Rectangle{

    int xVelocity;
    int speed = 10;
    static final Random random = new Random();
    final int r = random.nextInt(255);
    final int g2 = random.nextInt(255);
    final int b = random.nextInt(255);

    Player(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                setXDirection(-speed);
                break;
            case KeyEvent.VK_D: {
                setXDirection(speed);
                break;
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                setXDirection(0);
                break;
            case KeyEvent.VK_D: {
                setXDirection(0);
                break;
            }
        }
    }
    public void setXDirection(int xDirection) {
        xVelocity = xDirection;
    }
    public void move() {
        x= x+ xVelocity;
    }
    public void draw(Graphics g) {


        g.setColor(Color.white);
        g.fillRect(x, y, width, height);

    }
}
