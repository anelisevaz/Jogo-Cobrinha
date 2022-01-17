import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

import javafx.scene.shape.Rectangle;

public class Game extends Canvas implements Runnable,KeyListener{

  public Node[] nodeSnake = new Node[30];

  public boolean left,right,up,down;

  public int score = 0;

  public int macaX = 0, macaY = 0;

  public int spd = 1;

  public Game(){
    this.setPreferredSize(new Dimension(160,160));
    for(int i = 0; i < nodeSnake.length; i++){
      nodeSnake[i] = new Node(0,0);
    }
    this.addKeyListener(this);
  }

  public void tick(){

    for(int i = nodeSnake.length - 1; i > 0; i--){
      nodeSnake[i].x = nodeSnake[i-1].x;
      nodeSnake[i].y = nodeSnake[i-1].y;
    }


    if(right){
      nodeSnake[0].x+=spd;
    }else if(up){
      nodeSnake[0].y-=spd; 
    }else if(down){
      nodeSnake[0].y+=spd; 
    }else if(left){
      nodeSnake[0].x-=spd; 
    }

    if(new Rectangle(nodeSnake[0].x,nodeSnake[0].y,10,10).intersects(new Rectangle(macaX,macaY,10,10)))
    macaX = new Random().nextInt(160-10);
    macaY = new Random().nextInt(160-10);
    score++;
    spd++;
    System.out.println("Pontos: " + score);
  }

  public void render(){
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null){
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.black);
    g.fillRect(0, 0, 160, 160);
    for(int i = 0; i < nodeSnake.length; i++){
      g.setColor(Color.blue);
      g.fillRect(nodeSnake[i].x, nodeSnake[i].y,10,10);
    }

    g.setColor(Color.red);
    g.fillRect(macaX, macaY, 10,10);

    g.dispose();
    bs.show();
  
  }

public static void main(String[] args) {
  Game game = new Game();
  JFrame frame = new JFrame("Snake"); 
  frame.add(game);
  frame.setResizable(false);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  frame.pack();
  frame.setLocationRelativeTo(null);
  frame.setVisible(true);
  new Thread(game).start();
}


  @Override
  public void run() {
    while(true){
      tick();
      render();
      Thread.sleep(1000/60);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
    
  }
  


@Override
public void keyPressed(keyEvent e){
  if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
    right = true;
    up = false;
    right = false;
    down = false;
  }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
    left = true;
    up = false;
    right = false;
    down = false;
  }else if(e.getKeyCode() == KeyEvent.VK_UP){
    up = true;
    left = false;
    right = false;
    down = false;
}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
   down = true;
   left = false;
   right = false;
   up = false;
}

}

@Override
public void keyTyped(KeyEvent e) {
  // TODO Auto-generated method stub
  
}

@Override
public void keyPressed(KeyEvent e) {
  // TODO Auto-generated method stub
  
}

@Override
public void keyReleased(KeyEvent e) {
  // TODO Auto-generated method stub
  
}
