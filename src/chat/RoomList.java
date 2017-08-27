package chat;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RoomList extends JFrame{
	private JPanel panel;
	
	public RoomList(){
		panel = new JPanel();

		add(panel);
		
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
//	public static void main(String[] args) {
//		RoomList r = new RoomList();
//	}
}
