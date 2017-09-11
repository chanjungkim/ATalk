package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

public class PaintPanel extends JPanel {
	private List<PaintInfoVO> paintInfoList = new ArrayList<>();
	private Color currentColor;
	private int currentWidthline = 5;

	private ObjectOutputStream os;

	public PaintPanel(ObjectOutputStream os) {
		super();
		this.os = os;

		setFocusable(true);
		requestFocus();
		addMouseMotionListener(new PaintListener());
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public void plusCurrentWidthline() {
		this.currentWidthline += 5;
	}

	public void minusCurrentWidthline() {
		if (this.currentWidthline >= 5) {

		} else {
			this.currentWidthline -= 5;
		}
	}

	public void paintInfoAdd(PaintInfoVO info) {
		paintInfoList.add(info);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		Iterator<PaintInfoVO> iter = paintInfoList.iterator();

		while (iter.hasNext()) {
			PaintInfoVO info = iter.next();

			g2.setColor(info.getDrawColor());
			g2.fillOval((int) info.getDrawX(), (int) info.getDrawY(), info.getWidthLine(), info.getWidthLine());
		}

	}

	@Override
	public Insets getInsets() {
		return new Insets(40, 10, 10, 10);
	}

	class PaintListener implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent e) {
			double xx = e.getPoint().getX();
			double yy = e.getY();

			PaintInfoVO nowInfo = new PaintInfoVO(currentColor, xx, yy, currentWidthline);
			paintInfoAdd(nowInfo);

			System.out.println("client drag:" + xx + "," + yy + "," + nowInfo.getWidthLine());
			try {
				os.writeObject(nowInfo);
			} catch (IOException e1) {
				System.out.println("write exception");
				e1.printStackTrace();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}

}