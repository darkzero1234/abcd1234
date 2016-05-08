import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class performancePanel extends JPanel implements ActionListener{
	private ArrayList<Performance> performances;
	JButton[] performance;
	
	
	
	private JDialog dialog = new JDialog();
	
	
	
	//for update
	private JTextField performancePlace = new JTextField(10);
	private JTextField performanceName = new JTextField(10);
	
	//identify line
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	
	private JButton ok= new JButton("ok");
	private JButton cancel = new JButton("cancel");
	
	JLabel pLabel = new JLabel("장소 : ");
	JLabel nLabel = new JLabel("공연이름 :");
	
	
	
	
	public performancePanel()
	{
		int i = 0;
		
		performances = ProgramGUI.getInstance().getPerformances();
		performance = new JButton[performances.size()];
		
		for(Performance perform : performances)
		{
			performance[i] = new JButton(perform.getName());
			performance[i].addActionListener(this);
			this.add(performance[i]);
			i++;
		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) { 
		Object o = e.getSource();
		
		for(int i=0; i<performances.size();i++)
		{
			if(performance[i] == o)//공연 눌렀을 때 켜지는 화면
			{
				
				JDialog Info= new PerformanceGUI(performances,i);
				Info.setLayout(new FlowLayout());
				//Info.add(new PerformanceGUI(performances,i));
			
			
				Info.setSize(400,200);
				Info.setLayout(new FlowLayout());
				Info.setVisible(true);
			
			
			}
		}
		
		/*
		if(ok == o)// 얜뭐죠?
		{
			ok.removeActionListener(this);
			cancel.removeActionListener(this);
			
			panel.add(pLabel);
			panel.add(performancePlace);
			panel2.add(nLabel);
			panel2.add(performanceName);
			
			ok.addActionListener(this);
			cancel.addActionListener(this);
			dialog.setSize(300,300);
			panel3.add(ok);
			panel3.add(cancel);
			dialog.setLayout(new FlowLayout(FlowLayout.LEFT,10,15));
			dialog.add(panel);
			dialog.add(panel2);
			dialog.add(panel3);
			
			dialog.setVisible(true);
		}
		*/
		 if(ok==o)
		{
			performances.add(new Performance(0, ProgramGUI.getInstance().getHost(), performanceName.getText(), new java.sql.Date(2016-5-20), new java.sql.Time(210000),10));
			ProgramGUI.getInstance().createPerformanceInformation();
			dialog.dispose();
		}
	}
}
