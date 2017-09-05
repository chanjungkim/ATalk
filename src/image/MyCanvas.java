package image;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
//== MyCanvas.java �׸��� �׸��� �ִ� ������ ��ȭ�� Ŭ���� ======

public class MyCanvas extends Canvas {

	// ó���� ��� �� �������� �ϱ� ���ؼ� x,y -�� ����
	int x = -50;
	int y = -50, w = 7, h = 7;
	Color cr = Color.black;

	@Override
	public void paint(Graphics g) {
		g.setColor(cr);
		g.fillOval(x, y, w, h); // x, y ������ 70,70 ũ���� �� �׸���
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

}
