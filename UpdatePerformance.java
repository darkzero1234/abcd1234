import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UpdatePerformance implements ActionListener {
	private ArrayList<Performance> performances = ProgramGUI.getInstance().getPerformances();
	private Place place = new Place();//place저장공간이라서
	
	private JDialog dialog = new JDialog();
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	private JPanel panel5 = new JPanel();
	private JPanel panel6 = new JPanel();
	
	JLabel pLabel = new JLabel("장소 :");
	JLabel nLabel = new JLabel("공연이름 :");
	JLabel timeLabel =new JLabel("시간 :");
	JLabel dateLabel = new JLabel("날짜 :");
	JLabel costLabel = new JLabel("가격 : ");
	
	private JComboBox<String> performancePlace = new JComboBox<String>();
	private JComboBox<Integer> dayBox= new JComboBox<Integer>();
	private JComboBox<String>monthBox= new JComboBox<String>();
	private JComboBox<String>timeBox = new JComboBox<String>();
	private JTextField performanceName = new JTextField(10);
	
	private JTextField costTextfield = new JTextField(10);
	private JButton ok = new JButton("ok");
	private JButton cancel = new JButton("cancel");
	
	String[] timestamp = {"15:00", "16:00", "17:00", "18:00","19:00","20:00","21:00","22:00"} ;
	int[] timedata = {150000,160000,170000,180000,190000,200000,210000,220000};
	String[] monthstamp = {"January","February","March","April","May","June","July","August","September","October","November","Desember"};
	int[] monthdata = {1,2,3,4,5,6,7,8,9,10,11,12};
	int[] daydata = new int[31];

	int currentIndex=0;
	
	public JDialog updatePerformance(ArrayList<Performance> performances, int currentIndex)
	{
		this.currentIndex=currentIndex;
		//여기도 GUI로 따로뺄거 일단 구현만해놓음
		int placeindex =0;
		int dayindex=0;
		int monthindex=0;
		int timeindex=0;
		Performance update_perform = performances.get(currentIndex);
		for(int i=0; i<31; i++)
		{
		
			daydata[i] = i+1;
		}
		
		panel.add(pLabel);
		for(int i=0; i<3; i++)
		{
			performancePlace.addItem(place.getPlaceName(i));
			if(place.getPlaceName(i).equals(update_perform.getPlaceName()))//장소이름이 중복아니라고 가정햇을때 이렇게함 아마 다른방법도 잇을듯
				placeindex=i;
		}
		
		for(int i=0; i<31; i++)
		{
			dayBox.addItem(daydata[i]);
			if(daydata[i]==update_perform.getSchedule().getDate().getDate())
				dayindex=i;
		}
		for(int i=0; i<12;i++)
		{
			monthBox.addItem(monthstamp[i]);
			if(monthstamp[i].equals(String.valueOf(update_perform.getSchedule().getDate().getMonth()+1)))
				monthindex=i;
				
		}
		for(int i=0; i<timestamp.length; i++)
		{
			timeBox.addItem(timestamp[i]);
			if(timestamp[i].equals(update_perform.getSchedule().getTime().getHours()+":"+update_perform.getSchedule().getTime().getMinutes()))
				timeindex= i;
		}
		
		performancePlace.setSelectedIndex(placeindex);
		dayBox.setSelectedIndex(dayindex);
		monthBox.setSelectedIndex(monthindex);
		timeBox.setSelectedIndex(timeindex);
		
		
		panel.add(performancePlace);
		panel2.add(nLabel);
		panel2.add(performanceName);
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		dialog.setSize(500,400);
		panel3.add(dateLabel);
		panel3.add(monthBox);
		panel3.add(dayBox);
		
		panel4.add(timeLabel);
		panel4.add(timeBox);
		panel4.add(costLabel);
		panel4.add(costTextfield);
		
		panel5.add(ok);
		panel5.add(cancel);
		dialog.setLayout(new FlowLayout(FlowLayout.LEFT,10,15));
		dialog.add(panel);
		dialog.add(panel2);
		dialog.add(panel3);
		dialog.add(panel4);
		dialog.add(panel5);
		dialog.setVisible(true);
		return dialog;
	}

	public void eventUpdatePerformance(int currentIndex,int month, int day, String time,int cost,int placeNum){
		
		Calendar temp=Calendar.getInstance();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date d = null;
		Date date=new Date();
		temp.set(temp.get(Calendar.YEAR), month, day);
		
		date=new Date(temp.getTimeInMillis());
			
		
		Date t = null;
		
		SimpleDateFormat transFormat2= new SimpleDateFormat("HH:MM:SS");
		
		try {
			t=transFormat2.parse(time+":"+"00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		performances.set(currentIndex,new Performance(placeNum, ProgramGUI.getInstance().getHost(), performanceName.getText(), new java.sql.Date(date.getTime()), new java.sql.Time(t.getTime()),cost));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o==ok)
		{
			System.out.println("GGG");
			eventUpdatePerformance(currentIndex,monthdata[monthBox.getSelectedIndex()],daydata[dayBox.getSelectedIndex()],timestamp[timeBox.getSelectedIndex()],Integer.parseInt(costTextfield.getText()),performancePlace.getSelectedIndex());
			ProgramGUI.getInstance().createPerformanceInformation();
			dialog.dispose();
			
		}
		else if(o==cancel)
		{
			System.out.println("cancel 작동");
			ProgramGUI.getInstance().createPerformanceInformation();
			dialog.dispose();
		}
		
	}
}
