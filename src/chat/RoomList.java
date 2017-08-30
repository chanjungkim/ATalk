package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RoomList extends JFrame{
	private JPanel panel;
	private JPanel topPanel;
	private JPanel listPanel;
	private JButton currentRoom;
	private JButton roomList;
	private JButton setting;
	private JButton room;
	
	public RoomList(){
		panel = new JPanel();
		topPanel = new JPanel();
		listPanel = new JPanel();
		currentRoom = new JButton("현재 방");
		roomList = new JButton("목록"); 
		setting = new JButton("설정");
		room = new JButton("안드로이드 스터디 방");

		
		// Fucntion
		room.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Chat chat = new Chat();
			}
		});
		
		setting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Setting set = new Setting();
			}
		});
		
		// Layout
		panel.setLayout(new BoxLayout(panel, 1));
		
		// 
		topPanel.add(roomList);
		topPanel.add(currentRoom);
		topPanel.add(setting);
		listPanel.add(room);		
		
		panel.add(topPanel);
		panel.add(listPanel);
		
		add(panel);
		
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		RoomList r = new RoomList();
	}
}
