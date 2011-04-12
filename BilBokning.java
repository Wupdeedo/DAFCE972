import java.sql.Date;


public class BilBokning implements Bokning{
	private int bilID;
	private Date hamtDatum;
	private Date lamningsDatum;
	private int hamtOrt;
	private String lamningsOrt; //TODO int och String?
	private String kategori;
	private int pris;
	private boolean searchDone = false;
	
	
	public BilBokning(){
		
	}
	
	public BilBokning(Date hamtDatum, Date lamningsDatum, int hamtOrt, String lamningsOrt, String kategori){
		this.hamtDatum = hamtDatum;
		this.lamningsDatum = lamningsDatum;
		this.hamtOrt = hamtOrt;
		this.lamningsOrt = lamningsOrt;
		this.setKategori(kategori);
		this.searchDone = true;
	}

	public void setBilID(int bilID) {
		this.bilID = bilID;
	}

	public int getBilID() {
		return bilID;
	}

	public void setHamtdatum(Date hamtdatum) {
		this.hamtDatum = hamtdatum;
	}

	public Date getHamtdatum() {
		return hamtDatum;
	}

	public void setLamningsdatum(Date lamningsdatum) {
		this.lamningsDatum = lamningsdatum;
	}

	public Date getLamningsdatum() {
		return lamningsDatum;
	}

	public void setHamtort(int hamtort) {
		this.hamtOrt = hamtort;
	}

	public int getHamtort() {
		return hamtOrt;
	}

	public void setLamningsort(String lamningsort) {
		this.lamningsOrt = lamningsort;
	}

	public String getLamningsort() {
		return lamningsOrt;
	}

	@Override
	public int pris() {
		return this.pris;
	}
	
	@Override
	public String toString(){
		return "bilID: " + this.bilID + "\nhämtdatum: " + this.hamtDatum + "\nlämningsdatum: " + this.lamningsDatum + 
				"\nhämtort: " + this.hamtOrt + "\nlämningsort: " + this.lamningsOrt + "\nPris: " + this.pris;
	}

	@Override
	public Bokningstyp getType() {
		return Bokningstyp.HYRBIL;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public String getKategori() {
		return kategori;
	}

	@Override
	public boolean searchIsDone() {
		return this.searchDone;
	}

	@Override
	public String getOrt() {
		return this.lamningsOrt;
	}

}
