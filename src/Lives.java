import java.awt.*;

public class Lives extends Rectangle {
    int x;
    int y;
    int liveHeight;
    int liveWidht;

    public Lives(int x, int y, int liveWidht, int liveHeight){
        this.x = x;
        this.y = y;
        this.liveWidht = liveWidht;
        this.liveHeight = liveHeight;
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillOval(x,y,liveWidht,liveHeight);
    }
}
