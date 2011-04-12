
public class EventBokning implements Bokning{
	private int eventID;
	private int pris;
	
	public EventBokning(){
		
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
	
	// TODO toString()

}
