package gameTemplate;

import java.awt.*;

public class GameObject
{
	float x,y,dx,dy;
	int w,h;
	public GameObject()
	{
		
	}
	public boolean hits(GameObject other)
	{
		if(this.getBounds().intersects(other.getBounds()))
		{
			return true;
		}
		return false;
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, w, h);
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect((int)x,(int)y,w,h);
	}
}
