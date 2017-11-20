package simulationComponents;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class Simulation extends TimerTask{
	private static double minCustomerTimeDifference;
	private static double maxCustomerTimeDifference;
	private static int mixProductNumber;
	private static int maxProductNumber;
	private static double minProductServiceTime;
	private static double maxProductServiceTime;
	private static int expressCheckoutNumberes;
	private static int maxQueues;	
	private ArrayList<Queue> queues = new ArrayList<Queue>();
	private int queueNumber = 1;
	
	public Simulation ( double minCustomerTimeDifference, double maxCustomerTimeDifference, int maxQueues,int minProductNumber,int maxProductNumber,int minProductServiceTime,int maxProductServiceTime,int expressCheckoutNumberes)
	{
		Simulation.minCustomerTimeDifference = minCustomerTimeDifference * 1000;   //customer arriving time
		Simulation.maxCustomerTimeDifference = maxCustomerTimeDifference * 1000;
		Customer.setTimeDifference(System.currentTimeMillis());
		Simulation.expressCheckoutNumberes = expressCheckoutNumberes;
		Simulation.maxQueues = maxQueues;//maximum customers in the queue
		Simulation.mixProductNumber = minProductNumber ;
		Simulation.maxProductNumber = maxProductNumber ;
		Simulation.minProductServiceTime = minProductServiceTime ;
		Simulation.maxProductServiceTime = maxProductServiceTime ;
		
	}
	//method from timerTask
	@Override
	public void run() {
		if(SimulationController.isDone()==true) return;
		
		//creates a new customer at each customerTimeDifference interval
		if( Customer.getTimeDifference() <= System.currentTimeMillis())
			{
				newCustomer();
			}
		
		//removes served clients
		removeServedClients();
		
		//removes unused queues
		while (removeUnusedQueues()==true);
	
		//repaints the frame
		if(System.currentTimeMillis()%1000<=10)
			repaint()
			;			
	}

	private void repaint() {
		if(queues.size() ==0) return;
		for(Queue temp:queues)
		{
			temp.displayRemainingTime();		
		}
		if(queues.size() <expressCheckoutNumberes) return;
		for(int i=expressCheckoutNumberes+1;i<queues.size();i++)
			if(queues.get(i).get_y()-queues.get(i-1).get_y() != 20) {
				queues.get(i).set_y(queues.get(i-1).get_y()+20);
				for (Customer temp : queues.get(i).getCustomers()){
					temp.set_y(queues.get(i).get_y());
				}
			}
		SimulationController.getSimulationFrame().repaint();
	}
	
	//removes unused queues
	private boolean removeUnusedQueues() {
		for(int i=expressCheckoutNumberes; i<queues.size();i++)
		{
			if(queues.get(i).getCustomers().size() == 0)
			{				
				SimulationController.getSimulationFrame().getContentPane().remove(queues.get(i).getCustomerTimeLabel());
				SimulationController.getSimulationFrame().getContentPane().remove(queues.get(i).getQueueTimeLabel());
				SimulationController.getSimulationFrame().getContentPane().remove(queues.get(i));
				queues.remove(queues.get(i));
				SimulationController.getSimulationFrame().repaint();
				return true;
			}	
		}
		return false;
	}
	//removes served clients
	private void removeServedClients() {
		for(Queue temp : queues){
			if(temp.isCustomerServed()==true) {
				temp.removeCustomerFromQueue();
			}	
		}		
	}
	
	private void newCustomer()
	{
		double arrivalTime = 0;
		int expressIterator =0;
		int productNumber  = 0;
		double customerServiceTime = 0;
		double serviceProductTime = 0;
		double minServiceTime = Integer.MAX_VALUE;
		double minExpressServiceTime = Integer.MAX_VALUE;
		int iterator=0;
		
		Random generator = new Random();
		arrivalTime = generator.nextDouble()*(maxCustomerTimeDifference-minCustomerTimeDifference)+minCustomerTimeDifference;
		productNumber = (int)(generator.nextDouble()*(maxProductNumber - mixProductNumber) + mixProductNumber);
		
		for(int i=0; i<productNumber ;i++) {
			serviceProductTime = generator.nextDouble()*(maxProductServiceTime-minProductServiceTime)+minProductServiceTime;
			customerServiceTime += serviceProductTime;
		}
		System.out.println("customerServiceTime" + customerServiceTime);
		System.out.println("arrivalTime" + arrivalTime);
			
		if(queues.size()==0) {
			for(int i =0; i <expressCheckoutNumberes; i++) {
			queues.add(new Queue(150,30 + 20*i,queueNumber++));
			SimulationController.getSimulationFrame().add(queues.get(i));
			}
		}
		
		if(queues.size()==expressCheckoutNumberes) {
			queues.add(new Queue(150,50+20*expressCheckoutNumberes,queueNumber++));
			SimulationController.getSimulationFrame().add(queues.get(0));
				}
		
		//find which queue's waiting time is least
		for(int i=expressCheckoutNumberes;i<queues.size();i++)
		{
			if(queues.get(i).timeToServe()<minServiceTime) {
				minServiceTime = queues.get(i).timeToServe();
				iterator=i;
				}		
		}
		
		for (int i=0;i<expressCheckoutNumberes;i++)
		{
			if(queues.get(i).timeToServe()<minExpressServiceTime) {
				minExpressServiceTime = queues.get(i).timeToServe();
				expressIterator=i;
				}	
		}
		System.out.println("minServiceTime"+minServiceTime);
		if(minServiceTime > 1000 && queues.size()<maxQueues) 
			{	
			queues.add(new Queue(150,queues.get(queues.size()-1).get_y()+20,queueNumber++));
			iterator=queues.size()-1; // generate a new Queue 
			}
	
		SimulationController.getSimulationFrame().add(queues.get(iterator));
		System.out.println("productNumber==="+productNumber);
		if(productNumber <= 600) {
			queues.get(expressIterator).addCustomerToQueue(customerServiceTime);
			if(queues.get(expressIterator).getCustomers().size() == 1) queues.get(expressIterator).setServingStartTime(System.currentTimeMillis());
			Customer.setTimeDifference(arrivalTime + System.currentTimeMillis());	
			SimulationController.getSimulationFrame().setVisible(true);
		}
		else	{
			queues.get(iterator).addCustomerToQueue(customerServiceTime);
			if(queues.get(iterator).getCustomers().size() == 1) queues.get(iterator).setServingStartTime(System.currentTimeMillis());
			Customer.setTimeDifference(arrivalTime + System.currentTimeMillis());	
			SimulationController.getSimulationFrame().setVisible(true);
		}
		
//		Simulation.write("total wait time for each customer"+ queues.size()*(minCustomerServiceTime+maxCustomerServiceTime));
		SimulationController.write("Total utilization for each checkout"+ maxQueues*(minProductServiceTime+maxProductServiceTime));
		SimulationController.write("total products processed"+maxQueues*(mixProductNumber+maxProductNumber));
		SimulationController.write("Average customer wait time"+maxQueues*(mixProductNumber+maxProductNumber));
	}
	

	public void setMaxQueues(int maxQueues) {
		Simulation.maxQueues = maxQueues;
	}

	public int getMaxQueues() {
		return maxQueues;
	}

	public void setMaxCustomerTimeDifference(double maxCustomerTimeDifference) {
		Simulation.maxCustomerTimeDifference = maxCustomerTimeDifference;
	}
	public double getMaxCustomerTimeDifference() {
		return maxCustomerTimeDifference;
	}
	public void setMinCustomerTimeDifference(double minCustomerTimeDifference) {
		Simulation.minCustomerTimeDifference = minCustomerTimeDifference;
	}
	public double getMinCustomerTimeDifference() {
		return minCustomerTimeDifference;
	}

	public void setQueues(ArrayList<Queue> queues) {
		this.queues = queues;
	}

	public ArrayList<Queue> getQueues() {
		return queues;
	}
}

