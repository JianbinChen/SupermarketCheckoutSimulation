package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class SimulationFrame extends JFrame{

	private JLabel head =  new JLabel("<html><h3>Queue Time | Customer Time</h3></html>");
	public SimulationFrame(){
	super("Simulation Window");
	this.setBounds(200, 20, 850, 680);
	this.setResizable(false);
	this.getContentPane().setBackground(Color.lightGray);
	this.getContentPane().setLayout(new SpringLayout());
	this.add(head);
	this.setVisible(true);
	}
}
