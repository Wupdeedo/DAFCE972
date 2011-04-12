import java.sql.Date;


public class HotellBokning implements Bokning{
	private int hotellID;
	private String ort;
	private Date fran;
	private Date till;
	private int antalRum;
	private int antalPersoner;
	private int pris;
	private boolean searchDone = false;
	
	public HotellBokning(){
		
	}
	
	public HotellBokning(String ort, Date checkInDatum, Date checkUtDatum, int antalRum, int antalPersoner){
		this.setOrt(ort);
		this.fran = checkInDatum;
		this.till = checkUtDatum;
		this.antalRum = antalRum;
		this.antalPersoner = antalPersoner;
		searchDone = true;
	}

	public void setHotellID(int hotellID) {
		this.hotellID = hotellID;
	}

	public int getHotellID() {
		return hotellID;
	}

	public void setFran(Date fran) {
		this.fran = fran;
	}

	public Date getFran() {
		return fran;
	}

	public void setTill(Date till) {
		this.till = till;
	}

	public Date getTill() {
		return till;
	}

	@Override
	public int pris() {
		return this.pris;
	}

	@Override
	public Bokningstyp getType() {
		return Bokningstyp.HOTELL;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getOrt() {
		return ort;
	}

	public void setAntalRum(int antalRum) {
		this.antalRum = antalRum;
	}

	public int getAntalRum() {
		return antalRum;
	}

	public void setAntalPersoner(int antalPersoner) {
		this.antalPersoner = antalPersoner;
	}

	public int getAntalPersoner() {
		return antalPersoner;
	}

	@Override
	public boolean searchIsDone() {
		return this.searchDone;
	}
	
	//TODO toString()
}
