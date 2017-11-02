package simulationComponents;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class Thread extends TimerTask{
	
	
	private static double minCustomerTimeDifference;
	private static double maxCustomerTimeDifference;
	private static double minCustomerServiceTime;
	private static double maxCustomerServiceTime;
	
	private static int mixProductNumber;
	private static int maxProductNumber;
	
	private static double minProductServiceTime;
	private static double maxProductServiceTime;
	
	
	
	
	
	
	private static int maxQueues;	
	private ArrayList<Queue> queues = new ArrayList<Queue>();
	private int queueNumber = 1;
	
	
	public Thread ( double minCustomerTimeDifference, double maxCustomerTimeDifference, double minCustomerServiceTime, double maxCustomerServiceTime, int maxQueues,int minProductNumber,int maxProductNumber,int minProductServiceTime,int maxProductServiceTime)
	{
		Thread.minCustomerTimeDifference = minCustomerTimeDifference * 1000;
		Thread.maxCustomerTimeDifference = maxCustomerTimeDifference * 1000;
		Customer.setTimeDifference(System.currentTimeMillis());
		Thread.minCustomerServiceTime = minCustomerServiceTime * 1000;
		Thread.maxCustomerServiceTime = maxCustomerServiceTime * 1000;
/*****/
		Thread.minCustomerServiceTime = 1;
		Thread.maxCustomerServiceTime = 8;
/****/		
		Thread.maxQueues = maxQueues;//maximum customers in the queue
		
		Thread.mixProductNumber = minProductNumber ;
		Thread.maxProductNumber = maxProductNumber ;
		Thread.minProductServiceTime = minProductServiceTime ;
		Thread.maxProductServiceTime = maxProductServiceTime ;
		
	}
	//method from timerTask
	@Override
	public void run() {
		
		if(Simulation.isDone()==true) return;
		
		//creates a new customer at each customerTimeDifference interval
		if(Simulation.isTimeForMoreClients(maxServeTime()) == false && Customer.getTimeDifference() <= System.currentTimeMillis())
			{
				newCustomer();
			}
		
		//removes served clients
		removeServedClients();
		
		
		
		//removes unused queues
		while (removeUnusedQueues()==true);
		
		//optimize();
		
		//repaints the frame
		if(System.currentTimeMillis()%1000<=10)
			repaint();
				
	}


	private double maxServeTime() {
		double time=0;
		for(Queue temp:queues)
		{
			if(time < temp.timeToServe()) time = temp.timeToServe();	
		}
		if(time==0) time=maxCustomerServiceTime+maxCustomerTimeDifference;
		return time;
	}
	//used to optimize the queues but since the 
	//testing showed that this method is never used as intended is unused
	@SuppressWarnings("unused")
	private void optimize() {
		double maxServiceTime = -1;
		double minServiceTime = Integer.MAX_VALUE;
		int queueIteratorFrom = -1;
		int queueIteratorTo = -1;
		for(Queue temp : queues)
		{
			if(temp.getCustomers().get(temp.getCustomers().size()-1).getServiceTime() > maxServiceTime)
				{
				maxServiceTime = temp.timeToServe();
				queueIteratorFrom = queues.indexOf(temp);
				}
			if(temp.timeToServe() < minServiceTime)
				{
				minServiceTime = temp.timeToServe();
				queueIteratorTo = queues.indexOf(temp);
				}
		
		}
		if(queueIteratorFrom<2 || queueIteratorTo<2) return;
		if(queues.get(queueIteratorTo).timeToServe() + maxServiceTime < queues.get(queueIteratorFrom).timeToServe())
		{
			Customer customer = queues.get(queueIteratorTo).getCustomers().get(queues.get(queueIteratorTo).getCustomers().size()-1);
			customer.set_x(queues.get(queueIteratorFrom).get_x());
			queues.get(queueIteratorFrom).getCustomers().add(customer);
			//System.out.println("da");
			
		}
		
	}
	//repaints the frame
	private void repaint() {
		if(queues.size()==0) return;
		for(Queue temp:queues)
		{
			temp.displayRemainingTime();		
		}
		if(queues.get(0).get_y()!=30) 
			{
			queues.get(0).set_y(30); 
			for (Customer temp : queues.get(0).getCustomers())
			{
				temp.set_y(queues.get(0).get_y());
			}
		
			}
		for(int i=1;i<queues.size();i++)
			if(queues.get(i).get_y()-queues.get(i-1).get_y() != 20) 
				{
				queues.get(i).set_y(queues.get(i-1).get_y()+20);
				for (Customer temp : queues.get(i).getCustomers())
				{
					temp.set_y(queues.get(i).get_y());
				}
				
				}
		
		Simulation.getSimulationFrame().repaint();
	}
	//removes unused queues
	private boolean removeUnusedQueues() {
		for(Queue temp : queues)
		{
			if(temp.getCustomers().size() == 0) 
			{
				Simulation.getSimulationFrame().getContentPane().remove(temp.getCustomerTimeLabel());
				Simulation.getSimulationFrame().getContentPane().remove(temp.getQueueTimeLabel());
				Simulation.getSimulationFrame().getContentPane().remove(temp);
				queues.remove(temp);
				Simulation.getSimulationFrame().repaint();
				
				return true;
			}	
			
		}
		
		return false;
	}
	//removes served clients
	private void removeServedClients() {
		for(Queue temp : queues)
		{
			if(temp.isCustomerServed()==true) 
			{
				temp.removeCustomerFromQueue();
				Simulation.write("customer left from queue " + Integer.toString(temp.getQueueNumber()));
			}	
		}		
	}
	//creates a new customer at each customerTimeDifference interval
	private void newCustomer()
	{
		Random generator = new Random();
		double arrivalTime = 0;
		arrivalTime = generator.nextDouble()*(maxCustomerTimeDifference-minCustomerTimeDifference)+minCustomerTimeDifference;
			
		int productNumber  = 0;
		productNumber = (int)(generator.nextDouble()*(maxProductNumber - mixProductNumber) + mixProductNumber);
		
		double customerServiceTime = 0;
		double serviceProductTime = 0;
		
		for(int i=0; i<productNumber ;i++) {
			serviceProductTime = generator.nextDouble()*(maxProductServiceTime-minProductServiceTime)+minProductServiceTime;
			System.out.println("ServiceProductTime" + serviceProductTime);
			System.out.println("productNumber" + productNumber);
			customerServiceTime += serviceProductTime;
		}
		System.out.println("customerServiceTime" + customerServiceTime);
		
	
//		
//		if(queues.size()==0 && expressCheckoutNumbers ==1 ) {
//			queues.add(new Queue(150,30,queueNumber++));
//				}
//		for(int i =1 ;i< expressCheckoutNumbers;i++) {
//			queues.add(new Queue(150,queues.get(queues.size()-1).get_y()+20,queueNumber++));
//			
//		}
//		
		
		if(queues.size()==0) {
			queues.add(new Queue(150,30,queueNumber++));
			//Simulation.getSimulationFrame().add(queues.get(0));
				}
		double minServiceTime = Integer.MAX_VALUE;
		int iterator=0;
		for(int i=0;i<queues.size();i++)
		{
			if(queues.get(i).timeToServe()<minServiceTime) {
				minServiceTime = queues.get(i).timeToServe();
				iterator=i;
				}		
		}
		
		
		/*
		 *   /////////////////////////////////
		 *   !!!!!!!!!ATTENTION!!!!!!!!!!
		 *   HERE IS THE VALUE OF HOW MANY PEOPLE SHOULD BE ON A QUEUE
		 *   
		 *   			 		  VALUE
		 *                  		|									*/
		
		
		
		
		if(minServiceTime > maxCustomerServiceTime && queues.size()<maxQueues) 
			{	
			queues.add(new Queue(150,queues.get(queues.size()-1).get_y()+20,queueNumber++));
			iterator=queues.size()-1; // generate a new Queue 
			
			}
		
		Simulation.getSimulationFrame().add(queues.get(iterator));		
		queues.get(iterator).addCustomerToQueue(customerServiceTime);
		Simulation.write("new customer arrived to queue " + queues.get(iterator).getQueueNumber());
		if(queues.get(iterator).getCustomers().size() == 1) queues.get(iterator).setServingStartTime(System.currentTimeMillis());
		Customer.setTimeDifference(arrivalTime + System.currentTimeMillis());	
		Simulation.getSimulationFrame().setVisible(true);
	}

	
	
	public void setMinCustomerServiceTime(double minCustomerServiceTime) {
		Thread.minCustomerServiceTime = minCustomerServiceTime;
	}

	public double getMinCustomerServiceTime() {
		return minCustomerServiceTime;
	}

	public void setMaxCustomerServiceTime(double maxCustomerServiceTime) {
		Thread.maxCustomerServiceTime = maxCustomerServiceTime;
	}

	public double getMaxCustomerServiceTime() {
		return maxCustomerServiceTime;
	}

	public void setMaxQueues(int maxQueues) {
		Thread.maxQueues = maxQueues;
	}

	public int getMaxQueues() {
		return maxQueues;
	}

	public void setMaxCustomerTimeDifference(double maxCustomerTimeDifference) {
		Thread.maxCustomerTimeDifference = maxCustomerTimeDifference;
	}
	public double getMaxCustomerTimeDifference() {
		return maxCustomerTimeDifference;
	}
	public void setMinCustomerTimeDifference(double minCustomerTimeDifference) {
		Thread.minCustomerTimeDifference = minCustomerTimeDifference;
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

