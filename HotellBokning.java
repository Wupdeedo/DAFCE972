import java.sql.Date;


public class HotellBokning implements Bokning{
	private int hotellID;
	private Date fran;
	private Date till;
	private int pris;
	
	public HotellBokning(){
		
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
	
	//TODO toString()
}
