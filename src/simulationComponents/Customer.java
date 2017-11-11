package simulationComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Customer extends JPanel{
	private int x;
	private int y;	
	private static double timeDifference;
	private double serviceTime;
	private int last = 10;
	//private int productNumber;
	
	public void paint(Graphics g)
	{
		super.paint(g);
		this.setBackground(Simulation.getSimulationFrame().getContentPane().getBackground());
		
		this.setBounds(get_x(), get_y(), 17, 17);
		if(last == 0 ) g.setColor(Color.BLUE);
		if(last > 0 ) {g.setColor(Color.RED);last--;}
		g.fillOval(0, 0, 17, 17);	
	}
	
	public Customer(int x, int y, double serviceTime) {
		super();
		this.set_x(x);
		this.set_y(y);
		this.setServiceTime(serviceTime);	
	}	
	
	public void set_x(int x) {
		this.x = x;
	}

	public int get_x() {
		return x;
	}

	public void set_y(int y) {
		this.y = y;
	}
	
	public int get_y() {
		return y;
	}

	public void setServiceTime(double serviceTime) {
		this.serviceTime = serviceTime;
	}
	public double getServiceTime() {
		return serviceTime;
	}
	
	public static void setTimeDifference(double timeDifference) {
		Customer.timeDifference = timeDifference;
	}
	
	public static double getTimeDifference() {
		return timeDifference;
	}

}
