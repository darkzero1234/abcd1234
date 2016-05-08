
public class Seat {
	private boolean isReserved;
	
	public Seat(){
		this.isReserved = false;
	}
	
	public void reserve(){
		this.isReserved = true;
	}
	
	public void cancel(){
		this.isReserved = false;
	}
	
	public boolean isReserved(){
		return this.isReserved;
	}
}
