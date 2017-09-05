package chat;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dialog {
	JDialog imo;
	JButton btn1;

	public Dialog() {
		JLabel lb1= new JLabel();
		Icon icon=new ImageIcon("Penguins.jpg");
		imo = new JDialog();
		imo.setLayout(new GridLayout(0, 3));
		btn1 = new JButton(new ImageIcon("Penguins.jpg"));

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btn1) {
					

				}

			}
		});

		imo.add(btn1);

		imo.setVisible(true);
		imo.setSize(300, 300);
		imo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
