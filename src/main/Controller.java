package main;

import gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import simulationComponents.Simulation;

public class Controller {
	
	private MainFrame mainFrame;
	
private ActionListener startListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			int minCustomerTimeDifference = 0;
			int maxCustomerTimeDifference = 0;
			
			int minProductNumber = 0;
			int maxProductNumber = 0;
			int minProductServiceTime = 0;
			int maxProductServiceTime = 0;
			int expressCheckoutNumberes= 0;
			int maxQueues = 0;
			int simulationTime = 0;
			try{
				minCustomerTimeDifference = Integer.parseInt(getMainFrame().getMinCustomerTimeDifference().getText());
				maxCustomerTimeDifference = Integer.parseInt(getMainFrame().getMaxCustomerTimeDifference().getText());
				minProductServiceTime = Integer.parseInt(getMainFrame().getMinProductServiceTime().getText());
				maxProductServiceTime = Integer.parseInt(getMainFrame().getMaxProductServiceTime().getText());
				minProductNumber = Integer.parseInt(getMainFrame().getMinProductNumber().getText());
				maxProductNumber = Integer.parseInt(getMainFrame().getMaxProductNumber().getText());
				expressCheckoutNumberes = Integer.parseInt(getMainFrame().getExpressCheckoutNumbers().getText());
				
				maxQueues = Integer.parseInt(getMainFrame().getMaxQueues().getText());
				simulationTime = Integer.parseInt(getMainFrame().getSimulationTime().getText());
			}
			catch(Exception e){JOptionPane.showMessageDialog(new JFrame(),e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);return;}
			
			new Simulation( minCustomerTimeDifference, maxCustomerTimeDifference, maxQueues, simulationTime,minProductNumber,maxProductNumber,minProductServiceTime,maxProductServiceTime,expressCheckoutNumberes);
				
		}
	};

	public Controller(MainFrame mainFrame) {
		setMainFrame(mainFrame);
		getMainFrame().getStart().addActionListener(startListener);
		
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	public MainFrame getMainFrame() {
		return mainFrame;
	}

}
