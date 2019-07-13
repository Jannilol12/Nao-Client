package nao;

public class Debugger {
	private Debugger() {}
	
	private static boolean enable = true;
	
	public static void redirectOutputs(boolean enable) {
//		System.setOut(out);
		
		
	}
	
	public static void setEnable(boolean value) {
		enable = value;
		redirectOutputs(enable);
	}
	
	public static boolean isEnable() {
		return enable;
	}
}
