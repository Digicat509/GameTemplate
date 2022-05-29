package gameTemplate;

import java.awt.Graphics;

import java.util.*;

public class Handler{
	static ArrayList <GameObject> hand = new ArrayList <GameObject>();
	// add the objects as static variables here
	public Handler()
	{
		// add the objects to the list
	}
	// for if you add a menu
	public void startGame()
	{
		// same thing as a constructor but used if you make a menu
	}
	public void render(Graphics g)
	{
		for(int i = 0; i < hand.size(); i++)
		{
			GameObject o = hand.get(i);
			o.draw(g);
		}
	}
}
