package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RoomPanel extends JPanel{
	private JPanel panel;
	private JPanel titlePanel;
	private JPanel rightPanel;
	public JButton roomBtn;
	private JLabel masterLb;
	private JLabel populationLb;
	private JLabel lang;
	
	// For saving each room info.
	private static int roomCounter=0;
	private String title;
	private String masterID;
	private String language;
	private String roomPassword;
	private int populationLimitation;
	
	public RoomPanel(String title, String maker, int maxPopulation, String language, String password) {
		panel = new JPanel();
		titlePanel = new JPanel();
		rightPanel = new JPanel();
		roomBtn = new JButton(title);
		masterLb = new JLabel("πÊ¿Â: " + maker+" ");
		populationLb = new JLabel("¿Œø¯: 1/" + maxPopulation);
		lang = new JLabel("ææÓ: " + language);
		++roomCounter;
		
		titlePanel.setLayout(new BorderLayout());
		panel.setLayout(new GridLayout(1, 2));
		rightPanel.setLayout(new GridLayout(2, 2));
		this.title = title;
		this.masterID = maker;

		this.populationLimitation = maxPopulation;
		this.language=language;
		this.roomPassword = password;
		
		roomBtn.setFont(new Font("πŸ≈¡", Font.BOLD, 25));	
		masterLb.setFont(new Font("πŸ≈¡", Font.BOLD, 25));
		populationLb.setFont(new Font("πŸ≈¡", Font.BOLD, 25));
		
		setBackground(Color.blue);
		titlePanel.add(roomBtn);
		rightPanel.add(masterLb);
		rightPanel.add(populationLb);
		rightPanel.add(lang);
		panel.add(titlePanel);
		panel.add(rightPanel);
		add(panel);
		setVisible(true);
	}

	public String getTitle() {
		return title;
	}
	public JLabel getPopulationLb() {
		return populationLb;
	}

	public void setPopulationLb(JLabel populationLb) {
		this.populationLb = populationLb;
	}

	public static int getRoomCounter() {
		return roomCounter;
	}

	public static void setRoomCounter(int roomCounter) {
		RoomPanel.roomCounter = roomCounter;
	}

	public String getMasterID() {
		return masterID;
	}

	public void setMasterID(String masterID) {
		this.masterID = masterID;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRoomPassword() {
		return roomPassword;
	}

	public void setRoomPassword(String roomPassword) {
		this.roomPassword = roomPassword;
	}
	
}
