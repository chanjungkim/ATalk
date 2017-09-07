package compile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CompileFrame extends JFrame{

	//JFrame compile;
	JPanel textPanel;
	JPanel btnPanel;
	JTextArea codeArea; // 
	JTextArea inputArea; // input data
	JTextArea outputArea; // output data 
	JButton btn;
	JScrollPane scroll1; // scroll bar
	JScrollPane scroll2;
	public CompileFrame() {
		//compile = new JFrame();
		
		textPanel = new JPanel();
		btnPanel = new JPanel();
		
		codeArea = new JTextArea();
		inputArea = new JTextArea();
		outputArea = new JTextArea();
		scroll1 = new JScrollPane(codeArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//codeArea�뿉 �뒪�겕濡� �깮�꽦
		scroll2 = new JScrollPane(inputArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//inputArea�뿉 �뒪�겕濡� �깮�꽦

		
		this.getContentPane().add(scroll1);
		this.getContentPane().add(scroll2);
		btn = new JButton("RUN");
		btn.setBounds(300, 600, 100, 60);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Compile compile = new Compile(codeArea.getText(), inputArea.getText());
				
				inputArea.setText(compile.getResult());
				btn.setText("Done!");
			}
		});
		codeArea.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(btn.getText().equals("Done!")){
					btn.setText("RUN");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		scroll1.setBounds(50, 50, 550, 450);
		codeArea.setRows(10);
		codeArea.setColumns(10);
		codeArea.setBackground(Color.DARK_GRAY);
		codeArea.setForeground(Color.white);
		
		scroll2.setBounds(50, 500, 550, 100);
		inputArea.setBackground(Color.YELLOW);
		outputArea.setBounds(50, 500, 550, 100);
		
		
		textPanel.setLayout(null);
		textPanel.setBounds(0, 0, 600, 600);
		textPanel.add(scroll1);
		textPanel.add(scroll2);
		
		btnPanel.add(btn);
		
		String t = 
		"import java.util.*;\n"+
		"import java.lang.*;\n"+
		"import java.io.*;\n" +
		"import java.util.Scanner;\n" +
		"class Code{\n" +
		"	public static void main (String[] args){\n"+
		"		// your code goes here\n"+
		"	}\n"+
		"}";
		
		codeArea.setText(t);
		textPanel.setBackground(Color.DARK_GRAY);
		btnPanel.setBackground(Color.DARK_GRAY);
		
		add(textPanel);
		add(btnPanel,new BorderLayout().PAGE_END);
		
		setBackground(Color.DARK_GRAY);
		setVisible(true);
		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		new CompileFrame();
	}
}

