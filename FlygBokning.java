import java.sql.Date;
import java.util.List;


public class FlygBokning implements Bokning{
	private int flightID;
	private List<Integer> platsNummer;
	private Date avgangsDatum;
	private Date ankomstDatum;
	private int pris;
	
	public FlygBokning(){
		
	}

	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}

	public int getFlightID() {
		return flightID;
	}

	public void setPlatsNummer(List<Integer> platsNummer) {
		this.platsNummer = platsNummer;
	}

	public List<Integer> getPlatsNummer() {
		return platsNummer;
	}
	
	public String toString(){
		return "FlightID: " + this.flightID + "\nPlatsnummer: " + this.platsNummer;
		// TODO
	}

	public void setAvgangsDatum(Date avgangsDatum) {
		this.avgangsDatum = avgangsDatum;
	}

	public Date getAvgangsDatum() {
		return avgangsDatum;
	}

	public void setAnkomstDatum(Date ankomstDatum) {
		this.ankomstDatum = ankomstDatum;
	}

	public Date getAnkomstDatum() {
		return ankomstDatum;
	}

	@Override
	public int pris() {
		return this.pris;
	}
}
