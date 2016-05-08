
public class Ticket {
	private Schedule schedule;
	private String serialnumber;
	private int cost;
	private Performance performance;
	
	public Ticket(Performance performance){
		this.performance=performance;
	}
	
}
