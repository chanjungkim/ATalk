package image;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class MyDrawing extends JFrame {

	JPanel p1, p2;
	JButton btR, btG, btB, btOpen;
	Canvas can; // �θ�Ÿ��
	PaintToolFrame pt;

	private BufferedReader br;
	private BufferedWriter bw;

	public MyDrawing() {
		super("::MyDrawing::");
		try {
			Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5555);

			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw.flush();

			new ListenThread().start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		pt = new PaintToolFrame();
		p1 = new JPanel();
		add(p1, "North");
		p2 = new JPanel() { // �����ֱ�
			public Insets getInsets() {
				return new Insets(40, 10, 10, 10);
			}
		};
		add(p2, "Center");
		p2.setBackground(Color.lightGray);

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

		can = new MyCanvas(); // ��ȭ�� ������ �ϴ� ������Ʈ MyCanvas�� can�� ��� �޴� �ڽ�->���� ���ڱ� ����
		can.setSize(300, 300); // ��ȭ�� ũ��
		can.setBackground(Color.white); // ��ȭ�� ���� �ֱ�
		p2.add(can);

		// ������ ���� -------------------
		MyHandler my = new MyHandler();
		can.addMouseMotionListener(my); // ĵ���� ��ü�� ���콺��Ǹ����ʸ� �����Ѵ�.
		btR.addActionListener(my);
		btG.addActionListener(my);
		btB.addActionListener(my);
		btOpen.addActionListener(my);

		// pt��ư(PaintToolFrame Ŭ������)���� �����ʸ� ��������
		pt.btPlus.addActionListener(my);
		pt.btMinus.addActionListener(my);
		pt.btClear.addActionListener(my);
		pt.btAllClear.addActionListener(my);
		pt.btColor.addActionListener(my);
		pt.btClose.addActionListener(my);

		// �̹����� ���ο��� ���ϰ� �����ڿ��� �Ѵ�
		setSize(500, 500);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
/*
	class ChattingListener implements ActionListener, MouseMotionListener {
		int xx;
		int yy;

		public void mouseDragged(MouseEvent e) {
			try {
				setTitle("Drag");
				// ���콺�� �巡���� ������ x��ǥ,y��ǥ�� ���ͼ� can�� x,y ��ǥ���� �����Ѵ�.

				setTitle("xx=" + xx + ", yy" + yy);
				((MyCanvas) can).x = e.getX();
				((MyCanvas) can).y = e.getY();
				bw.write(((MyCanvas) can).x);
				bw.write(((MyCanvas) can).y);
				bw.flush();
				can.repaint();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// paint()�� JVM�� ȣ�����ִ� �޼ҵ����� ����x, repaint�� �Ἥ ��������
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}
*/
	// �����κ��� �޼����� �޴� ���� ������ Ŭ����
	class ListenThread extends Thread {

		@Override
		public void run() {
			try {
				while (true) {
					//int receiveX = br.read();
					//System.out.println(receiveX);
					//int receiveY = br.read();
					//System.out.println(receiveY);
					char[] r = new char [2];
					br.read(r);
					
					//setTitle("xx=" + receiveX + ", yy" + receiveY);
					((MyCanvas) can).x =r[0];
					((MyCanvas) can).y = r[1];

					// paint()�� JVM�� ȣ�����ִ� �޼ҵ����� ����x, repaint�� �Ἥ ��������
					can.repaint();
					// String receiveMsg = br.readLine();
					// chatArea.append(receiveMsg + "\n");
					// chatArea.setCaretPosition(chatArea.getText().length());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class MyHandler implements MouseMotionListener, ActionListener {

		public void mouseDragged(MouseEvent e) {
			try {
				setTitle("Drag");
				// ���콺�� �巡���� ������ x��ǥ,y��ǥ�� ���ͼ� can�� x,y ��ǥ���� �����Ѵ�.
				int xx = e.getX();
				int yy = e.getY();
				setTitle("xx=" + xx + ", yy" + yy);
				((MyCanvas) can).x = xx;
				((MyCanvas) can).y = yy;
				bw.write(((MyCanvas) can).x);
				bw.write(((MyCanvas) can).y);
				//bw.write(e.getX());
				//bw.write(e.getY());
				bw.flush();
				can.repaint();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		public void mouseMoved(MouseEvent e) {
		}

		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			MyCanvas can2 = (MyCanvas) can;

			if (o == btR) {
				can2.cr = Color.red;
			} else if (o == btG) {
				can2.cr = Color.GREEN;
			} else if (o == btB) {
				can2.cr = Color.blue;
			} else if (o == btOpen) {
				// ���ο� 'paintToolJframe' �����ؼ� â ����
				// PaintToolFrame pt = new PaintToolFrame(); ���⼭ �ڵ� ������ �Ź� ������ ���� ��
				// pt.setSize(400, 400);
				pt.pack(); // ũ�⸦ �����ؼ� ������
				pt.setLocation(getWidth(), 0); // x�ุŭ ���������� â �̵�
				pt.setVisible(true);
			} else if (o == pt.btPlus) {
				can2.w += 10;
				can2.h += 10;
			} else if (o == pt.btMinus) {
				if (can2.w > 3) { // ��ư�� ��� ������ �ƿ� �ȳ���. �ּ����� ũ�� ����
					can2.w -= 10;
					can2.h -= 10;
				}
			} else if (o == pt.btClear) {
				// �巡���� ������ �κ� �����
				can2.cr = can.getBackground();
			} else if (o == pt.btAllClear)

			{
				// ĵ������ ��� �����
				// Graphics Ŭ������ clearRect(x,y,w,h)
				Graphics g = can2.getGraphics();
				g.clearRect(0, 0, can.getWidth(), can.getHeight());
			} else if (o == pt.btColor) {
				// (Swing�� ����) JColorChooser�� ����� ������ �������� �׷�������
				Color selCr = JColorChooser.showDialog(null, "������", Color.blue); // null=��ũ�� �߾ӿ� ȭ�� ����
				can2.cr = selCr;
			} else if (o == pt.btClose) {
				// pt�� ����������
				// pt.setVisible(false);-> ���� ������ �ʴ°��ϻ���
				pt.dispose(); // �ý��� �ڿ��� �ݳ�����
			}
		}

	}

	public static void main(String[] args) {
		new MyDrawing(); // ������ �ҷ�����
	}
}