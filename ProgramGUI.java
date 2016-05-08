import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;


public class ProgramGUI extends JFrame implements ActionListener
{
	public static JFrame Program;
	public static ProgramGUI programGUI;
	private Host host;
	private  ArrayList<Performance> performances = new ArrayList<Performance>();
	private AddPerformance addPerform;

	
	JPanel p1 = new JPanel();
	JButton[] b = new JButton[10];
	Container contentPane;
	JButton cancel = new JButton("뒤로가기");
	JButton add = new JButton("추가");
	JButton update = new JButton("update");
	
	int user=0;
	
	JButton login = new JButton("Login");
	JRadioButton checkAudience = new JRadioButton("Audience",false);
	JRadioButton checkHost = new JRadioButton("Host",false);
	ButtonGroup btngroup = new ButtonGroup();
	
	JDialog logindialog= new JDialog();
	
	//singleton
	private ProgramGUI()
	{
		host=new Host("임현우","01093045749","darkzero");
		setTitle("title");
		setSize(768,576);
		setLayout(new BorderLayout());
		this.dispose();
		
		createLogin();
	}
	public static ProgramGUI getInstance()
	{
		if(programGUI == null)
		{
			programGUI = new ProgramGUI();
		}
		return programGUI;
		
	}
	
	public int getUser() {
		return user;
	}
	public void setUser(int currentuser) {
		this.user = currentuser;
	}
	public ArrayList<Performance> getPerformances() {
		return performances;
	}

	public static JFrame getJFrame()
	{
		return Program;
	}
	
	public Host getHost() {
		return host;
	}
	public void setHost(Host host) {
		this.host = host;
	}
	
	public ProgramGUI getProgmGUI()
	{
		return this;
	}
	
	public static void main(String[] args)
	{
		Program=ProgramGUI.getInstance();
	}
	
	public void createLogin()
	{
		logindialog.setLayout(new FlowLayout());
		contentPane = logindialog.getContentPane();
		logindialog.setSize(200,200);
		btngroup.add(checkAudience);
		btngroup.add(checkHost);
		
		login.addActionListener(this);
		
		contentPane.add(checkAudience);
		contentPane.add(checkHost);
		contentPane.add(login);
		
		
		logindialog.setVisible(true);
	}
	
	
	
	public void createPerformanceInformation()
	{
		if(contentPane!=null)
			contentPane.removeAll();
		contentPane= this.getContentPane();
		contentPane.setBackground(Color.BLUE);

		add.removeActionListener(this);
		cancel.removeActionListener(this);
		JPanel performPanelTop = new performancePanel();
		JPanel performPanelBottom = new JPanel();
		cancel.addActionListener(this);
	
		add.addActionListener(this);

		if(user == 1){
			performPanelBottom.add(add);
		}
		
		contentPane.add(performPanelTop,BorderLayout.NORTH);
		contentPane.add(performPanelBottom,BorderLayout.SOUTH);
		contentPane.revalidate();
		
		this.repaint();
		setVisible(true);
	}
	
	public void createBuyFrame()
	{
		JPanel bpanel = new buyPanel(0);
		contentPane.removeAll();
		contentPane.add(bpanel);
		
		cancel.addActionListener(this);
		
		bpanel.add(cancel);
		
		contentPane.revalidate();
		this.repaint();
	}

	public void createTicketInformation()
	{
		JButton[] c = new JButton[10];
		JPanel performPanelTop = new JPanel();
		JPanel performPanelBottom = new JPanel();
		contentPane.removeAll();
		cancel.removeActionListener(this);
		update.removeActionListener(this);
		add.removeActionListener(this);

		for(int j =0; j<3; j++)
		{
			c[j]= new JButton();
			c[j].setText(Integer.toString('x'));
			c[j].addActionListener(this);
			performPanelTop.add(c[j],BorderLayout.NORTH);
		}
		
		cancel.addActionListener(this);
		update.addActionListener(this);
		add.addActionListener(this);
		
		performPanelBottom.add(cancel);
		performPanelBottom.add(add);
		
		String[] header = {"티켓이름","티켓번호","좌석번호"};
		String[][] contents = {{"몰라","1","1"},{"알수","2","2"},{"가 없네","3","3"}};
		JTable table = TicketTable.createTable(contents, header);
		
		contentPane.add(performPanelTop,BorderLayout.NORTH);
		contentPane.add(table,BorderLayout.CENTER);
		contentPane.add(performPanelBottom,BorderLayout.SOUTH);
		
		
		contentPane.revalidate(); 
		
		
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		addPerform = new AddPerformance();
		
		//state pattern??
			if(o==cancel)
			{
				p1.removeAll();
				createPerformanceInformation();
				return;
			}
			else if(o==add)
			{
				addPerform.addPerformacne();
				
			}
			else if(o==login)
			{
				if(checkAudience.isSelected() == true)
				{

					user = 2;
					logindialog.dispose();
					createPerformanceInformation();

				}
				else if(checkHost.isSelected() == true)
				{

					user = 1;
					logindialog.dispose();
					createPerformanceInformation();
				}
			}
		}
}
