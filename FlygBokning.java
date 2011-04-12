import java.sql.Date;
import java.util.List;


public class FlygBokning implements Bokning{
	private int flightID;
	private List<Integer> platsNummer;
	private Date avgangsDatum;
	private Date ankomstDatum;
	private String avgangsOrt;
	private String ankomstOrt;
	private String klass;
	private int pris;
	private boolean searchDone = false;
	
	public FlygBokning(){
	}
	
	public FlygBokning(String avgangsOrt, String ankomstOrt, Date avgangsDatum, Date ankomstDatum){
		this.avgangsOrt = avgangsOrt;
		this.ankomstOrt = ankomstOrt;
		this.avgangsDatum = avgangsDatum;
		this.ankomstDatum = ankomstDatum;
		this.searchDone = true;
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
		return "FlightID: " + this.flightID + "\nPlatsnummer: " + this.platsNummer + 
				"\nAvgångsdatum: " + this.avgangsDatum + "\nAnkomstdatum: " + this.ankomstDatum +
				"\nAvgångsort: " + this.avgangsOrt + "\nAnkomstort" + this.ankomstOrt + "\nPris: " + this.pris;
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

	public void setAvgangsOrt(String avgangsOrt) {
		this.avgangsOrt = avgangsOrt;
	}

	public String getAvgangsOrt() {
		return avgangsOrt;
	}

	public void setAnkomstOrt(String ankomstOrt) {
		this.ankomstOrt = ankomstOrt;
	}

	public String getAnkomstOrt() {
		return ankomstOrt;
	}

	@Override
	public Bokningstyp getType() {
		return Bokningstyp.FLYG;
	}

	@Override
	public boolean searchIsDone() {
		return this.searchDone;
	}

	public void setKlass(String klass) {
		this.klass = klass;
	}

	public String getKlass() {
		return klass;
	}
}
