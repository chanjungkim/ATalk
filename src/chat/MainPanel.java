package chat;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.pink);
		
		g.drawRect(10, 10, 50, 50);
	}
}

