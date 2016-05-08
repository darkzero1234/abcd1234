import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class buyPanel extends JPanel implements ActionListener{
	int performanceNum;
	
	private ArrayList<Performance> performances = ProgramGUI.getInstance().getPerformances();
	JButton[] ticketInformation = new JButton[performances.get(0).getMaxSeat()];
	
	public buyPanel(int performanceNum)
	{
		this.performanceNum = performanceNum;
		ticketInformation = new JButton[performances.get(performanceNum).getMaxSeat()];
		for(int i=0; i<performances.get(performanceNum).getMaxSeat();i++)
		{
			ticketInformation[i]=new JButton("ÁÂ¼® - " + (i+1));
			ticketInformation[i].addActionListener(this);
			this.add(ticketInformation[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		int seatIndex = 0;
		Object o = e.getSource();

		for(int i=0;i<performances.get(0).getMaxSeat();i++){
			if(ticketInformation[i] == o){
				seatIndex = i;
				new ReserveDialog(performances.get(this.performanceNum).getSeat()[seatIndex]);
			}
		}
	}
}
