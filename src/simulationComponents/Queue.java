package simulationComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Queue extends JPanel{
	private int x;
	private int y;
	private int last = 10;
	private double servingStartTime = 0;
	private int queueNumber;
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private JLabel customerTimeLabel = new JLabel();
	private JLabel queueTimeLabel = new JLabel();
	
	public void paint(Graphics g)
	{
		super.paint(g);
		this.setBackground(Simulation.getSimulationFrame().getContentPane().getBackground());
		this.setBounds(get_x(), get_y(), 20, 20);
		if(last == 0) g.setColor(Color.BLACK);
		else {g.setColor(Color.RED);last--;}
		g.fillRect(0, 0, 15, 15);
		getQueueTimeLabel().setBounds(x-120, y, 60, 10);
		getCustomerTimeLabel().setBounds(x-40, y, 60, 10);
	
	}
	
	public Queue(int x, int y, int queueNumber) {
		super();
		this.set_x(x);
		this.set_y(y);
		this.setQueueNumber(queueNumber);
		Simulation.getSimulationFrame().add(getQueueTimeLabel());
		Simulation.getSimulationFrame().setVisible(true);
		Simulation.getSimulationFrame().add(getCustomerTimeLabel());
		Simulation.getSimulationFrame().setVisible(true);

		
	}
	
	public void addCustomerToQueue(double serviceTime)
	{
		if(this.getCustomers().size()==0) this.getCustomers().add(new Customer(this.x + 20, this.y, serviceTime));		
		else this.getCustomers().add(new Customer(this.getCustomers().get(this.getCustomers().size()-1).get_x()+20, this.y, serviceTime));     
		
		Simulation.getSimulationFrame().add(this.getCustomers().get(this.getCustomers().size()-1));		
		Simulation.getSimulationFrame().setVisible(true);
	}
	
	
	public void addCustomerToExpressQueue(double serviceTime) {
		if(this.getCustomers().size()==0) this.getCustomers().add(new Customer(this.x + 20, this.y, serviceTime));		
		else this.getCustomers().add(new Customer(this.getCustomers().get(this.getCustomers().size()-1).get_x()+20, this.y, serviceTime));     
		
		Simulation.getSimulationFrame().add(this.getCustomers().get(this.getCustomers().size()-1));		
		Simulation.getSimulationFrame().setVisible(true);
		
		
	}
	
	
	public void removeCustomerFromQueue()
	{
		Simulation.getSimulationFrame().getContentPane().remove(this.getCustomers().get(0));
		this.getCustomers().remove(0);	
		for(Customer temp : customers)
		{
			temp.set_x(temp.get_x()-20);
			servingStartTime = System.currentTimeMillis();
		}
		Simulation.getSimulationFrame().repaint();
	}
	
	public boolean isCustomerServed()
	{
		if (customers.size() == 0) return false;
		long timeNow = System.currentTimeMillis();
		if(timeNow > customers.get(0).getServiceTime() + servingStartTime) return true; 
		else return false;
	}

	public double timeToServe()
	{
		double sum = 0;
		for(Customer temp : customers)
		{
			sum += temp.getServiceTime();		
		}
		return sum;
	}
	
	public void displayRemainingTime()
	{
		String string,string2;
		long timeNow = System.currentTimeMillis();
		int time = (int) ((this.servingStartTime + customers.get(0).getServiceTime() - timeNow )/1000);
		string=Integer.toString(time/60) + ":" + Integer.toString(time%60);
		time = (int) ((this.servingStartTime + timeToServe() - timeNow )/1000);
		string2=Integer.toString(time/60) + ":" + Integer.toString(time%60);
		if(System.currentTimeMillis()%1000<=10) {getCustomerTimeLabel().setText(string);getQueueTimeLabel().setText(string2);}	
	}
	
	
	
	
	/**
	 * x axis setter
	 * @param x x axis value
	 */
	public void set_x(int x) {
		this.x = x;
	}
	/**
	 * x axis getter
	 * @return value of x
	 */
	public int get_x() {
		return x;
	}
	/**
	 * y axis setter
	 * @param y y axis value
	 */
	public void set_y(int y) {
		this.y = y;
	}
	/**
	 * y axis getter
	 * @return value of y
	 */
	public int get_y() {
		return y;
	}


	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setServingStartTime(double servingStartTime) {
		this.servingStartTime = servingStartTime;
	}

	public double getServingStartTime() {
		return servingStartTime;
	}

	public void setCustomerTimeLabel(JLabel customerTimeLabel) {
		this.customerTimeLabel = customerTimeLabel;
	}

	public JLabel getCustomerTimeLabel() {
		return customerTimeLabel;
	}

	public void setQueueTimeLabel(JLabel queueTimeLabel) {
		this.queueTimeLabel = queueTimeLabel;
	}

	public JLabel getQueueTimeLabel() {
		return queueTimeLabel;
	}

	public void setQueueNumber(int queueNumber) {
		this.queueNumber = queueNumber;
	}

	public int getQueueNumber() {
		return queueNumber;
	}

	

}
