import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ball extends Rectangle{

    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 2;
    boolean id;

    Ball(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();
        id = random.nextBoolean();
        setXDirection(0);
        int randomYDirection = 1;
        setYDirection(randomYDirection*initialSpeed);
    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }
    public void move() {
        x += xVelocity;
        y += yVelocity;
        id = random.nextBoolean();
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }

}
