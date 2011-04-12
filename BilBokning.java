import java.sql.Date;


public class BilBokning implements Bokning{
	private int bilID;
	private Date hamtdatum;
	private Date lamningsdatum;
	private int hamtort;
	private String lamningsort; //TODO int och String?
	private int pris;
	
	
	public BilBokning(){
		
	}

	public void setBilID(int bilID) {
		this.bilID = bilID;
	}

	public int getBilID() {
		return bilID;
	}

	public void setHamtdatum(Date hamtdatum) {
		this.hamtdatum = hamtdatum;
	}

	public Date getHamtdatum() {
		return hamtdatum;
	}

	public void setLamningsdatum(Date lamningsdatum) {
		this.lamningsdatum = lamningsdatum;
	}

	public Date getLamningsdatum() {
		return lamningsdatum;
	}

	public void setHamtort(int hamtort) {
		this.hamtort = hamtort;
	}

	public int getHamtort() {
		return hamtort;
	}

	public void setLamningsort(String lamningsort) {
		this.lamningsort = lamningsort;
	}

	public String getLamningsort() {
		return lamningsort;
	}

	@Override
	public int pris() {
		return this.pris;
	}
	
	@Override
	public String toString(){
		return "bilID: " + this.bilID + "\nhämtdatum: " + this.hamtdatum + "\nlämningsdatum: " + this.lamningsdatum + 
				"\nhämtort: " + this.hamtort + "\nlämningsort: " + this.lamningsort + "\nPris: " + this.pris;
	}

	@Override
	public Bokningstyp getType() {
		return Bokningstyp.HYRBIL;
	}

}
