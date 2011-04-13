package mainwindow;
import java.sql.Date;


public class EventBokning implements Bokning{
	private int eventID;
	private String ort;
	private Date datum;
	private int pris;
	private boolean searchDone = false;
	
	public EventBokning(){
		
	}
	
	public EventBokning(String ort, Date datum){
		this.ort = ort;
		this.datum = datum;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public int getEventID() {
		return eventID;
	}

	@Override
	public int pris() {
		return this.pris;
	}

	@Override
	public Bokningstyp getType() {
		return Bokningstyp.EVENT;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getOrt() {
		return ort;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getDatum() {
		return datum;
	}

	@Override
	public boolean searchIsDone() {
		return this.searchDone;
	}
	
	// TODO toString()

}
