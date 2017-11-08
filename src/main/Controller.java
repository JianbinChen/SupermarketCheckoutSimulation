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
			
			int minCustomerServiceTime = 0;
			int maxCustomerServiceTime = 0;
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
			
			minCustomerServiceTime =  1;
			maxCustomerServiceTime = 1200;
			new Simulation( minCustomerTimeDifference, maxCustomerTimeDifference, minCustomerServiceTime, maxCustomerServiceTime, maxQueues, simulationTime,minProductNumber,maxProductNumber,minProductServiceTime,maxProductServiceTime,expressCheckoutNumberes);
			
			
		}
	};
private ActionListener lowFluxListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			getMainFrame().getMinCustomerTimeDifference().setText("0");
			getMainFrame().getMaxCustomerTimeDifference().setText("30");
			getMainFrame().getMinProductServiceTime().setText("0");
			getMainFrame().getMaxProductServiceTime().setText("80");
			getMainFrame().getMaxQueues().setText("10");
			getMainFrame().getSimulationTime().setText("300");
			
		}
	};
	
//private ActionListener standardFluxListener = new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			getMainFrame().getMinCustomerTimeDifference().setText("0");
//			getMainFrame().getMaxCustomerTimeDifference().setText("20");
//			getMainFrame().getMinProductServiceTime().setText("0");
//			getMainFrame().getMaxProductServiceTime().setText("100");
//			getMainFrame().getMaxQueues().setText("15");
//			getMainFrame().getSimulationTime().setText("300");
//			
//		}
//	};
	
private ActionListener highFluxListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			getMainFrame().getMinCustomerTimeDifference().setText("0");
			getMainFrame().getMaxCustomerTimeDifference().setText("12");
			getMainFrame().getMinProductServiceTime().setText("0");
			getMainFrame().getMaxProductServiceTime().setText("150");
			getMainFrame().getMaxQueues().setText("20");
			getMainFrame().getSimulationTime().setText("300");	
			
		}
	};
	
//private ActionListener reportListener = new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			new ReportFrame();	
//			
//		}
//	};
//	
//private ActionListener reflectionListener = new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent ae) {
//			try {
//			    BufferedWriter out = new BufferedWriter(new FileWriter("reflection.txt"));
//			    out.write("");	    
//			    out.close();
//			} catch (IOException e) {System.out.println(e.getMessage());
//			}
//			
//			DumpClasses.dump("main.Main");
//			DumpClasses.dump("main.Controller");
//			DumpClasses.dump("gui.MainFrame");
//			DumpClasses.dump("gui.ReportFrame");
//			DumpClasses.dump("gui.SimulationFrame");
//			DumpClasses.dump("simulationComponents.Controller");
//			DumpClasses.dump("simulationComponents.Customer");
//			DumpClasses.dump("simulationComponents.Queue");
//			DumpClasses.dump("simulationComponents.Simulation");
//			
//			new ReflectionFrame();
//			
//		}
//	};
//	
	
	
	public Controller(MainFrame mainFrame) {
		setMainFrame(mainFrame);
		getMainFrame().getStart().addActionListener(startListener);
		getMainFrame().getlowCustomerRate().addActionListener(lowFluxListener);
	//	getMainFrame().getStandardFlux().addActionListener(standardFluxListener);
		getMainFrame().gethighCustomerRate().addActionListener(highFluxListener);
	//	getMainFrame().getReport().addActionListener(reportListener);
	//	getMainFrame().getReflection().addActionListener(reflectionListener);
		
	}



	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}



	public MainFrame getMainFrame() {
		return mainFrame;
	}

}
