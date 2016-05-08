import java.sql.*;

public class Schedule {
	private Time time;
	private Date date;
	private int duration;
	
	public Schedule()
	{
		this.time = null;
		this.date = null;
	}
	public Time getTime() {
		return this.time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public Date getDate() {
		return this.date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getDuration(){
		return this.duration;
	}
	public void getDuration(int duration){
		this.duration = duration;
	}
}
