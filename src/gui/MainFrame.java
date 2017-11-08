package gui;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

// Extends the class JFrame and is used for user interface


public class MainFrame  extends JFrame{
	//interface fields
	private JTextField minCustomerTimeDifference = new JTextField("0");
	private JTextField maxCustomerTimeDifference = new JTextField("6");
	private JTextField minProductNumber = new JTextField("1");
	private JTextField maxProductNumber = new JTextField("2000");
	private JTextField minProductServiceTime = new JTextField("5");
	private JTextField maxProductServiceTime = new JTextField("60");
	private JTextField maxQueues = new JTextField("8");
	private JTextField expressCheckoutNumbers = new JTextField("2");
	private JTextField simulationTime = new JTextField("1500");

	
 
	public MainFrame()
	{
		super("SupermarketCheckout Simulation");
		this.setBounds(200, 170, 980, 600);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		GridLayout layout = new GridLayout(12,2);
		layout.setHgap(15);
		layout.setVgap(35);
		this.getContentPane().setLayout(layout);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.menu();
		this.setVisible(true);
		
	}

	//interface button
	private JButton start = new JButton("Start Simulation");
	private JButton lowCustomerRate = new JButton("Low Customer rate");

	private JButton highCustomerRate = new JButton("High Customer rate");
//	private JButton report = new JButton("Report ");

	
	public void menu() {
	
		this.add(new JLabel("Minimum Customer Rate:", JLabel.TRAILING));
		this.add(getMinCustomerTimeDifference());
		
		
		
		this.add(new JLabel("Maximum Customer Rate:", JLabel.TRAILING));
		this.add(getMaxCustomerTimeDifference());
		
		
		
		this.add(new JLabel("Minimum pruducts number:", JLabel.TRAILING));
		this.add(getMinProductNumber());
		
		
		this.add(new JLabel("Maximum pruducts number:", JLabel.TRAILING));
		this.add(getMaxProductNumber());
		
		
		this.add(new JLabel("minimum Product Check time:", JLabel.TRAILING));
		this.add(getMinProductServiceTime());
		
		
		this.add(new JLabel("maximum Product service time:", JLabel.TRAILING));
		this.add(getMaxProductServiceTime());
		
		
		this.add(new JLabel("maximum number of queues:", JLabel.TRAILING));
		this.add(getMaxQueues());
		
		
		this.add(new JLabel("the numbers of express checkout:", JLabel.TRAILING));
		this.add(getExpressCheckoutNumbers());
		
		
		
		this.add(new JLabel("simulation time:", JLabel.TRAILING));
		this.add(getSimulationTime());
		this.add(getlowCustomerRate());
		this.add(gethighCustomerRate());
		
		this.add(getStart());
		
		this.setVisible(true);
		
	}


	public void setStart(JButton start) {
		this.start = start;
	}


	public JButton getStart() {
		return start;
	}


	public void setlowCustomerRate(JButton lowCustomerRate) {
		this.lowCustomerRate = lowCustomerRate;
	}


	public JButton getlowCustomerRate() {
		return lowCustomerRate;
	}

	public void sethighCustomerRate(JButton highCustomerRate) {
		this.highCustomerRate = highCustomerRate;
	}


	public JButton gethighCustomerRate() {
		return highCustomerRate;
	}


	public void setMinCustomerTimeDifference(JTextField minCustomerTimeDifference) {
		this.minCustomerTimeDifference = minCustomerTimeDifference;
	}


	public JTextField getMinCustomerTimeDifference() {
		return minCustomerTimeDifference;
	}


	public void setMaxCustomerTimeDifference(JTextField maxCustomerTimeDifference) {
		this.maxCustomerTimeDifference = maxCustomerTimeDifference;
	}


	public JTextField getMaxCustomerTimeDifference() {
		return maxCustomerTimeDifference;
	}


	public void setMinProductServiceTime(JTextField minProductServiceTime) {
		this.minProductServiceTime = minProductServiceTime;
	}


	public JTextField getMinProductServiceTime() {
		return minProductServiceTime;
	}

	public JTextField getMinProductNumber() {
		return minProductNumber;
	}
	
	public JTextField getMaxProductNumber() {
		return maxProductNumber;
	}
	
	
	
	public void setMaxProductServiceTime(JTextField maxProductServiceTime) {
		this.maxProductServiceTime = maxProductServiceTime;
	}


	public JTextField getMaxProductServiceTime() {
		return maxProductServiceTime;
	}


	public void setMaxQueues(JTextField maxQueues) {
		this.maxQueues = maxQueues;
	}


	public JTextField getMaxQueues() {
		return maxQueues;
	}

	
	public JTextField getExpressCheckoutNumbers(){
		return expressCheckoutNumbers;
	}
	
	public void setExpressCheckoutNumbers(JTextField expressCheckoutNumbers){
		this.expressCheckoutNumbers =  expressCheckoutNumbers;
	}
	
	public void setSimulationTime(JTextField simulationTime) {
		this.simulationTime = simulationTime;
	}


	public JTextField getSimulationTime() {
		return simulationTime;
	}



}









