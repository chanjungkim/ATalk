package image;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//== PaintToolFrame.java ���� â���� PaintTool ��ư�� ���� �� ������ â ȭ�� ===========

public class PaintToolFrame extends JFrame {

	JButton btPlus, btMinus, btClear, btAllClear, btClose, btColor;
	JPanel p;

	public PaintToolFrame() {
		super("::PaintToolFrame::");
		Container c = getContentPane();

		p = new JPanel();
		c.add(p, "Center");
		p.add(btPlus = new JButton("ũ��"));
		p.add(btMinus = new JButton("�۰�"));
		p.add(btClear = new JButton("�����"));
		p.add(btAllClear = new JButton("��������"));
		p.add(btColor = new JButton("����"));
		p.add(btClose = new JButton("�ݱ�"));

	}
}