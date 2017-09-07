package image;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintToolFrame extends JFrame {

	JButton btPlus, btMinus, btClear, btClose, btColor;
	JPanel p;

	public PaintToolFrame() {
		super("::PaintToolFrame::");
		Container c = getContentPane();

		p = new JPanel();
		c.add(p, "Center");
		p.add(btPlus = new JButton("plus"));
		p.add(btMinus = new JButton("minus"));
		p.add(btClear = new JButton("clear"));
		p.add(btColor = new JButton("color"));
		p.add(btClose = new JButton("close"));

	}
}