package drawing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyDrawing extends JFrame {

	JPanel p1;
	PaintPanel p2;
	JButton btR, btG, btB, btOpen;
	PaintToolFrame pt;

	private ObjectOutputStream os;
	private ObjectInputStream is;

	public MyDrawing() {
		super("::MyDrawing::");
		try {
			Socket socket = new Socket(InetAddress.getByName("70.12.115.61"), 6666);

			os = new ObjectOutputStream(socket.getOutputStream());
			is = new ObjectInputStream(socket.getInputStream());

			new ListenThread().start();

			
			pt = new PaintToolFrame();
			p1 = new JPanel();
			add(p1, "North");
			p2 = new PaintPanel(os);

			add(p2, "Center");
			p2.setBackground(Color.white);

			btR = new JButton("RED");
			btR.setBackground(Color.red);
			p1.add(btR);
			btG = new JButton("GREEN");
			btG.setBackground(Color.green);
			p1.add(btG);
			btB = new JButton("BLUE");
			btB.setBackground(Color.blue);
			p1.add(btB);
			btOpen = new JButton("Paint Tool");
			p1.add(btOpen);

			MyHandler my = new MyHandler();
			btR.addActionListener(my);
			btG.addActionListener(my);
			btB.addActionListener(my);
			btOpen.addActionListener(my);

			pt.btPlus.addActionListener(my);
			pt.btMinus.addActionListener(my);
			pt.btClear.addActionListener(my);

			pt.btColor.addActionListener(my);
			pt.btClose.addActionListener(my);

			setSize(500, 500);
			setVisible(true);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ListenThread extends Thread {

		@Override
		public void run() {
			try {
				while (true) {
					PaintInfoVO recInfo = (PaintInfoVO) is.readObject();
					p2.paintInfoAdd(recInfo);
				}
			} catch (IOException e) {
				System.out.println("read exception 1");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("read exception 2");
				e.printStackTrace();
			}
		}
	}

	class MyHandler implements ActionListener {

		public void mouseMoved(MouseEvent e) {

		}

		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();

			if (o == btR) {
				p2.setCurrentColor(Color.RED);
			} else if (o == btG) {
				p2.setCurrentColor(Color.GREEN);
			} else if (o == btB) {
				p2.setCurrentColor(Color.BLUE);
			} else if (o == btOpen) {
				pt.pack();
				pt.setLocation(getWidth(), 0);
				pt.setVisible(true);
			} else if (o == pt.btPlus) {
				p2.plusCurrentWidthline();
			} else if (o == pt.btMinus) {
				p2.minusCurrentWidthline();

			} else if (o == pt.btClear) {
				p2.setCurrentColor(Color.white);
			} else if (o == pt.btColor) {
				Color selCr = JColorChooser.showDialog(null, "", Color.blue);
				p2.setCurrentColor(selCr);
			} else if (o == pt.btClose) {
				pt.dispose();
			}
		}

	}
}