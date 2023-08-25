import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame {

    GamePanel game;


    public MyFrame() {


        game = new GamePanel();
        this.add(game);
        this.setTitle("Brick Breaker");

        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.addKeyListener(new AL());


    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            game.paddle1.keyPressed(e);
            if(e.getKeyCode() == KeyEvent.VK_R){
                if(game.gameover2 || game.gameover){
                restart();
                }
            }
        }
        public void keyReleased(KeyEvent e) {
            game.paddle1.keyReleased(e);
        }
    }
    public void restart(){
                this.remove(game);
                game = new GamePanel();
                this.add(game);
                SwingUtilities.updateComponentTreeUI(this);
        }


}
