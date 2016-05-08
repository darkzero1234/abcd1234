import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PerformanceGUI extends JDialog implements ActionListener{

		JButton update=new JButton("update");
		JButton delete=new JButton("삭제");
		JButton reserve = new JButton("예약");
		JButton close = new JButton("닫기");
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		
		JLabel timeLabel = new JLabel();
		JLabel dateLabel = new JLabel();
		JLabel costLabel = new JLabel();
		
		
	
	//for information
		JLabel plabel = new JLabel();
		JLabel hlabel = new JLabel();
		JLabel slabel = new JLabel();
		JLabel nlabel = new JLabel();
		
		private int currentIndex;
		
	PerformanceGUI(ArrayList<Performance> performances,int index)
	{
		currentIndex = index;
		
		update.removeActionListener(this);
		delete.removeActionListener(this);
		
		
		Date tempdate = performances.get(index).getSchedule().getDate();
		Time temptime = performances.get(index).getSchedule().getTime();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tempdate);
		System.out.println(""+calendar.get(Calendar.YEAR));
		plabel.setText("장소 : " +performances.get(index).getPlaceName());
		hlabel.setText("주최자 : " +ProgramGUI.getInstance().getHost().getName());
		slabel.setText("잔여 좌석수 : " + (performances.get(index).getMaxSeat() - performances.get(index).getCurrentNum()));
		nlabel.setText("공연이름 : " +performances.get(index).getName());
		dateLabel.setText("날짜 : " + calendar.get(Calendar.YEAR) +"-" + calendar.get(Calendar.MONTH)+"-" +calendar.get(Calendar.DATE));	
		
		calendar.setTime(temptime);
		timeLabel.setText("시간 : " + calendar.get(Calendar.HOUR_OF_DAY) +"time");
		costLabel.setText("가격 : " + performances.get(index).getCost());
		
		
		panel1.add(plabel);
		panel1.add(hlabel);
		panel2.add(slabel);
		panel2.add(nlabel);
		panel3.add(dateLabel);
		panel3.add(timeLabel);
		panel3.add(costLabel);
		
		
		if(ProgramGUI.getInstance().getUser()==1)
		{
			update.addActionListener(this);
			delete.addActionListener(this);
			reserve.addActionListener(this);
			close.addActionListener(this);
			
			panel4.add(reserve);
			panel4.add(close);
			panel4.add(update);
			panel4.add(delete);
		}
		else if(ProgramGUI.getInstance().getUser()==2)
		{
			reserve.addActionListener(this);
			close.addActionListener(this);
			
			panel4.add(reserve);
			panel4.add(close);
		}		
		
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		UpdatePerformance updateperformance = new UpdatePerformance();
		DeletePerformance deleteperformance = new DeletePerformance();
		if(o==update)
		{
			ArrayList<Performance> performances = ProgramGUI.getInstance().getPerformances();
			JDialog Info= updateperformance.updatePerformance(performances,currentIndex);
			Info.setLayout(new FlowLayout());
			//Info.add(new PerformanceGUI(performances,i));
		
		
			Info.setSize(500,400);
			Info.setLayout(new FlowLayout());
			Info.setVisible(true);
			
			update.removeActionListener(this);
		}
		else if(o==delete)
		{
			JDialog Info=deleteperformance.deletePerformance(currentIndex);
			Info.setVisible(true);
			Info.setSize(200,200);
			Info.setLayout(new FlowLayout());
			Info.setVisible(true);
			this.dispose();
			
			delete.removeActionListener(this);
		}
		else if(o==reserve)
		{
			this.dispose();
			ProgramGUI.getInstance().createBuyFrame();
			
			reserve.removeActionListener(this);
		}
		else if(o==close)
		{
			this.dispose();
			ProgramGUI.getInstance().createPerformanceInformation();
			
			close.removeActionListener(this);
		}
	}
}
