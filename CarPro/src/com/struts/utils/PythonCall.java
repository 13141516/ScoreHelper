package com.struts.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class StreamDrainer implements Runnable {
	private InputStream ins;
	private StringBuffer sb;

	public StreamDrainer(InputStream ins, StringBuffer sb) {
		this.ins = ins;
		this.sb = sb;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

public class PythonCall {
	private static final String cmd = "python " + PythonCall.class.getResource("").getPath().substring(1) + "score.py ";
 
	public StringBuffer selectFromPython(String... strings) {
		try {
			Process process = Runtime.getRuntime().exec(cmd + strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3]);
			StringBuffer result = new StringBuffer();
			new Thread(new StreamDrainer(process.getInputStream(), result)).start();
			new Thread(new StreamDrainer(process.getErrorStream(), result)).start();
			process.getOutputStream().close();
			process.waitFor();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
