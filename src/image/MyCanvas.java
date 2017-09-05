package image;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
//== MyCanvas.java 그림을 그릴수 있는 나만의 도화지 클래스 ======

public class MyCanvas extends Canvas {

	// 처음에 까만색 점 안찍히게 하기 위해서 x,y -값 지정
	int x = -50;
	int y = -50, w = 7, h = 7;
	Color cr = Color.black;

	@Override
	public void paint(Graphics g) {
		g.setColor(cr);
		g.fillOval(x, y, w, h); // x, y 지점에 70,70 크기의 원 그리기
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

}
