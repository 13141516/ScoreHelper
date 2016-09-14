package com.struts.utils;

public class TestAccount {
	private static final String cmd = "python " + TestAccount.class.getResource("").getPath().substring(1) + "test.py ";
 
	public StringBuffer selectFromPython(String... strings) {
		try {
			Process process = Runtime.getRuntime().exec(cmd + strings[0] + " " + strings[1]);
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

