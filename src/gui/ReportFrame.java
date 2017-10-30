package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ReportFrame extends JFrame{
	
	private JTextArea textArea =  new JTextArea("");
	
	
	public ReportFrame()
	{
	super("Report");
	this.setBounds(200, 20, 850, 680);
	JScrollPane spane = new JScrollPane(textArea);
    spane.getHorizontalScrollBar().addAdjustmentListener(new MyAction());
    spane.getVerticalScrollBar().addAdjustmentListener(new MyAction());
    this.add(spane);
    report();
	this.setVisible(true);
	
	
	
	}
	//writes in the text area log.txt content
	private void report()
	{
		String str = new String();		
		textArea.setText("");
	  
		try {
			 BufferedReader in = new BufferedReader(new FileReader("log.txt"));
			 while ((str = in.readLine()) != null) {
			        textArea.setText(textArea.getText() + str + "\n");
			    }
			 } 
		catch (IOException e) {System.out.println(e.getMessage());}	
		
	}

}
