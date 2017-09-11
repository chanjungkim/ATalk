package drawing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class MyCanvas extends Canvas {

	int x = -50;
	int y = -50, w = 7, h = 7;
	Color cr = Color.black;

	@Override
	public void paint(Graphics g) {
		g.setColor(cr);
		g.fillOval(x, y, w, h);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

}