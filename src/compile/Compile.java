package compile;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JTextArea;

public class Compile {
	
	String result = "";
	public Compile(String code) {
		String fileName = "jinyoung.java";
		// 현재 경로의 jinyoung.java
		

		try {

			// 파일 객체 생성
			File file = new File(fileName);

			// true 지정시 파일의 기존 내용에 이어서 작성, true없을 시 새로 작성
			FileWriter fw = new FileWriter(file);

			// 파일안에 문자열 쓰기
			fw.write(code);
			fw.flush();

			// 객체 닫기
			fw.close();

			String command1 = "cmd.exe /c javac jinyoung.java & java jinyoung";

			try {
				// Runtime.getRuntime().exec(command2);
				// String m = sc.next();
				// Runtime.getRuntime().exec(m);

				Process process = Runtime.getRuntime().exec(command1);
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				Scanner scanner = new Scanner(br);
				scanner.useDelimiter(System.getProperty("line.separator"));
				String intpt;
				while (scanner.hasNext()) {
		//			System.out.println(scanner.next().toString());
					//super.setInputTextArea(scanner.next().toString());
					result += scanner.next().toString() + "\n";
				}
				setResult(result);
				scanner.close();
				br.close();

			} catch (IOException e3) {
				e3.printStackTrace();
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResult() {
		return result;
	}

}
