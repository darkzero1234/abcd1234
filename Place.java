public class Place {
	private String[] placeName = new String[3];
	private int[] maxSeat = new int[3];
	private int[] currentNum = new int[3];
	private Seat[][] seat = new Seat[3][];
	
	public Place(){
		this.placeName[0] = "학관 101호";
		this.maxSeat[0] = 60;
		this.seat[0] = new Seat[this.maxSeat[0]];
		for(int i=0;i<this.maxSeat[0];i++)
			this.seat[0][i] = new Seat();
		this.currentNum[0] = 0;
		
		this.placeName[1] = "학관 104호";
		this.maxSeat[1] = 80;
		this.seat[1] = new Seat[this.maxSeat[1]];
		for(int i=0;i<this.maxSeat[0];i++)
			this.seat[1][i] = new Seat();
		this.currentNum[1] = 0;
		
		this.placeName[2] = "채플";
		this.maxSeat[2] = 100;
		this.seat[2] = new Seat[this.maxSeat[2]];
		for(int i=0;i<this.maxSeat[0];i++)
			this.seat[2][i] = new Seat();
		this.currentNum[2] = 0;
	}
	
	public String getPlaceName(int place){
		return this.placeName[place];
	}
	
	public int getMaxSeat(int placeNum){
		return this.maxSeat[placeNum];
	}
	
	public void setCurrentNum(int place, int num){
		this.currentNum[place] = num;
	}
	
	public int getCurrentNum(int placeNum){
		return this.currentNum[placeNum];
	}
	
	public Seat[] getSeat(int placeNum){
		return this.seat[placeNum];
	}
}
