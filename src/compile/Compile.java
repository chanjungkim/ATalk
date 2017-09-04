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
		try {
			File codeFile = new File("Code.java");
			File inputFile = new File("input.txt");
			
			FileWriter fwCode = new FileWriter(codeFile);
			FileWriter fwInput = new FileWriter(inputFile);

			fwCode.write(code);
			fwInput.write(input);
			
			fwCode.flush();
			fwInput.flush();
			
			fwCode.close();
			fwInput.close();
			
		    // 디렉토리 위치 구하기
			String position = codeFile.getCanonicalPath().substring(0, codeFile.getCanonicalPath().length()-codeFile.getName().length());
		    position = position.replace((char)92, (char)47);
	    	System.out.println(position);
	    	
			String command1 = "cmd.exe /c "+position+"/jdk1.8.0_111/bin/javac Code.java & "+position+"/jdk1.8.0_111/bin/java Code < input.txt";

			try {
				// Runtime.getRuntime().exec(command2);
				// String m = sc.next();
				// Runtime.getRuntime().exec(m);

				Process process = Runtime.getRuntime().exec(command1);
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				Scanner scanner = new Scanner(br);
				scanner.useDelimiter(System.getProperty("line.separator"));
				
				while (scanner.hasNext()) {
					System.out.println(scanner.next().toString());
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
