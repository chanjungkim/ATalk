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
	Canvas can; // 부모타입
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
		p2 = new JPanel() { // 여백주기
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

		can = new MyCanvas(); // 도화지 역할을 하는 컴포넌트 MyCanvas는 can을 상속 받는 자식->원이 갑자기 생김
		can.setSize(300, 300); // 도화지 크기
		can.setBackground(Color.white); // 도화지 배경색 주기
		p2.add(can);

		// 리스너 부착 -------------------
		MyHandler my = new MyHandler();
		can.addMouseMotionListener(my); // 캔버스 객체에 마우스모션리스너를 부착한다.
		btR.addActionListener(my);
		btG.addActionListener(my);
		btB.addActionListener(my);
		btOpen.addActionListener(my);

		// pt버튼(PaintToolFrame 클래스꺼)에도 리스너를 부착하자
		pt.btPlus.addActionListener(my);
		pt.btMinus.addActionListener(my);
		pt.btClear.addActionListener(my);
		pt.btAllClear.addActionListener(my);
		pt.btColor.addActionListener(my);
		pt.btClose.addActionListener(my);

		// 이번에는 메인에서 안하고 생성자에서 한다
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
				// 마우스를 드래그한 지점의 x좌표,y좌표를 얻어와서 can의 x,y 좌표값에 전달한다.

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
			// paint()는 JVM이 호출해주는 메소드으로 변경x, repaint을 써서 재사용하자
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
	// 서버로부터 메세지를 받는 내부 쓰레드 클래스
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

					// paint()는 JVM이 호출해주는 메소드으로 변경x, repaint을 써서 재사용하자
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
				// 마우스를 드래그한 지점의 x좌표,y좌표를 얻어와서 can의 x,y 좌표값에 전달한다.
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
				// 새로운 'paintToolJframe' 생성해서 창 열기
				// PaintToolFrame pt = new PaintToolFrame(); 여기서 코드 넣으면 매번 프레임 생성 ㅜ
				// pt.setSize(400, 400);
				pt.pack(); // 크기를 압축해서 보여줌
				pt.setLocation(getWidth(), 0); // x축만큼 오른쪽으로 창 이동
				pt.setVisible(true);
			} else if (o == pt.btPlus) {
				can2.w += 10;
				can2.h += 10;
			} else if (o == pt.btMinus) {
				if (can2.w > 3) { // 버튼을 계속 누르면 아예 안나옴. 최소한의 크기 설정
					can2.w -= 10;
					can2.h -= 10;
				}
			} else if (o == pt.btClear) {
				// 드래그한 지점만 부분 지우기
				can2.cr = can.getBackground();
			} else if (o == pt.btAllClear)

			{
				// 캔버스를 모두 지우기
				// Graphics 클래스의 clearRect(x,y,w,h)
				Graphics g = can2.getGraphics();
				g.clearRect(0, 0, can.getWidth(), can.getHeight());
			} else if (o == pt.btColor) {
				// (Swing에 있음) JColorChooser를 띄워서 선택한 색상으로 그려지도록
				Color selCr = JColorChooser.showDialog(null, "색선정", Color.blue); // null=스크린 중앙에 화면 나옴
				can2.cr = selCr;
			} else if (o == pt.btClose) {
				// pt만 닫혀지도록
				// pt.setVisible(false);-> 눈에 보이지 않는것일뿐임
				pt.dispose(); // 시스템 자원을 반납해줌
			}
		}

	}

	public static void main(String[] args) {
		new MyDrawing(); // 생성자 불러오기
	}
}