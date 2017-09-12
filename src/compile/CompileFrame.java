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
		scroll1.setBounds(5, 5, 550, 450);
		textPanel.setBounds(5, 5, 550, 600);
		scroll2.setBounds(5, 455, 550, 100);
		outputArea.setBounds(5, 455, 550, 100);
		codeArea.setRows(10);
		codeArea.setColumns(10);
		codeArea.setTabSize(2);
		
		codeArea.setBackground(Color.BLACK);
		codeArea.setForeground(Color.cyan);
		codeArea.setSelectedTextColor(Color.black);
		codeArea.setDisabledTextColor(Color.gray);
		codeArea.setSelectionColor(Color.green);
		codeArea.setCaretColor(Color.white);
		codeArea.setLineWrap(true);
		
		textPanel.setBackground(Color.BLACK);
		
		btnPanel.setBackground(Color.DARK_GRAY);
		
		inputArea.setCaretColor(Color.WHITE);
		inputArea.setForeground(Color.WHITE);
		inputArea.setBackground(Color.BLACK);
				
		setBackground(Color.white);
		
		textPanel.setLayout(null);

		textPanel.add(scroll1);
		textPanel.add(scroll2);
		
		btnPanel.add(btn);
		
		String t = 
		"import java.util.*;\n"+
		"import java.lang.*;\n"+
		"import java.io.*;\n" +
		"\n" +
		"public class Code{\n" +
		"	public static void main (String[] args){\n"+
		"		// TODO: your code goes here. \n"+
		"	}\n"+
		"}";
		
		codeArea.setText(t);

		add(textPanel);
		add(btnPanel,new BorderLayout().PAGE_END);

		setVisible(true);
		setResizable(false);
		setSize(565, 625);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		new CompileFrame();
	}
}

