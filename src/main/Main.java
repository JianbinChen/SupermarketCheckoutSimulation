package main;

import gui.MainFrame;

public class Main {

	private static MainFrame mainFrame;
	private static Controller controller;
	public static void main(String[] args) {
		mainFrame = new MainFrame();
		controller = new Controller(mainFrame);
	}
	public static void setMainFrame(MainFrame mainFrame) {
		Main.mainFrame = mainFrame;
	}
	public static MainFrame getMainFrame() {
		return mainFrame;
	}
	public static void setController(Controller controller) {
		Main.controller = controller;
	}
	public static Controller getController() {
		return controller;
	}

}
