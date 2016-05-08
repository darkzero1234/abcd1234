import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ReserveDialog implements ActionListener{
	JButton confirm = new JButton("확인");
	JButton ok = new JButton("확인");
	JButton cancel = new JButton("취소");
	JDialog buyTicket;
	Seat seat;
	
	public ReserveDialog(Seat seat){
		this.seat = seat;
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		confirm.addActionListener(this);
		
		buyTicket = new JDialog(ProgramGUI.getJFrame(),"A",true);
		
		buyTicket.setSize(180, 100);
		buyTicket.setLayout(new FlowLayout());
	
		if(!seat.isReserved()){
			buyTicket.add(new JLabel("예약하시겠습니까?",JLabel.CENTER));
			buyTicket.add(confirm);
			buyTicket.add(cancel);
		}else{
			buyTicket.add(new JLabel("이미 예약된 좌석입니다.",JLabel.CENTER));
			buyTicket.add(ok);
		}
		
		buyTicket.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok || e.getSource() == cancel){
			buyTicket.dispose();
		}else if(e.getSource() == confirm){
			seat.reserve();
			buyTicket.dispose();
		}
	}
}