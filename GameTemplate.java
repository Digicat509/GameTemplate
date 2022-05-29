package gameTemplate;
import java.awt.*;//Canvas, Dimension
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.JFrame;//JFrame
import java.awt.image.BufferStrategy;
import javax.imageio.*;

public class GameTemplate extends Canvas implements Runnable
{
	static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	private boolean playing = true;
	static Handler hand = new Handler();
	Keyboard kevin = new Keyboard();
	public GameTemplate()//Constructor
	{
		new Window(WIDTH, HEIGHT, "Game", this);
		addKeyListener(kevin);
		this.requestFocus();
	}
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running =true;
	}
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void run()
	{
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 /amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames =0;
		while(running)
		{
			try {Thread.sleep(5);}catch(Exception e) {}
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			while(delta>=1)
			{
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer+=1000;
				System.out.println("FPS: "+frames);
				frames = 0;
			}


			}
			stop();

	}
	public void render()
	{
		kevin.update();
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);
		if(playing)
			hand.render(g);
		if(Keyboard.esc)
		{
			System.exit(0);
		}
		g.dispose();
		bs.show();
	}
	public void tick()
	{
		
	}
	public static void main(String args[])
	{
		new GameTemplate();
	}
}
class Window extends Canvas
{
	public Window(int w, int h, String t, GameTemplate game)
	{
		JFrame frame = new JFrame(t);
		frame.setPreferredSize(new Dimension(w,h));
		frame.setMinimumSize(new Dimension(w,h));
		frame.setMaximumSize(new Dimension(w,h));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setVisible(true);
		game.start();

	}
}
class Keyboard implements KeyListener
{
    private boolean[] keys = new boolean[120];
    public static  boolean up,down,left,right, /*space,*/ esc, w, s;

    public void update()
    {
        up = keys[KeyEvent.VK_UP];
        w = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN];
        s = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT]|| keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT]|| keys[KeyEvent.VK_D];
    //    space = keys[KeyEvent.VK_SPACE];
        esc = keys[KeyEvent.VK_ESCAPE];


    }
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }
    public void keyTyped(KeyEvent e)
    {

    }

}