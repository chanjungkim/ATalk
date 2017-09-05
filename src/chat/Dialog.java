package chat;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class Dialog {
	JDialog imo;
	JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

	public Dialog() {
		imo = new JDialog();
		imo.setLayout(new GridLayout(0, 3));
		btn1 = new JButton(new ImageIcon("Penguins.jpg"));
		btn2 = new JButton("ff");
		btn3 = new JButton("gg");
		btn4 = new JButton("dd");
		btn5 = new JButton("ff");
		btn6 = new JButton("gg");
		btn7 = new JButton("dd");
		btn8 = new JButton("ff");
		btn9 = new JButton("gg");
		
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		imo.add(btn1);
		imo.add(btn2);
		imo.add(btn3);
		imo.add(btn4);
		imo.add(btn5);
		imo.add(btn6);
		imo.add(btn7);
		imo.add(btn8);
		imo.add(btn9);
		imo.setVisible(true);
		imo.setSize(300, 300);
		imo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
