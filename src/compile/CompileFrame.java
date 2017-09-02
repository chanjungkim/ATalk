package compile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class CompileFrame extends JFrame{
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
		//codeArea에 스크롤 생성
		scroll2 = new JScrollPane(inputArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//inputArea에 스크롤 생성

		
		this.getContentPane().add(scroll1);
		this.getContentPane().add(scroll2);
		btn = new JButton("RUN");
		btn.setBounds(300, 600, 100, 60);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Compile compile = new Compile(codeArea.getText());
				//codeArea 부분의 code부분을 compile class로 넘겨서 컴파일 시킴
				inputArea.setText(compile.getResult());
				//실행 결과를 inputArea로 set
				btn.setText("결과");
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
		"import java.util.*;" + "\n"+
		"import java.lang.*;" + "\n"+
		"import java.io.*;" + "\n" +
		"class jinyoung" + "\n" +
		"{" + "\n" +
		"	public static void main (String[] args) throws java.lang.Exception"+ "\n"+
		"	{" + "\n"+
		"		// your code goes here" + "\n"+
		"	}" + "\n"+
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
		CompileFrame compile = new CompileFrame();
	}
}

