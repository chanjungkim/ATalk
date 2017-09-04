package compile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Compile {
	
	String result = "";
	public Compile(String code,String input) {
		String codeFileName = "Code.java";
		String inputFileName = "input.txt";
		// �쁽�옱 寃쎈줈�쓽 jinyoung.java
		

		try {

			// �뙆�씪 媛앹껜 �깮�꽦
			File CodeFile = new File(codeFileName);
			File InputFile = new File(inputFileName);

			// true 吏��젙�떆 �뙆�씪�쓽 湲곗〈 �궡�슜�뿉 �씠�뼱�꽌 �옉�꽦, true�뾾�쓣 �떆 �깉濡� �옉�꽦
			FileWriter fwCode = new FileWriter(CodeFile);
			FileWriter fwInput = new FileWriter(InputFile);

			// �뙆�씪�븞�뿉 臾몄옄�뿴 �벐湲�
			fwCode.write(code);
			fwInput.write(input);
			
			fwCode.flush();
			fwInput.flush();
			
			// 媛앹껜 �떕湲�
			fwCode.close();
			fwInput.close();
			
			String command1 = "cmd.exe /c ./Java/jdk1.8.0_111/bin/ & javac Code.java & java Code < input.txt";

			try {
				// Runtime.getRuntime().exec(command2);
				// String m = sc.next();
				// Runtime.getRuntime().exec(m);

				Process process = Runtime.getRuntime().exec(command1);
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				Scanner scanner = new Scanner(br);
				scanner.useDelimiter(System.getProperty("line.separator"));
				
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
