package simulationComponents;

import gui.SimulationFrame;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;

public class Simulation {

	private static long simulationTime;
	private static SimulationFrame simulationFrame;
	private static Timer timer;
	
	public Simulation( int minCustomerTimeDifference, int maxCustomerTimeDifference, int minCustomerServiceTime, int maxCustomerServiceTime, int maxQueues, long simulationTime, int minProductNumber,int maxProductNumber,int minProductServiceTime,int maxProductServiceTime,int expressCheckoutNumberes)
	{
		simulationFrame = new SimulationFrame();
		this.simulationTime = simulationTime*1000+System.currentTimeMillis(); // the time that the pop-up window will be retained
		try {
		    BufferedWriter out = new BufferedWriter(new FileWriter("log.txt")); //buffer the output to file to make it efficiently 
		    out.write("");	    
		    out.close();
		} catch (IOException e) {System.out.println(e.getMessage());
		}
		timer = new Timer();
		timer.schedule(new Thread(minCustomerTimeDifference, maxCustomerTimeDifference, minCustomerServiceTime, maxCustomerServiceTime, maxQueues,minProductNumber,maxProductNumber,minProductServiceTime,maxProductServiceTime,expressCheckoutNumberes), 1000,10);
			
	}
	//true is there is no more time to add a new client in order to serve him
	//before the end of simulation
	public static boolean isTimeForMoreClients(double maxTime)
	{
		if(simulationTime - maxTime - 1000 < System.currentTimeMillis())     //really don't understand it  
		{			
			return true;
		}
		return false;	
		
		
		
	}
	//true if the simulation is done
	public static boolean isDone()
	{		
		
		if(simulationTime <= System.currentTimeMillis() || simulationFrame.isVisible() == false) 
		{
			
			getSimulationFrame().setVisible(false);
			timer.cancel();
			return true;
		}
		return false;	
	}
	//writes a specific string to the file log.txt
	public static void write(String string)
	{
		try {
		    BufferedWriter out = new BufferedWriter(new FileWriter("log.txt",true));
		    out.write(string);		   
		    out.newLine();
		    out.close();
		} catch (IOException e) {System.out.println(e.getMessage());
		}	
	}

	public static void setSimulationFrame(SimulationFrame simulationFrame) {
		Simulation.simulationFrame = simulationFrame;
	}

	public static SimulationFrame getSimulationFrame() {
		return simulationFrame;
	}
	
	
}
