import java.awt.Canvas;

public class Game extends Canvas implements Runnable{

  public Game(){
    this.setPreferredSize(new Dimension(480,480));
  }

  public void tick(){

  }

  public void render(){
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null){
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    g.setColor(color.black);
    g.fillRect(0, 0, 480, 480);

    g.dispose();
    bs.show();
  }

  @Override
  public void run(){
    while(true){
      tick();
      render();
      Thread.sleep(1000/60);
    }
  }

}
